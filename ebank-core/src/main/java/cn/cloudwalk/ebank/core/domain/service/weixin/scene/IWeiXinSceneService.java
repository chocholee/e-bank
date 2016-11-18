package cn.cloudwalk.ebank.core.domain.service.weixin.scene;

import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.scene.command.WeiXinSceneCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.scene.command.WeiXinScenePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

/**
 * Created by liwenhe on 16/11/17.
 */
public interface IWeiXinSceneService {

    Pagination<WeiXinSceneEntity> pagination(WeiXinScenePaginationCommand command);

    WeiXinSceneEntity findById(String id);

    WeiXinSceneEntity save(WeiXinSceneCommand command);

    WeiXinSceneEntity update(WeiXinSceneCommand command);

    void delete(String id);

}
