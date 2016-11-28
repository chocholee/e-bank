package cn.cloudwalk.ebank.core.repository.weixin.menucustomrule;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/25.
 */
@Repository("weiXinMenuCustomRuleRepository")
public class WeiXinMenuCustomRuleRepository extends AbstractHibernateRepository<WeiXinMenuCustomRuleEntity, String>
        implements IWeiXinMenuCustomRuleRepository<WeiXinMenuCustomRuleEntity, String> {

    @Override
    public WeiXinMenuCustomRuleEntity findByIdAndFetch(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .createAlias("menuCustom", "menuCustom", JoinType.LEFT_OUTER_JOIN)
                .createAlias("groupWechat", "groupWechat", JoinType.LEFT_OUTER_JOIN);
        return (WeiXinMenuCustomRuleEntity) criteria.uniqueResult();
    }

}
