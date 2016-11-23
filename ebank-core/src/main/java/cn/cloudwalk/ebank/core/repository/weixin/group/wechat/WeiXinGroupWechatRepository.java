package cn.cloudwalk.ebank.core.repository.weixin.group.wechat;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/23.
 */
@Repository("weiXinGroupWechatRepository")
public class WeiXinGroupWechatRepository extends AbstractHibernateRepository<WeiXinGroupWechatEntity, String>
        implements IWeiXinGroupWechatRepository<WeiXinGroupWechatEntity, String> {

    @Override
    public WeiXinGroupWechatEntity findByGroupId(Integer groupId) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("groupId", groupId));
        return (WeiXinGroupWechatEntity) criteria.uniqueResult();
    }

}
