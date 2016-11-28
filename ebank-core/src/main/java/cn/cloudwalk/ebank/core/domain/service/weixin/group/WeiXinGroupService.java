package cn.cloudwalk.ebank.core.domain.service.weixin.group;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntityType;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.group.IWeiXinGroupRepository;
import cn.cloudwalk.ebank.core.repository.weixin.group.virtual.IWeiXinGroupVirtualRepository;
import cn.cloudwalk.ebank.core.repository.weixin.group.wechat.IWeiXinGroupWechatRepository;
import cn.cloudwalk.ebank.core.repository.weixin.menucustomrule.IWeiXinMenuCustomRuleRepository;
import cn.cloudwalk.ebank.core.repository.weixin.qrcode.IWeiXinQRCodeRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import com.arm4j.weixin.exception.WeiXinRequestException;
import com.arm4j.weixin.request.group.WeiXinGroupsCreateRequest;
import com.arm4j.weixin.request.group.WeiXinGroupsDeleteRequest;
import com.arm4j.weixin.request.group.WeiXinGroupsGetRequest;
import com.arm4j.weixin.request.group.WeiXinGroupsUpdateRequest;
import com.arm4j.weixin.request.group.entity.GroupEntity;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by liwenhe on 16/11/23.
 */
@Service("weiXinGroupService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinGroupService implements IWeiXinGroupService {

    @Autowired
    private IWeiXinGroupRepository<WeiXinGroupEntity, String> weiXinGroupRepository;

    @Autowired
    private IWeiXinGroupWechatRepository<WeiXinGroupWechatEntity, String> weiXinGroupWechatRepository;

    @Autowired
    private IWeiXinGroupVirtualRepository<WeiXinGroupVirtualEntity, String> weiXinGroupVirtualRepository;

    @Autowired
    private IWeiXinQRCodeRepository<WeiXinQRCodeEntity, String> weiXinQRCodeRepository;

    @Autowired
    private IWeiXinMenuCustomRuleRepository<WeiXinMenuCustomRuleEntity, String> weiXinMenuCustomRuleRepository;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinGroupEntity> pagination(WeiXinGroupPaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getName())) {
                criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
            }
            if (null != command.getType() && !command.getType().isOnlyQuery()) {
                criterions.add(Restrictions.eq("type", command.getType()));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinGroupRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinGroupEntity findById(String id) {
        return weiXinGroupRepository.getById(id);
    }

    @Override
    public WeiXinGroupEntity save(WeiXinGroupCommand command) throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 添加微信分组数据
        if (command.getType() == WeiXinGroupEntityType.WECHAT) {
            // 添加微信分组至微信服务器
            GroupEntity group = new GroupEntity();
            group.setName(command.getName());
            group = WeiXinGroupsCreateRequest.request(
                    weiXinAccountService.getAccessToken(accountEntity.getAppId()), group);

            // 添加微信分组到数据库
            WeiXinGroupWechatEntity entity = new WeiXinGroupWechatEntity(
                    command.getName(),
                    group.getId(),
                    0,
                    command.getRemark(),
                    new Date(),
                    accountEntity.getId()
            );
            weiXinGroupWechatRepository.save(entity);
            return entity;
        }

        // 添加虚拟分组数据
        if (command.getType() == WeiXinGroupEntityType.VIRTUAL) {
            WeiXinGroupVirtualEntity entity = new WeiXinGroupVirtualEntity(
                    command.getName(),
                    null,
                    null,
                    command.getRemark(),
                    new Date(),
                    accountEntity.getId()
            );
            weiXinGroupVirtualRepository.save(entity);
            return entity;
        }

        return null;
    }

    @Override
    public WeiXinGroupEntity update(WeiXinGroupCommand command) throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 从数据库中查出数据
        WeiXinGroupEntity entity = this.findById(command.getId());

        // 如果微信分组，则更新数据到微信服务器
        if (entity.getType() == WeiXinGroupEntityType.WECHAT) {
            GroupEntity group = new GroupEntity();
            group.setId(entity.getGroupId());
            group.setName(command.getName());
            WeiXinGroupsUpdateRequest.request(
                    weiXinAccountService.getAccessToken(accountEntity.getAppId()), group);
        }

        // 更新数据至数据库中
        entity.setName(command.getName());
        entity.setRemark(command.getRemark());
        weiXinGroupRepository.update(entity);

        return entity;
    }

    @Override
    public void delete(String id) throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 从数据库中查出数据
        WeiXinGroupEntity entity = this.findById(id);

        // 如果微信分组，则更新数据到微信服务器
        if (entity.getType() == WeiXinGroupEntityType.WECHAT) {
            GroupEntity group = new GroupEntity();
            group.setId(entity.getGroupId());
            WeiXinGroupsDeleteRequest.request(
                    weiXinAccountService.getAccessToken(accountEntity.getAppId()), group);
        }

        // 删除二维码与分组的外键关系
        DetachedCriteria qrCodeDc = DetachedCriteria.forClass(WeiXinQRCodeEntity.class);
        qrCodeDc.add(Restrictions.or(
                Restrictions.eq("groupWechat.id", id),
                Restrictions.eq("groupVirtual.id", id)))
                .createAlias("groupWechat", "groupWechat", JoinType.LEFT_OUTER_JOIN)
                .createAlias("groupVirtual", "groupVirtual", JoinType.LEFT_OUTER_JOIN);
        List<WeiXinQRCodeEntity> qrList = weiXinQRCodeRepository.findAll(qrCodeDc);
        for (WeiXinQRCodeEntity qrCode : qrList) {
            if (entity.getType() == WeiXinGroupEntityType.WECHAT) {
                weiXinQRCodeRepository.delete(qrCode);
            } else {
                qrCode.setGroupVirtual(null);
                weiXinQRCodeRepository.update(qrCode);
            }
        }

        // 删除与个性化规则的外键关系
        DetachedCriteria menuCustomRuleDc = DetachedCriteria.forClass(WeiXinMenuCustomRuleEntity.class);
        menuCustomRuleDc.add(Restrictions.eq("groupWechat.id", id))
                .createAlias("groupWechat", "groupWechat", JoinType.LEFT_OUTER_JOIN);
        List<WeiXinMenuCustomRuleEntity> mcRuleList = weiXinMenuCustomRuleRepository.findAll(menuCustomRuleDc);
        for (WeiXinMenuCustomRuleEntity mcRule : mcRuleList) {
            mcRule.setGroupWechat(null);
            weiXinMenuCustomRuleRepository.update(mcRule);
        }

        // 删除数据库中的记录
        weiXinGroupRepository.delete(entity);
    }

    @Override
    public void sync() throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 获取微信分组数据并同步到数据库中
        List<GroupEntity> groupEntities =
                WeiXinGroupsGetRequest.request(weiXinAccountService.getAccessToken(accountEntity.getAppId()));
        for (GroupEntity group : groupEntities) {
            WeiXinGroupWechatEntity groupWechatEntity = weiXinGroupWechatRepository.findByGroupId(group.getId());
            if (null != groupWechatEntity) {
                if (!groupWechatEntity.getName().equals(group.getName())
                        || !groupWechatEntity.getCount().equals(group.getCount())) {
                    groupWechatEntity.setName(group.getName());
                    groupWechatEntity.setCount(group.getCount());
                    weiXinGroupWechatRepository.update(groupWechatEntity);
                }
            } else {
                WeiXinGroupWechatEntity entity = new WeiXinGroupWechatEntity(
                        group.getName(),
                        group.getId(),
                        group.getCount(),
                        null,
                        new Date(),
                        accountEntity.getId()
                );
                weiXinGroupWechatRepository.save(entity);
            }
        }
    }

}
