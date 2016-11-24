package cn.cloudwalk.ebank.core.domain.service.weixin.scene;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.scene.command.WeiXinSceneCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.scene.command.WeiXinScenePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.qrcode.IWeiXinQRCodeRepository;
import cn.cloudwalk.ebank.core.repository.weixin.reply.IWeiXinReplyRepository;
import cn.cloudwalk.ebank.core.repository.weixin.scene.IWeiXinSceneRepository;
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
 * Created by liwenhe on 16/11/17.
 */
@Service("weiXinSceneService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinSceneService implements IWeiXinSceneService {

    @Autowired
    private IWeiXinSceneRepository<WeiXinSceneEntity, String> weiXinSceneRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private IWeiXinReplyRepository<WeiXinReplyEntity, String> weiXinReplyRepository;

    @Autowired
    private IWeiXinQRCodeRepository<WeiXinQRCodeEntity, String> weiXinQRCodeRepository;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    public Pagination<WeiXinSceneEntity> pagination(WeiXinScenePaginationCommand command) {
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

            return weiXinSceneRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinSceneEntity findById(String id) {
        return weiXinSceneRepository.getById(id);
    }

    @Override
    public WeiXinSceneEntity save(WeiXinSceneCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinSceneEntity entity = new WeiXinSceneEntity(
                command.getName(),
                command.getRemark(),
                new Date(),
                accountEntity.getId());

        weiXinSceneRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinSceneEntity update(WeiXinSceneCommand command) {
        WeiXinSceneEntity entity = this.findById(command.getId());
        entity.setName(command.getName());
        entity.setRemark(command.getRemark());
        weiXinSceneRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        WeiXinSceneEntity entity = this.findById(id);

        // 删除与回复管理间的外键关系
        List<WeiXinReplyEntity> replyEntities = weiXinReplyRepository.findBySceneId(entity.getId());
        for (WeiXinReplyEntity replyEntity : replyEntities) {
            weiXinReplyRepository.delete(replyEntity);
        }

        // 删除与二维码间的外键关系
        DetachedCriteria dc = DetachedCriteria.forClass(WeiXinQRCodeEntity.class);
        dc.add(Restrictions.eq("scene.id", id))
                .createAlias("scene", "scene", JoinType.LEFT_OUTER_JOIN);
        List<WeiXinQRCodeEntity> qrList = weiXinQRCodeRepository.findAll(dc);
        for (WeiXinQRCodeEntity qrCode : qrList) {
            weiXinQRCodeRepository.delete(qrCode);
        }

        weiXinSceneRepository.delete(entity);
    }
}
