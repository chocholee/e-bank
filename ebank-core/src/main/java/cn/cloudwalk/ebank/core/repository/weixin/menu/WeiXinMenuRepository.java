package cn.cloudwalk.ebank.core.repository.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Repository("weiXinMenuRepository")
public class WeiXinMenuRepository extends AbstractHibernateRepository<WeiXinMenuEntity, String>
        implements IWeiXinMenuRepository<WeiXinMenuEntity, String> {

    @Override
    public WeiXinMenuEntity findByIdAndFetch(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .setFetchMode("parent", FetchMode.JOIN);
        return (WeiXinMenuEntity) criteria.uniqueResult();
    }

}
