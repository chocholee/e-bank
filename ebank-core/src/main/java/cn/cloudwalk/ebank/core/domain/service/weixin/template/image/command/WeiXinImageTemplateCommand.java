package cn.cloudwalk.ebank.core.domain.service.weixin.template.image.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by liwenhe on 16/11/15.
 */
public class WeiXinImageTemplateCommand extends AbstractCommand {

    private String  id;

    @NotBlank(message = "{WeiXinImageTemplateCommand.name.NotBlank}")
    private String  name;                       // 名称

    @NotBlank(message = "{WeiXinImageTemplateCommand.path.NotBlank}")
    private String  path;                       // 图片存放地址

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
