package cn.cloudwalk.ebank.core.domain.service.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.repository.function.IFunctionRepository;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<FunctionEntity> findAll() {
        // 添加排序
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));

        // 添加fetchMode
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("roleEntities", FetchMode.JOIN);

        return functionRepository.findAll(null, orders, fetchModeMap);
    }
}
