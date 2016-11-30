package cn.cloudwalk.ebank.core.domain.service.weixin.group.virtual;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.util.List;

/**
 * Created by liwenhe on 16/11/23.
 */
public interface IWeiXinGroupVirtualService {

    Pagination<WeiXinGroupVirtualEntity> pagination(WeiXinGroupPaginationCommand command);

    List<WeiXinGroupVirtualEntity> findAll();

}
