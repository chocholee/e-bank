package cn.cloudwalk.ebank.core.domain.service.role;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import cn.cloudwalk.ebank.core.domain.service.role.command.RoleCommand;
import cn.cloudwalk.ebank.core.domain.service.role.command.RolePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.role.IRoleRepository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository<RoleEntity, String> roleRepository;

    @Autowired
    private IFunctionService functionService;

    @Override
    public Pagination<RoleEntity> pagination(RolePaginationCommand command) {
        List<Criterion> criterions = new ArrayList<>();
        if (!StringUtils.isEmpty(command.getName())) {
            criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));
        return roleRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public RoleEntity save(RoleCommand command) {
//        RoleEntity parent = this.findById(command.getParent().getId());
        RoleEntity entity = new RoleEntity(
                command.getName(),
                command.getDescription(),
                command.getOrder(),
                null,
                null
        );
        roleRepository.save(entity);
        return entity;
    }

    @Override
    public RoleEntity update(RoleCommand command) {
        RoleEntity entity = this.findById(command.getId());
//        RoleEntity parent = this.findById(command.getParent().getId());
        entity.setName(command.getName());
        entity.setDescription(command.getDescription());
        entity.setOrder(command.getOrder());
//        entity.setParent(parent);
        roleRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        RoleEntity entity = this.findById(id);
        roleRepository.delete(entity);
    }

    @Override
    public void authorize(String id, String[] functionIds) {
        List<FunctionEntity> functionEntities = new ArrayList<>();
        if (null != functionIds) {
            for (String functionId : functionIds) {
                FunctionEntity functionEntity = functionService.findById(functionId);
                functionEntities.add(functionEntity);
            }
        }
        RoleEntity entity = this.findById(id);
        entity.setFunctionEntities(functionEntities);
        roleRepository.update(entity);
    }
}
