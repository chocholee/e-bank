package cn.cloudwalk.ebank.core.repository.weixin.keyword;

import cn.cloudwalk.ebank.core.domain.model.weixin.keyword.WeiXinKeywordEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
@Repository("weiXinKeywordRepository")
public class WeiXinKeywordRepository extends AbstractHibernateRepository<WeiXinKeywordEntity, String>
        implements IWeiXinKeywordRepository<WeiXinKeywordEntity, String> {

    @Override
    public WeiXinKeywordEntity findByKeyword(String keyword) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("keyword", keyword));
        return (WeiXinKeywordEntity) criteria.uniqueResult();
    }

}
