package cn.cloudwalk.ebank.core.domain.service.weixin.account;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntityStatus;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountPaginationCommand;
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

    String getAccessTokenByAccountId(String accountId) throws WeiXinRequestException;

    String getJsApiTicket(String appId) throws WeiXinRequestException;

    WeiXinAccountEntity findById(String id);

    WeiXinAccountEntity findByAppId(String appId);

    WeiXinAccountEntity findByUsername(String username);

    WeiXinAccountEntity findByAccountId(String accountId);

    WeiXinAccountEntity save(WeiXinAccountCommand command);

    WeiXinAccountEntity update(WeiXinAccountCommand command);

    void authorize(String id, WeiXinAccountEntityStatus status);

    void delete(String id);

    void token(String id) throws WeiXinRequestException;

    void activated(String id);
}
