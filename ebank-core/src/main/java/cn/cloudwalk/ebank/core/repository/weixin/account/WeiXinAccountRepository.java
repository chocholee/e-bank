package cn.cloudwalk.ebank.core.repository.weixin.account;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
@Repository("weiXinAccountRepository")
public class WeiXinAccountRepository extends AbstractHibernateRepository<WeiXinAccountEntity, String>
        implements IWeiXinAccountRepository<WeiXinAccountEntity, String> {

    @Override
    public WeiXinAccountEntity findById(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .setFetchMode("user", FetchMode.JOIN);
        return (WeiXinAccountEntity) criteria.uniqueResult();
    }

    @Override
    public WeiXinAccountEntity findByAppId(String appId) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("appId", appId))
                .setFetchMode("user", FetchMode.JOIN);
        return (WeiXinAccountEntity) criteria.uniqueResult();
    }

    @Override
    public WeiXinAccountEntity findByUsername(String username) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("user.username", username))
                .setFetchMode("user", FetchMode.JOIN)
                .createAlias("user", "user");
        return (WeiXinAccountEntity) criteria.uniqueResult();
    }
}
