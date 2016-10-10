package cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.texttemplate.WeiXinTextTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate.command.WeiXinTextTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate.command.WeiXinTextTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.texttemplate.IWeiXinTextTemplateRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinAccountNotFoundException;
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
    private IWeiXinAccountService weiXinAccountService;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    public Pagination<WeiXinTextTemplateEntity> pagination(WeiXinTextTemplatePaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getName())) {
                criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
            }
            if (!CustomSecurityContextHolderUtil.hasRole("administrator"))
                criterions.add(Restrictions.eq("accountEntity.id", accountEntity.getId()));

            // 添加排序
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinTextTemplateRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<WeiXinTextTemplateEntity>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinTextTemplateEntity findById(String id) {
        return weiXinTextTemplateRepository.getById(id);
    }

    @Override
    public WeiXinTextTemplateEntity save(WeiXinTextTemplateCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinAccountNotFoundException(message.getMessage("WeiXinTextTemplateService.WeiXinAccountNotFoundException"));
        }

        WeiXinTextTemplateEntity entity = new WeiXinTextTemplateEntity(
                command.getName(),
                command.getContent(),
                new Date(),
                accountEntity
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
