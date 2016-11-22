package cn.cloudwalk.ebank.core.repository.weixin.channel;

import cn.cloudwalk.ebank.core.domain.model.weixin.channel.WeiXinChannelEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/22.
 */
@Repository("weiXinChannelRepository")
public class WeiXinChannelRepository extends AbstractHibernateRepository<WeiXinChannelEntity, String>
        implements IWeiXinChannelRepository<WeiXinChannelEntity, String> {
}
