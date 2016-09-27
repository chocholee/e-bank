package cn.cloudwalk.ebank.core.domain.service.role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleService implements IRoleService {
}
