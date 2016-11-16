package cn.cloudwalk.ebank.core.domain.service.weixin.template.news;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsItemsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.news.command.WeiXinNewsTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.news.command.WeiXinNewsTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.template.news.IWeiXinNewsItemsTemplateRepository;
import cn.cloudwalk.ebank.core.repository.weixin.template.news.IWeiXinNewsTemplateRepository;
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
@Service("weiXinNewsTemplateService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinNewsTemplateService implements IWeiXinNewsTemplateService {

    @Autowired
    private IWeiXinNewsTemplateRepository<WeiXinNewsTemplateEntity, String> weiXinNewsTemplateRepository;

    @Autowired
    private IWeiXinNewsItemsTemplateRepository<WeiXinNewsItemsTemplateEntity, String> weiXinNewsItemsTemplateRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinNewsTemplateEntity> pagination(WeiXinNewsTemplatePaginationCommand command) {
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

            return weiXinNewsTemplateRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinNewsTemplateEntity findById(String id) {
        return weiXinNewsTemplateRepository.getById(id);
    }

    @Override
    public WeiXinNewsTemplateEntity save(WeiXinNewsTemplateCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinNewsTemplateEntity entity = new WeiXinNewsTemplateEntity(
                command.getName(),
                new Date(),
                accountEntity.getId()
        );

        weiXinNewsTemplateRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinNewsTemplateEntity update(WeiXinNewsTemplateCommand command) {
        WeiXinNewsTemplateEntity entity = this.findById(command.getId());
        entity.setName(command.getName());
        weiXinNewsTemplateRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        // 查询图文模板数据
        WeiXinNewsTemplateEntity entity = this.findById(id);

        // 查找关联的子项数据并删除
        List<WeiXinNewsItemsTemplateEntity> itemsTemplateEntities =
                weiXinNewsItemsTemplateRepository.findByNewsTemplateId(entity.getId());
        for (WeiXinNewsItemsTemplateEntity item : itemsTemplateEntities) {
            weiXinNewsItemsTemplateRepository.delete(item);
        }

        // 删除图文模板数据
        weiXinNewsTemplateRepository.delete(entity);
    }

}
