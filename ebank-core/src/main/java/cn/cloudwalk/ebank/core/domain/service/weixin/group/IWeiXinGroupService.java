package cn.cloudwalk.ebank.core.domain.service.weixin.group;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import com.arm4j.weixin.exception.WeiXinRequestException;

/**
 * Created by liwenhe on 16/11/23.
 */
public interface IWeiXinGroupService {

    Pagination<WeiXinGroupEntity> pagination(WeiXinGroupPaginationCommand command);

    WeiXinGroupEntity findById(String id);

    WeiXinGroupEntity save(WeiXinGroupCommand command) throws WeiXinRequestException;

    WeiXinGroupEntity update(WeiXinGroupCommand command) throws WeiXinRequestException;

    void delete(String id) throws WeiXinRequestException;

    void sync() throws WeiXinRequestException;

}
