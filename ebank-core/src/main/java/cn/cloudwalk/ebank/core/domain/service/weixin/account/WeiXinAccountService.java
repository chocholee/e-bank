package cn.cloudwalk.ebank.core.domain.service.weixin.account;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.service.user.IUserService;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import com.arm4j.weixin.exception.WeiXinRequestException;
import com.arm4j.weixin.request.accesstoken.WeiXinAccessTokenRequest;
import com.arm4j.weixin.request.accesstoken.WeiXinJsApiTicketAccessTokenRequest;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
@Service("weiXinAccountService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinAccountService implements IWeiXinAccountService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private IUserService userService;

    @Override
    public Pagination<WeiXinAccountEntity> pagination(WeiXinAccountPaginationCommand command) {
        // 添加查询条件
        List<Criterion> criterions = new ArrayList<>();
        if (!StringUtils.isEmpty(command.getName())) {
            criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }
        if (!StringUtils.isEmpty(command.getAppId())) {
            criterions.add(Restrictions.like("appId", command.getAppId(), MatchMode.ANYWHERE));
        }
        if (null != command.getType() && !command.getType().isOnlyQuery()) {
            criterions.add(Restrictions.eq("type", command.getType()));
        }

        // 添加排序条件
        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("createdDate"));

        // 查询时关联user
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("user", FetchMode.JOIN);

        return weiXinAccountRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders, fetchModeMap);
    }

    @Override
    public String getAccessToken(String appId) throws WeiXinRequestException {
        // 判断accessToken是否空，为空时，获取accessToken并更新；
        // 不为空时，判断是否失效，失效则更新accessToken
        WeiXinAccountEntity entity = this.findByAppId(appId);
        if (StringUtils.isEmpty(entity.getAccessToken())) {
            String accessToken = WeiXinAccessTokenRequest
                    .request("client_credential", entity.getAppId(), entity.getAppSecret());
            entity.setAccessToken(accessToken);
            entity.setAccessTokenTime(new Date());
            weiXinAccountRepository.update(entity);
        } else {
            long tokenDate  = entity.getAccessTokenTime().getTime();
            long nowDate    =  new Date().getTime();
            if (tokenDate + 7200L <= nowDate) {
                String accessToken = WeiXinAccessTokenRequest
                        .request("client_credential", entity.getAppId(), entity.getAppSecret());
                entity.setAccessToken(accessToken);
                entity.setAccessTokenTime(new Date());
                weiXinAccountRepository.update(entity);
            }
        }
        return entity.getAccessToken();
    }

    @Override
    public String getJsApiTicket(String appId) throws WeiXinRequestException {
        // 判断jsApiTicket是否空，为空时，获取jsApiTicket并更新；
        // 不为空时，判断是否失效，失效则更新jsApiTicket
        WeiXinAccountEntity entity = this.findByAppId(appId);
        if (StringUtils.isEmpty(entity.getJsApiTicket())) {
            String accessToken = this.getAccessToken(appId);
            String jsApiTicket = WeiXinJsApiTicketAccessTokenRequest.request(accessToken, "jsapi");
            entity.setJsApiTicket(jsApiTicket);
            entity.setJsApiTicketTime(new Date());
            weiXinAccountRepository.update(entity);
        } else {
            long tokenDate  = entity.getJsApiTicketTime().getTime();
            long nowDate    =  new Date().getTime();
            if (tokenDate + 7200L <= nowDate) {
                String accessToken = this.getAccessToken(appId);
                String jsApiTicket = WeiXinJsApiTicketAccessTokenRequest.request(accessToken, "jsapi");
                entity.setJsApiTicket(jsApiTicket);
                entity.setJsApiTicketTime(new Date());
                weiXinAccountRepository.update(entity);
            }
        }
        return entity.getJsApiTicket();
    }

    @Override
    public WeiXinAccountEntity findById(String id) {
        return weiXinAccountRepository.findById(id);
    }

    @Override
    public WeiXinAccountEntity findByAppId(String appId) {
        return weiXinAccountRepository.findByAppId(appId);
    }

    @Override
    public WeiXinAccountEntity findByUsername(String username) {
        return weiXinAccountRepository.findByUsername(username);
    }

    @Override
    public WeiXinAccountEntity save(WeiXinAccountCommand command) {
        UserEntity userEntity = userService.findById(command.getUserId());
        WeiXinAccountEntity entity = new WeiXinAccountEntity(
                command.getName(),
                command.getToken(),
                command.getNumber(),
                command.getAccountId(),
                command.getAppId(),
                command.getAppSecret(),
                command.getEmail(),
                command.getDescription(),
                new Date(),
                command.getType(),
                userEntity
        );
        weiXinAccountRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinAccountEntity update(WeiXinAccountCommand command) {
        UserEntity userEntity = userService.findById(command.getUserId());
        WeiXinAccountEntity entity = this.findById(command.getId());
        entity.setName(command.getName());
        entity.setToken(command.getToken());
        entity.setNumber(command.getNumber());
        entity.setAccountId(command.getAccountId());
        entity.setAppId(command.getAppId());
        entity.setAppSecret(command.getAppSecret());
        entity.setEmail(command.getEmail());
        entity.setDescription(command.getDescription());
        entity.setType(command.getType());
        entity.setUser(userEntity);
        weiXinAccountRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        WeiXinAccountEntity entity = this.findById(id);
        weiXinAccountRepository.delete(entity);
    }
}
