package cn.cloudwalk.ebank.core.domain.service.weixin.template.image;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.image.WeiXinImageTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.command.WeiXinImageTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.command.WeiXinImageTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.template.image.IWeiXinImageTemplateRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import cn.cloudwalk.ebank.core.support.utils.CustomUploadUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by liwenhe on 16/11/15.
 */
@Service("weiXinImageTemplateService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinImageTemplateService implements IWeiXinImageTemplateService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinImageTemplateRepository<WeiXinImageTemplateEntity, String> weiXinImageTemplateRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinImageTemplateEntity> pagination(WeiXinImageTemplatePaginationCommand command) {
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

            return weiXinImageTemplateRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinImageTemplateEntity findById(String id) {
        return weiXinImageTemplateRepository.getById(id);
    }

    @Override
    public WeiXinImageTemplateEntity save(WeiXinImageTemplateCommand command, String tempDir, String saveDir) throws IOException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinImageTemplateEntity entity = new WeiXinImageTemplateEntity(
                command.getName(),
                command.getPath(),
                command.getPath(),
                new Date(),
                accountEntity.getId()
        );

        weiXinImageTemplateRepository.save(entity);

        // 保存图片
        File src = new File(tempDir.concat(File.separator).concat(command.getPath()));
        File dst = new File(saveDir.concat(File.separator).concat(command.getPath()));
        CustomUploadUtil.move(src, dst);

        return entity;
    }

    @Override
    public WeiXinImageTemplateEntity update(WeiXinImageTemplateCommand command, String tempDir, String saveDir) throws IOException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinImageTemplateEntity entity = this.findById(command.getId());
        entity.setName(command.getName());

        if (!command.getPath().equals(entity.getPath())) {
            try {
                // 删除图片
                File origin = new File(saveDir.concat(File.separator).concat(entity.getPath()));
                CustomUploadUtil.delete(origin);
            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }

            // 更新新上传的图片
            File src = new File(tempDir.concat(File.separator).concat(command.getPath()));
            File dst = new File(saveDir.concat(File.separator).concat(command.getPath()));
            CustomUploadUtil.move(src, dst);
            entity.setPath(command.getPath());
        }

        weiXinImageTemplateRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id, String saveDir) {
        WeiXinImageTemplateEntity entity = this.findById(id);
        weiXinImageTemplateRepository.delete(entity);

        try {
            // 删除图片
            File origin = new File(saveDir.concat(File.separator).concat(entity.getPath()));
            CustomUploadUtil.delete(origin);
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
    }

}
