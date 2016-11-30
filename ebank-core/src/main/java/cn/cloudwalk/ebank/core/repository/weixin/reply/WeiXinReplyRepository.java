package cn.cloudwalk.ebank.core.repository.weixin.reply;

import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liwenhe on 16/11/21.
 */
@Repository("weiXinReplyRepository")
public class WeiXinReplyRepository extends AbstractHibernateRepository<WeiXinReplyEntity, String>
        implements IWeiXinReplyRepository<WeiXinReplyEntity, String> {

    @Override
    public List<WeiXinReplyEntity> findBySceneId(String sceneId) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("scene.id", sceneId))
                .setFetchMode("scene", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public WeiXinReplyEntity findByKeyword(String keyword, String accountId) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("keyword", keyword))
                .add(Restrictions.eq("accountId", accountId));
        return (WeiXinReplyEntity) criteria.uniqueResult();
    }

}
