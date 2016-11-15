package cn.cloudwalk.ebank.core.repository.weixin.template.text;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.text.WeiXinTextTemplateEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Repository("weiXinTextTemplateRepository")
public class WeiXinTextTemplateRepository extends AbstractHibernateRepository<WeiXinTextTemplateEntity, String>
        implements IWeiXinTextTemplateRepository<WeiXinTextTemplateEntity, String> {
}
