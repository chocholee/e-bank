package cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate.WeiXinNewsItemsTemplateEntity;

import java.util.List;

/**
 * Created by liwenhe on 2016/10/10.
 *
 * @author 李文禾
 */
public interface IWeiXinNewsItemsTemplateService {

    List<WeiXinNewsItemsTemplateEntity> findByNewsTemplateId(String newsTemplateId);

    WeiXinNewsItemsTemplateEntity findById(String id);

}
