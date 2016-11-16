package cn.cloudwalk.ebank.core.domain.service.weixin.template.image;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.image.WeiXinImageTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.command.WeiXinImageTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.command.WeiXinImageTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.io.IOException;

/**
 * Created by liwenhe on 16/11/15.
 */
public interface IWeiXinImageTemplateService {

    Pagination<WeiXinImageTemplateEntity> pagination(WeiXinImageTemplatePaginationCommand command);

    WeiXinImageTemplateEntity findById(String id);

    WeiXinImageTemplateEntity save(WeiXinImageTemplateCommand command, String tempDir, String saveDir) throws IOException;

    WeiXinImageTemplateEntity update(WeiXinImageTemplateCommand command, String tempDir, String saveDir) throws IOException;

    void delete(String id, String saveDir);

}
