package cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.text.WeiXinTextTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate.command.WeiXinTextTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate.command.WeiXinTextTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public interface IWeiXinTextTemplateService {

    Pagination<WeiXinTextTemplateEntity> pagination(WeiXinTextTemplatePaginationCommand command);

    WeiXinTextTemplateEntity findById(String id);

    WeiXinTextTemplateEntity save(WeiXinTextTemplateCommand command);

    WeiXinTextTemplateEntity update(WeiXinTextTemplateCommand command);

    void delete(String id);

}
