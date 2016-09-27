package cn.cloudwalk.ebank.core.domain.service.resource;

import cn.cloudwalk.ebank.core.domain.model.resource.ResourceEntity;
import cn.cloudwalk.ebank.core.repository.resource.IResourceRepository;
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
@Service("resourceService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ResourceService implements IResourceService {

    @Autowired
    private IResourceRepository<ResourceEntity, String> resourceRepository;

    @Override
    public List<ResourceEntity> findAll() {
        // 添加排序
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));

        // 添加fetchMode
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("roleEntities", FetchMode.JOIN);

        return resourceRepository.findAll(null, orders, fetchModeMap);
    }
}
