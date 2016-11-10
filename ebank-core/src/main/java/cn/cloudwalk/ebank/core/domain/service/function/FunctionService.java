package cn.cloudwalk.ebank.core.domain.service.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntityType;
import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionCommand;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.function.IFunctionRepository;
import cn.cloudwalk.ebank.core.repository.icon.IIconRepository;
import cn.cloudwalk.ebank.core.repository.role.IRoleRepository;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Service("functionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FunctionService implements IFunctionService {

    @Autowired
    private IFunctionRepository<FunctionEntity, String> functionRepository;

    @Autowired
    private IIconRepository<IconEntity, String> iconRepository;

    @Autowired
    private IRoleRepository<RoleEntity, String> roleRepository;

    @Override
    public Pagination<FunctionEntity> pagination(FunctionPaginationCommand command) {
        List<Criterion> criterions = new ArrayList<>();
        if (!StringUtils.isEmpty(command.getName())) {
            criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }
        if (!StringUtils.isEmpty(command.getCode())) {
            criterions.add(Restrictions.like("code", command.getCode(), MatchMode.ANYWHERE));
        }

        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));

        return functionRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
    }

    @Override
    public List<FunctionEntity> findAll() {
        // 添加排序
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));
        return functionRepository.findAll(null, orders, null);
    }

    @Override
    public List<FunctionEntity> findForFirstMenu(boolean isFetchIcon, boolean isFetchRole) {
        // 添加查询条件
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.eq("type", FunctionEntityType.FIRST));

        // 添加排序
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));

        // 添加fetch mode
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        if (isFetchIcon) fetchModeMap.put("iconEntity", FetchMode.JOIN);
        if (isFetchRole) fetchModeMap.put("roleEntities", FetchMode.JOIN);

        return functionRepository.findAll(criterions, orders, fetchModeMap);
    }

    @Override
    public List<FunctionEntity> findByParentId(String parentId, boolean isFetchIcon, boolean isFetchRole) {
        // 添加查询条件
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.eq("parent.id", parentId));

        // 添加排序
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));

        // 添加fetch mode
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("parent", FetchMode.JOIN);
        if (isFetchIcon) fetchModeMap.put("iconEntity", FetchMode.JOIN);
        if (isFetchRole) fetchModeMap.put("roleEntities", FetchMode.JOIN);

        return functionRepository.findAll(criterions, orders, fetchModeMap);
    }

    @Override
    public FunctionEntity findById(String id) {
        return functionRepository.getById(id);
    }

    @Override
    public FunctionEntity findByIdAndFetch(String id) {
        return functionRepository.findById(id);
    }

    @Override
    public FunctionEntity save(FunctionCommand command) {
        IconEntity iconEntity = iconRepository.getById(command.getIconId());
        FunctionEntity parent = null;
        if (null != command.getParent() && !StringUtils.isEmpty(command.getParent().getId())) {
            parent = functionRepository.getById(command.getParent().getId());
        }
        FunctionEntity entity = new FunctionEntity(
                command.getName(),
                command.getUri(),
                command.getDescription(),
                command.getOrder(),
                command.getType(),
                parent,
                iconEntity,
                null
        );
        functionRepository.save(entity);
        return entity;
    }

    @Override
    public FunctionEntity update(FunctionCommand command) {
        // 得到父菜单
        FunctionEntity parent = null;
        if (command.getType() != FunctionEntityType.FIRST
                && null != command.getParent()
                && !StringUtils.isEmpty(command.getParent().getId())) {
            parent = functionRepository.getById(command.getParent().getId());
        }

        // 得到图标
        IconEntity iconEntity = iconRepository.getById(command.getIconId());

        // 得到当前数据并更新
        FunctionEntity entity = functionRepository.getById(command.getId());
        entity.setName(command.getName());
        entity.setUri(command.getUri());
        entity.setDescription(command.getDescription());
        entity.setOrder(command.getOrder());
        entity.setType(command.getType());
        entity.setParent(parent);
        entity.setIconEntity(iconEntity);
        functionRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        // 清空菜单中相应的function
        FunctionEntity entity = functionRepository.findById(id);
        for (RoleEntity role : entity.getRoleEntities()) {
            Set<FunctionEntity> newFunctionEntities = new HashSet<>();
            for (FunctionEntity function : role.getFunctionEntities()) {
                if (!entity.getId().equals(function.getId())) {
                    newFunctionEntities.add(function);
                }
            }
            role.setFunctionEntities(newFunctionEntities);
            roleRepository.update(role);
        }

        List<FunctionEntity> children = this.findByParentId(entity.getId(), false, false);
        for (FunctionEntity function : children) {
            this.delete(function.getId());
        }

        entity.setParent(null);
        functionRepository.update(entity);

        // 删除function
        functionRepository.delete(entity);
    }
}
