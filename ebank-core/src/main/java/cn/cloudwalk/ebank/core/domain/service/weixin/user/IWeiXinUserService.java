package cn.cloudwalk.ebank.core.domain.service.weixin.user;

import cn.cloudwalk.ebank.core.domain.model.weixin.user.WeiXinUserEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.user.command.WeiXinUserPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import com.arm4j.weixin.exception.WeiXinRequestException;

/**
 * Created by liwenhe on 16/11/29.
 */
public interface IWeiXinUserService {

    Pagination<WeiXinUserEntity> pagination(WeiXinUserPaginationCommand command);

    WeiXinUserEntity findById(String id);

    WeiXinUserEntity findByIdAndFetch(String id);

    void sync() throws WeiXinRequestException;

    void doGroupWechat(String id, String newGroupWechatId) throws WeiXinRequestException;

}
