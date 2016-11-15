package cn.cloudwalk.ebank.core.repository.weixin.template.image;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.image.WeiXinImageTemplateEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/15.
 */
@Repository("weiXinImageTemplateRepository")
public class WeiXinImageTemplateRepository extends AbstractHibernateRepository<WeiXinImageTemplateEntity, String>
        implements IWeiXinImageTemplateRepository<WeiXinImageTemplateEntity, String> {
}
