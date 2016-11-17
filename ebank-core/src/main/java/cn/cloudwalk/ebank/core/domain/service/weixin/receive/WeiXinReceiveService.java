package cn.cloudwalk.ebank.core.domain.service.weixin.receive;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.receive.WeiXinReceiveEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.receive.command.WeiXinReceiveCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.receive.command.WeiXinReceivePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.receive.IWeiXinReceiveRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import com.arm4j.weixin.exception.WeiXinRequestException;
import com.arm4j.weixin.request.customservice.WeiXinCustomServiceSendMessageRequest;
import com.arm4j.weixin.request.customservice.entity.msg.text.KFAccountTextMsgEntity;
import com.arm4j.weixin.request.customservice.entity.msg.text.TextMsgEntity;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
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
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
@Service("weiXinReceiveService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinReceiveService implements IWeiXinReceiveService {

    @Autowired
    private IWeiXinReceiveRepository<WeiXinReceiveEntity, String> weiXinReceiveRepository;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinReceiveEntity> pagination(WeiXinReceivePaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getNickname())) {
                criterions.add(Restrictions.like("nickname", command.getNickname(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(command.getContent())) {
                criterions.add(Restrictions.like("content", command.getContent(), MatchMode.ANYWHERE));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinReceiveRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinReceiveEntity save(WeiXinReceiveCommand command) {
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByAccountId(command.getToUserName());
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinMenuService.WeiXinNotFoundException"));
        }

        WeiXinReceiveEntity entity = new WeiXinReceiveEntity(
                command.getMsgId(),
                command.getMsgType(),
                command.getContent(),
                command.getFromUserName(),
                command.getToUserName(),
                command.getNickname(),
                null,
                false,
                new Date(),
                accountEntity.getId()
        );

        weiXinReceiveRepository.save(entity);
        return entity;
    }

    @Override
    public void reply(String id, String content) throws WeiXinRequestException {
        // 更新数据
        WeiXinReceiveEntity entity = weiXinReceiveRepository.getById(id);
        entity.setResponse(content);
        entity.setIsResponse(true);
        weiXinReceiveRepository.update(entity);


        // 回复信息给用户
        TextMsgEntity textMsgEntity = new TextMsgEntity();
        textMsgEntity.setContent(content);

        KFAccountTextMsgEntity kfAccountTextMsgEntity = new KFAccountTextMsgEntity();
        kfAccountTextMsgEntity.setToUser(entity.getFromUserName());
        kfAccountTextMsgEntity.setText(textMsgEntity);

        WeiXinAccountEntity accountEntity = weiXinAccountService.findById(entity.getAccountId());
        WeiXinCustomServiceSendMessageRequest.request(
                weiXinAccountService.getAccessToken(accountEntity.getAppId()),
                kfAccountTextMsgEntity);
    }

    @Override
    public void delete(String id) {
        WeiXinReceiveEntity entity = weiXinReceiveRepository.getById(id);
        weiXinReceiveRepository.delete(entity);
    }

}
