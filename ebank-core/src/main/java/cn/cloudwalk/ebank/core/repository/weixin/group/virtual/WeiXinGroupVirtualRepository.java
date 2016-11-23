package cn.cloudwalk.ebank.core.repository.weixin.group.virtual;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/23.
 */
@Repository("weiXinGroupVirtualRepository")
public class WeiXinGroupVirtualRepository extends AbstractHibernateRepository<WeiXinGroupVirtualEntity, String>
        implements IWeiXinGroupVirtualRepository<WeiXinGroupVirtualEntity, String> {
}
