package cn.cloudwalk.ebank.core.domain.service.weixin.template.news;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsItemsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.news.command.WeiXinNewsItemsTemplateCommand;

import java.io.IOException;
import java.util.List;

/**
 * Created by liwenhe on 2016/10/10.
 *
 * @author 李文禾
 */
public interface IWeiXinNewsItemsTemplateService {

    List<WeiXinNewsItemsTemplateEntity> findByNewsTemplateId(String newsTemplateId);

    WeiXinNewsItemsTemplateEntity findById(String id);

    WeiXinNewsItemsTemplateEntity save(WeiXinNewsItemsTemplateCommand command, String tempDir, String saveDir) throws IOException;

    WeiXinNewsItemsTemplateEntity update(WeiXinNewsItemsTemplateCommand command, String tempDir, String saveDir) throws IOException;

    void delete(String id, String saveDir);

}
