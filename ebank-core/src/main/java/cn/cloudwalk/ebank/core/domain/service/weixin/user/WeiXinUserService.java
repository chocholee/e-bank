package cn.cloudwalk.ebank.core.domain.service.weixin.user;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.user.WeiXinUserEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.user.WeiXinUserEntitySex;
import cn.cloudwalk.ebank.core.domain.model.weixin.user.WeiXinUserEntitySubscribeType;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.IWeiXinGroupService;
import cn.cloudwalk.ebank.core.domain.service.weixin.user.command.WeiXinUserPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.group.wechat.IWeiXinGroupWechatRepository;
import cn.cloudwalk.ebank.core.repository.weixin.user.IWeiXinUserRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import com.arm4j.weixin.exception.WeiXinRequestException;
import com.arm4j.weixin.request.group.WeiXinGroupsMembersUpdateRequest;
import com.arm4j.weixin.request.user.WeiXinUserInfoBatchGetRequest;
import com.arm4j.weixin.request.user.WeiXinUserListGetRequest;
import com.arm4j.weixin.request.user.entity.UserInfoBatchGetEntity;
import com.arm4j.weixin.request.user.entity.UserInfoEntity;
import com.arm4j.weixin.request.user.response.UserListResponse;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by liwenhe on 16/11/29.
 */
@Service("weiXinUserService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinUserService implements IWeiXinUserService {

    @Autowired
    private IWeiXinUserRepository<WeiXinUserEntity, String> weiXinUserRepository;

    @Autowired
    private IWeiXinGroupWechatRepository<WeiXinGroupWechatEntity, String> weiXinGroupWechatRepository;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @Autowired
    private IWeiXinGroupService weiXinGroupService;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinUserEntity> pagination(WeiXinUserPaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getNickname())) {
                criterions.add(Restrictions.like("nickname", command.getNickname(), MatchMode.ANYWHERE));
            }

            if (!StringUtils.isEmpty(command.getGroupWechatId())) {
                criterions.add(Restrictions.eq("groupWechat.id", command.getGroupWechatId()));
            }

            if (!StringUtils.isEmpty(command.getGroupVirtualId())) {
                criterions.add(Restrictions.eq("groupVirtualEntities.id", command.getGroupVirtualId()));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));
            criterions.add(Restrictions.eq("subscribeType", WeiXinUserEntitySubscribeType.SUBSCRIBED));

            // 添加fetchMode
            Map<String, FetchMode> fetchModeMap = new HashMap<>();
            fetchModeMap.put("groupWechat", FetchMode.JOIN);
            fetchModeMap.put("groupVirtualEntities", FetchMode.JOIN);

            // 添加别名
            Map<String, String> aliasMap = new HashMap<>();
