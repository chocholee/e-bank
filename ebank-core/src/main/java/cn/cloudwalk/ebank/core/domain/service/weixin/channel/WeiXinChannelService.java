package cn.cloudwalk.ebank.core.domain.service.weixin.channel;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.channel.WeiXinChannelEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.channel.command.WeiXinChannelCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.channel.command.WeiXinChannelPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.channel.IWeiXinChannelRepository;
import cn.cloudwalk.ebank.core.repository.weixin.qrcode.IWeiXinQRCodeRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
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
 * Created by liwenhe on 16/11/22.
 */
@Service("weiXinChannelService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinChannelService implements IWeiXinChannelService {

    @Autowired
    private IWeiXinChannelRepository<WeiXinChannelEntity, String> weiXinChannelRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private IWeiXinQRCodeRepository<WeiXinQRCodeEntity, String> weiXinQRCodeRepository;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinChannelEntity> pagination(WeiXinChannelPaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getName())) {
                criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinChannelRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinChannelEntity findById(String id) {
        return weiXinChannelRepository.getById(id);
    }

    @Override
    public WeiXinChannelEntity save(WeiXinChannelCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinChannelEntity entity = new WeiXinChannelEntity(
                command.getName(),
                command.getRemark(),
                new Date(),
                accountEntity.getId());

        weiXinChannelRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinChannelEntity update(WeiXinChannelCommand command) {
        WeiXinChannelEntity entity = this.findById(command.getId());
        entity.setName(command.getName());
        entity.setRemark(command.getRemark());
        weiXinChannelRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        WeiXinChannelEntity entity = this.findById(id);

        // 删除与二维码间的外键关系
        DetachedCriteria dc = DetachedCriteria.forClass(WeiXinQRCodeEntity.class);
        dc.add(Restrictions.eq("channel.id", id))
                .createAlias("channel", "channel", JoinType.LEFT_OUTER_JOIN);
        List<WeiXinQRCodeEntity> qrList = weiXinQRCodeRepository.findAll(dc);
        for (WeiXinQRCodeEntity qrCode : qrList) {
            weiXinQRCodeRepository.delete(qrCode);
        }

        weiXinChannelRepository.delete(entity);
    }
}
