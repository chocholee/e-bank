package cn.cloudwalk.ebank.core.domain.service.weixin.reply;

import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.reply.command.WeiXinReplyCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.reply.command.WeiXinReplyPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.util.List;

/**
 * Created by liwenhe on 16/11/21.
 */
public interface IWeiXinReplyService {

    Pagination<WeiXinReplyEntity> pagination(WeiXinReplyPaginationCommand command);

    List<WeiXinReplyEntity> findBySceneId(String sceneId);

    WeiXinReplyEntity findById(String id);

    WeiXinReplyEntity findByKeyword(String keyword, String accountId);

    WeiXinReplyEntity save(WeiXinReplyCommand command);

    WeiXinReplyEntity update(WeiXinReplyCommand command);

    void delete(String id);

}
