package cn.cloudwalk.ebank.core.domain.service.role;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.repository.role.IRoleRepository;
import org.hibernate.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<RoleEntity> findAll() {
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("userEntities", FetchMode.JOIN);
        return roleRepository.findAll(null, null, fetchModeMap);
    }
}
