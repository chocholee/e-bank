package cn.cloudwalk.ebank.core.repository.weixin.template.news;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Repository("weiXinNewsTemplateRepository")
public class WeiXinNewsTemplateRepository extends AbstractHibernateRepository<WeiXinNewsTemplateEntity, String>
        implements IWeiXinNewsTemplateRepository<WeiXinNewsTemplateEntity, String> {
}
