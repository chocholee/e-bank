package cn.cloudwalk.ebank.core.domain.service.weixinaccount;

import cn.cloudwalk.ebank.core.domain.model.weixinaccount.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.service.weixinaccount.command.WeiXinAccountCommand;
import cn.cloudwalk.ebank.core.domain.service.weixinaccount.command.WeiXinAccountPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import com.arm4j.weixin.exception.WeiXinRequestException;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
public interface IWeiXinAccountService {

    Pagination<WeiXinAccountEntity> pagination(WeiXinAccountPaginationCommand command);

    String getAccessToken(String appId) throws WeiXinRequestException;

    String getJsApiTicket(String appId) throws WeiXinRequestException;

    WeiXinAccountEntity findById(String id);

    WeiXinAccountEntity findByAppId(String appId);

    WeiXinAccountEntity save(WeiXinAccountCommand command);

    WeiXinAccountEntity update(WeiXinAccountCommand command);

    void delete(String id);

}
