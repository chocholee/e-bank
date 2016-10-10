package cn.cloudwalk.ebank.core.repository.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate.WeiXinNewsItemsTemplateEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liwenhe on 2016/10/10.
 *
 * @author 李文禾
 */
@Repository("weiXinNewsItemsTemplateRepository")
public class WeiXinNewsItemsTemplateRepository extends AbstractHibernateRepository<WeiXinNewsItemsTemplateEntity, String>
        implements IWeiXinNewsItemsTemplateRepository<WeiXinNewsItemsTemplateEntity, String> {

    @Override
    public List<WeiXinNewsItemsTemplateEntity> findByNewsTemplateId(String newsTemplateId) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("newsTemplateEntity.id", newsTemplateId))
                .createAlias("newsTemplateEntity", "newsTemplateEntity")
                .setFetchMode("newsTemplateEntity", FetchMode.JOIN)
                .addOrder(Order.asc("order"));
        return criteria.list();
    }

}
