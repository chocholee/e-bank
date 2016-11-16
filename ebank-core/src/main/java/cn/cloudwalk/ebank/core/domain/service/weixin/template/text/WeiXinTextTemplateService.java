package cn.cloudwalk.ebank.core.domain.service.weixin.template.text;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.text.WeiXinTextTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.text.command.WeiXinTextTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.text.command.WeiXinTextTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.template.text.IWeiXinTextTemplateRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
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
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Service("weiXinTextTemplateService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinTextTemplateService implements IWeiXinTextTemplateService {

    @Autowired
    private IWeiXinTextTemplateRepository<WeiXinTextTemplateEntity, String> weiXinTextTemplateRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinTextTemplateEntity> pagination(WeiXinTextTemplatePaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getName())) {
                criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinTextTemplateRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinTextTemplateEntity findById(String id) {
        return weiXinTextTemplateRepository.getById(id);
    }

    @Override
    public WeiXinTextTemplateEntity save(WeiXinTextTemplateCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinTextTemplateEntity entity = new WeiXinTextTemplateEntity(
                command.getName(),
                command.getContent(),
                new Date(),
                accountEntity.getId()
        );

        weiXinTextTemplateRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinTextTemplateEntity update(WeiXinTextTemplateCommand command) {
        WeiXinTextTemplateEntity entity = weiXinTextTemplateRepository.getById(command.getId());
        entity.setName(command.getName());
        entity.setContent(command.getContent());

        weiXinTextTemplateRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        WeiXinTextTemplateEntity entity = weiXinTextTemplateRepository.getById(id);
        weiXinTextTemplateRepository.delete(entity);
    }
}