//            aliasMap.put("groupWechat", "groupWechat");
//            aliasMap.put("groupVirtualEntities", "groupVirtualEntities");

            return weiXinUserRepository.pagination(command.getPage(), command.getPageSize(), criterions, null, fetchModeMap, aliasMap);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinUserEntity findById(String id) {
        return weiXinUserRepository.getById(id);
    }

    @Override
    public WeiXinUserEntity findByIdAndFetch(String id) {
        return weiXinUserRepository.findByIdAndFetch(id);
    }

    @Override
    public void sync() throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 先同步微信分组
        weiXinGroupService.sync();

        // 得到accessToken
        String accessToken = weiXinAccountService.getAccessToken(accountEntity.getAppId());

        // 得到openIdList
        UserListResponse response = WeiXinUserListGetRequest.request(accessToken);
        while (!StringUtils.isEmpty(response.getNextOpenId())) {
            List<UserInfoBatchGetEntity> batchGetEntityList = new ArrayList<>();
            for (int i = 0; i <= response.getCount(); i++) {
                if (i % 100 == 0 && i != 0
                        || response.getCount() - i < 100 && (i + 1) > response.getCount()) {
                    // 得到具体的用户信息
                    List<UserInfoEntity> userInfoEntityList =
                            WeiXinUserInfoBatchGetRequest.request(accessToken, batchGetEntityList);

                    // 得到的微信信息存至数据库中
                    for (UserInfoEntity userInfoEntity : userInfoEntityList) {
                        // 查找分组与用户信息
                        WeiXinGroupWechatEntity groupWechatEntity =
                                weiXinGroupWechatRepository.findByGroupId(userInfoEntity.getGroupId(), accountEntity.getId());
                        WeiXinUserEntity weiXinUserEntity =
                                weiXinUserRepository.findByOpenId(userInfoEntity.getOpenId());

                        // 判断用户信息是否存在
                        if (null != weiXinUserEntity) {
                            weiXinUserEntity.setCountry(userInfoEntity.getCountry());
                            weiXinUserEntity.setCity(userInfoEntity.getCity());
                            weiXinUserEntity.setProvince(userInfoEntity.getProvince());
                            weiXinUserEntity.setLanguage(userInfoEntity.getLanguage());
                            weiXinUserEntity.setHeadImgUrl(userInfoEntity.getHeadImgUrl());
                            weiXinUserEntity.setSex(WeiXinUserEntitySex.byWeiXinValue(userInfoEntity.getSex()));
                            weiXinUserEntity.setSubscribeType(WeiXinUserEntitySubscribeType.byWeiXinValue(userInfoEntity.getSubscribe()));
                            weiXinUserRepository.update(weiXinUserEntity);
                        } else {
                            weiXinUserEntity = new WeiXinUserEntity(
                                    userInfoEntity.getNickname(),
                                    null,
                                    userInfoEntity.getOpenId(),
                                    userInfoEntity.getCountry(),
                                    userInfoEntity.getCity(),
                                    userInfoEntity.getProvince(),
                                    userInfoEntity.getLanguage(),
                                    userInfoEntity.getHeadImgUrl(),
                                    null,
                                    WeiXinUserEntitySex.byWeiXinValue(userInfoEntity.getSex()),
                                    WeiXinUserEntitySubscribeType.byWeiXinValue(userInfoEntity.getSubscribe()),
                                    groupWechatEntity,
                                    null,
                                    null,
                                    accountEntity.getId()
                            );
                            weiXinUserRepository.save(weiXinUserEntity);
                        }
                    }

                    // 清空list中的数据
                    userInfoEntityList.clear();

                    // 如果已处理完数据,break
                    if (i >= response.getCount()) {
                        break;
                    }
                }

                UserInfoBatchGetEntity batchGetEntity = new UserInfoBatchGetEntity();
                batchGetEntity.setOpenId(response.getData().getOpenIds().get(i));
                batchGetEntityList.add(batchGetEntity);
            }

            // 得到下一批数据
            response = WeiXinUserListGetRequest.request(accessToken, response.getNextOpenId());
        }
    }

    @Override
    public void doGroupWechat(String id, String newGroupWechatId) throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 查看微信用户并判断用户是否可以分组
        WeiXinUserEntity userEntity = weiXinUserRepository.findByIdAndFetch(id);
        if (null == userEntity.getGroupWechat()
                || !userEntity.getGroupWechat().getId().equals(newGroupWechatId)) {
            // 查询分组并更新微信用户信息
            WeiXinGroupWechatEntity groupWechatEntity = weiXinGroupWechatRepository.getById(newGroupWechatId);
            userEntity.setGroupWechat(groupWechatEntity);
            weiXinUserRepository.update(userEntity);

            // 更新到微信服务器
            String accessToken = weiXinAccountService.getAccessToken(accountEntity.getAppId());
            WeiXinGroupsMembersUpdateRequest.request(accessToken, userEntity.getOpenId(), groupWechatEntity.getGroupId());

            // 同步微信分组数据
            weiXinGroupService.sync();
        }
    }
}
