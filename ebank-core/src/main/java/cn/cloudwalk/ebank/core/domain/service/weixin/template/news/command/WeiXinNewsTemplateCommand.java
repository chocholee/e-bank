package cn.cloudwalk.ebank.core.domain.service.weixin.template.news.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by liwenhe on 16/11/14.
 */
public class WeiXinNewsTemplateCommand extends AbstractCommand {

    private String id;

    @NotBlank(message = "{WeiXinNewsTemplateCommand.name.NotBlank}")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
