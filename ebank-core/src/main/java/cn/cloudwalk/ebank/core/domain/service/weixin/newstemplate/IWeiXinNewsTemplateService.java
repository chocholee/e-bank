package cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate.WeiXinNewsTemplateEntity;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public interface IWeiXinNewsTemplateService {

    WeiXinNewsTemplateEntity findById(String id);

}
