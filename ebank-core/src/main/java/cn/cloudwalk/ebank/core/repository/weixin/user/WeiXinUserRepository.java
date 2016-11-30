package cn.cloudwalk.ebank.core.repository.weixin.user;

import cn.cloudwalk.ebank.core.domain.model.weixin.user.WeiXinUserEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/29.
 */
@Repository("weiXinUserRepository")
public class WeiXinUserRepository extends AbstractHibernateRepository<WeiXinUserEntity, String>
        implements IWeiXinUserRepository<WeiXinUserEntity, String> {

    @Override
    public WeiXinUserEntity findByOpenId(String openId) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("openId", openId))
                .createAlias("groupWechat", "groupWechat", JoinType.LEFT_OUTER_JOIN);
        return (WeiXinUserEntity) criteria.uniqueResult();
    }

    @Override
    public WeiXinUserEntity findByIdAndFetch(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .createAlias("groupWechat", "groupWechat", JoinType.LEFT_OUTER_JOIN)
                .createAlias("groupVirtualEntities", "groupVirtualEntities", JoinType.LEFT_OUTER_JOIN)
                .createAlias("channel", "channel", JoinType.LEFT_OUTER_JOIN);
        return (WeiXinUserEntity) criteria.uniqueResult();
    }

}
