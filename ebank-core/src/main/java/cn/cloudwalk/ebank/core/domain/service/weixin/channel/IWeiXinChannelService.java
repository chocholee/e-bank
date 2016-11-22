package cn.cloudwalk.ebank.core.domain.service.weixin.channel;

import cn.cloudwalk.ebank.core.domain.model.weixin.channel.WeiXinChannelEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.channel.command.WeiXinChannelCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.channel.command.WeiXinChannelPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

/**
 * Created by liwenhe on 16/11/22.
 */
public interface IWeiXinChannelService {

    Pagination<WeiXinChannelEntity> pagination(WeiXinChannelPaginationCommand command);

    WeiXinChannelEntity findById(String id);

    WeiXinChannelEntity save(WeiXinChannelCommand command);

    WeiXinChannelEntity update(WeiXinChannelCommand command);

    void delete(String id);

}
