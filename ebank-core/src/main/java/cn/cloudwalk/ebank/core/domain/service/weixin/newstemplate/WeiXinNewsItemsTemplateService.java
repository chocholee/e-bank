package cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsItemsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.command.WeiXinNewsItemsTemplateCommand;
import cn.cloudwalk.ebank.core.repository.weixin.template.news.IWeiXinNewsItemsTemplateRepository;
import cn.cloudwalk.ebank.core.repository.weixin.template.news.IWeiXinNewsTemplateRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by liwenhe on 2016/10/10.
 *
 * @author 李文禾
 */
@Service("weiXinNewsItemsTemplateService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinNewsItemsTemplateService implements IWeiXinNewsItemsTemplateService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinNewsItemsTemplateRepository<WeiXinNewsItemsTemplateEntity, String> weiXinNewsItemsTemplateRepository;

    @Autowired
    private IWeiXinNewsTemplateRepository<WeiXinNewsTemplateEntity, String> weiXinNewsTemplateRepository;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    public List<WeiXinNewsItemsTemplateEntity> findByNewsTemplateId(String newsTemplateId) {
        return weiXinNewsItemsTemplateRepository.findByNewsTemplateId(newsTemplateId);
    }

    @Override
    public WeiXinNewsItemsTemplateEntity findById(String id) {
        return weiXinNewsItemsTemplateRepository.getById(id);
    }

    @Override
    public WeiXinNewsItemsTemplateEntity save(WeiXinNewsItemsTemplateCommand command, String tempDir, String saveDir) throws IOException {
        WeiXinNewsTemplateEntity newsTemplateEntity = weiXinNewsTemplateRepository.getById(command.getNewsTemplateId());
        if (null != newsTemplateEntity) {
            WeiXinNewsItemsTemplateEntity entity = new WeiXinNewsItemsTemplateEntity(
                    command.getTitle(),
                    command.getAuthor(),
                    command.getPicUrl(),
                    command.getDescription(),
                    command.getUrl(),
                    command.getOrder(),
                    new Date(),
                    newsTemplateEntity
            );
            weiXinNewsItemsTemplateRepository.save(entity);

            // 保存图片
            File src = new File(tempDir.concat(File.separator).concat(command.getPicUrl()));
            File dst = new File(saveDir.concat(File.separator).concat(command.getPicUrl()));
            CustomUploadUtil.move(src, dst);

            return entity;
        } else {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinNewsTemplateNotFoundException.message"));
        }
    }

    @Override
    public WeiXinNewsItemsTemplateEntity update(WeiXinNewsItemsTemplateCommand command, String tempDir, String saveDir) throws IOException {
        WeiXinNewsItemsTemplateEntity entity = this.findById(command.getId());

        if (!command.getPicUrl().equals(entity.getPicUrl())) {
            try {
                // 删除图片
                File origin = new File(saveDir.concat(File.separator).concat(entity.getPicUrl()));
                CustomUploadUtil.delete(origin);
            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }

            // 更新新上传的图片
            File src = new File(tempDir.concat(File.separator).concat(command.getPicUrl()));
            File dst = new File(saveDir.concat(File.separator).concat(command.getPicUrl()));
            CustomUploadUtil.move(src, dst);
            entity.setPicUrl(command.getPicUrl());
        }

        entity.setTitle(command.getTitle());
        entity.setAuthor(command.getAuthor());
        entity.setDescription(command.getDescription());
        entity.setUrl(command.getUrl());
        entity.setOrder(command.getOrder());
        weiXinNewsItemsTemplateRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id, String saveDir) {
        WeiXinNewsItemsTemplateEntity entity = this.findById(id);
        entity.setNewsTemplateEntity(null);
        weiXinNewsItemsTemplateRepository.update(entity);
        weiXinNewsItemsTemplateRepository.delete(entity);

        try {
            // 删除图片
            File origin = new File(saveDir.concat(File.separator).concat(entity.getPicUrl()));
            CustomUploadUtil.delete(origin);
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
    }
}
