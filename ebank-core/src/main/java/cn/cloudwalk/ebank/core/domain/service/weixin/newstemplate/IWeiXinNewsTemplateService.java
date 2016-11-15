package cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.command.WeiXinNewsTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.command.WeiXinNewsTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public interface IWeiXinNewsTemplateService {

    Pagination<WeiXinNewsTemplateEntity> pagination(WeiXinNewsTemplatePaginationCommand command);

    WeiXinNewsTemplateEntity findById(String id);

    WeiXinNewsTemplateEntity save(WeiXinNewsTemplateCommand command);

    WeiXinNewsTemplateEntity update(WeiXinNewsTemplateCommand command);

    void delete(String id);

}
