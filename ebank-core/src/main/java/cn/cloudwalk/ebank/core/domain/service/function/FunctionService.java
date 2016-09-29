package cn.cloudwalk.ebank.core.domain.service.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionCommand;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.function.IFunctionRepository;
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
import java.util.List;

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
    public FunctionEntity findById(String id) {
        return functionRepository.getById(id);
    }

    @Override
    public FunctionEntity save(FunctionCommand command) {
        FunctionEntity parent = this.findById(command.getParent().getId());
        FunctionEntity entity = new FunctionEntity(
                command.getName(),
                command.getCode(),
                command.getUri(),
                command.getIconId(),
                command.getDescription(),
                command.getOrder(),
                command.getType(),
                parent,
                null
        );
        functionRepository.save(entity);
        return entity;
    }

    @Override
    public FunctionEntity update(FunctionCommand command) {
        return null;
    }

    @Override
    public void delete(String id) {
        FunctionEntity entity = this.findById(id);
        functionRepository.delete(entity);
    }
}
