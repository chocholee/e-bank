package cn.cloudwalk.ebank.core.domain.service.weixin.group.wechat;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.util.List;

/**
 * Created by liwenhe on 16/11/23.
 */
public interface IWeiXinGroupWechatService {

    Pagination<WeiXinGroupWechatEntity> pagination(WeiXinGroupPaginationCommand command);

    List<WeiXinGroupWechatEntity> findAll();

}
