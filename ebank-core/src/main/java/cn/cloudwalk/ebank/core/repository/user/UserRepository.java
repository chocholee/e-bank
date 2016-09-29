package cn.cloudwalk.ebank.core.repository.user;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
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
@Repository("userRepository")
public class UserRepository extends AbstractHibernateRepository<UserEntity, String>
        implements IUserRepository<UserEntity, String> {

    @Override
    public UserEntity findById(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .setFetchMode("roleEntities", FetchMode.JOIN);
        return (UserEntity) criteria.uniqueResult();
    }

    @Override
    public UserEntity findByUsername(String username) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("username", username))
                .setFetchMode("roleEntities", FetchMode.JOIN);
        return (UserEntity) criteria.uniqueResult();
    }

}
