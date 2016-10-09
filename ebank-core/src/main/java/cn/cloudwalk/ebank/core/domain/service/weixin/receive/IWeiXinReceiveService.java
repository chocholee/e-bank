package cn.cloudwalk.ebank.core.domain.service.weixin.receive;

import cn.cloudwalk.ebank.core.domain.model.weixin.receive.WeiXinReceiveEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.receive.command.WeiXinReceiveCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.receive.command.WeiXinReceivePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public interface IWeiXinReceiveService {

    Pagination<WeiXinReceiveEntity> pagination(WeiXinReceivePaginationCommand command);

    WeiXinReceiveEntity save(WeiXinReceiveCommand command);

    void response(String id, String content);

    void delete(String id);

}
