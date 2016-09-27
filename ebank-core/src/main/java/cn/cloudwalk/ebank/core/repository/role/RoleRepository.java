package cn.cloudwalk.ebank.core.repository.role;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Repository("roleRepository")
public class RoleRepository extends AbstractHibernateRepository<RoleEntity, String>
        implements IRoleRepository<RoleEntity, String> {
}
