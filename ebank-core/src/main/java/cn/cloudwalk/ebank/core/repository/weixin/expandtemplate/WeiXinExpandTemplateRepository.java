package cn.cloudwalk.ebank.core.repository.weixin.expandtemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.expandtemplate.WeiXinExpandTemplateEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Repository("weiXinExpandTemplateRepository")
public class WeiXinExpandTemplateRepository extends AbstractHibernateRepository<WeiXinExpandTemplateEntity, String>
        implements IWeiXinExpandTemplateRepository<WeiXinExpandTemplateEntity, String> {
}
