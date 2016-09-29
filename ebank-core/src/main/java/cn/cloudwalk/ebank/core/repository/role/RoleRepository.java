package cn.cloudwalk.ebank.core.repository.role;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Repository("roleRepository")
public class RoleRepository extends AbstractHibernateRepository<RoleEntity, String>
        implements IRoleRepository<RoleEntity, String> {

    @Override
    public RoleEntity findById(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .setFetchMode("parent", FetchMode.JOIN)
                .setFetchMode("functionEntities", FetchMode.JOIN);
        return (RoleEntity) criteria.uniqueResult();
    }

}
