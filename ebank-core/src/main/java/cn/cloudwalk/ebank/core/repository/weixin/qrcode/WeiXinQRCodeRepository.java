package cn.cloudwalk.ebank.core.repository.weixin.qrcode;

import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/24.
 */
@Repository("weiXinQRCodeRepository")
public class WeiXinQRCodeRepository extends AbstractHibernateRepository<WeiXinQRCodeEntity, String>
        implements IWeiXinQRCodeRepository<WeiXinQRCodeEntity, String> {

    @Override
    public WeiXinQRCodeEntity findByIdAndFetch(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .createAlias("scene", "scene", JoinType.LEFT_OUTER_JOIN)
                .createAlias("channel", "channel", JoinType.LEFT_OUTER_JOIN)
                .createAlias("groupWechat", "groupWechat", JoinType.LEFT_OUTER_JOIN)
                .createAlias("groupVirtual", "groupVirtual", JoinType.LEFT_OUTER_JOIN);
        return (WeiXinQRCodeEntity) criteria.uniqueResult();
    }

}
