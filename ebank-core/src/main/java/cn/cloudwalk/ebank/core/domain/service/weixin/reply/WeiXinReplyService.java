package cn.cloudwalk.ebank.core.domain.service.weixin.reply;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.reply.command.WeiXinReplyCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.reply.command.WeiXinReplyPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.reply.IWeiXinReplyRepository;
import cn.cloudwalk.ebank.core.repository.weixin.scene.IWeiXinSceneRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
 * Created by liwenhe on 16/11/21.
 */
@Service("weiXinReplyService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinReplyService implements IWeiXinReplyService {

    @Autowired
    private IWeiXinReplyRepository<WeiXinReplyEntity, String> weiXinReplyRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private IWeiXinSceneRepository<WeiXinSceneEntity, String> weiXinSceneRepository;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinReplyEntity> pagination(WeiXinReplyPaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (null != command.getEvent() && !command.getEvent().isOnlyQuery()) {
                criterions.add(Restrictions.eq("event", command.getEvent()));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinReplyRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public List<WeiXinReplyEntity> findBySceneId(String sceneId) {
        return null;
    }

    @Override
    public WeiXinReplyEntity findById(String id) {
        return weiXinReplyRepository.getById(id);
    }

    @Override
    public WeiXinReplyEntity findByKeyword(String keyword, String accountId) {
        return weiXinReplyRepository.findByKeyword(keyword, accountId);
    }

    @Override
    public WeiXinReplyEntity save(WeiXinReplyCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinSceneEntity sceneEntity = null;
        if (!StringUtils.isEmpty(command.getSceneId()))
            sceneEntity = weiXinSceneRepository.getById(command.getSceneId());

        WeiXinReplyEntity entity = new WeiXinReplyEntity(
                command.getKeyword(),
                command.getEvent(),
                command.getType(),
                sceneEntity,
                command.getTemplateId(),
                accountEntity.getId(),
                new Date()
        );

        weiXinReplyRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinReplyEntity update(WeiXinReplyCommand command) {
        WeiXinSceneEntity sceneEntity = null;
        if (!StringUtils.isEmpty(command.getSceneId()))
            sceneEntity = weiXinSceneRepository.getById(command.getSceneId());

        WeiXinReplyEntity entity = this.findById(command.getId());
        entity.setKeyword(command.getKeyword());
        entity.setEvent(command.getEvent());
        entity.setType(command.getType());
        entity.setScene(sceneEntity);
        entity.setTemplateId(command.getTemplateId());
        weiXinReplyRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        WeiXinReplyEntity entity = this.findById(id);
        weiXinReplyRepository.delete(entity);
    }
}
