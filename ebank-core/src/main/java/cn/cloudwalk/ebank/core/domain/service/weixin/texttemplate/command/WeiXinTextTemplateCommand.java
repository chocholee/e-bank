package cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinTextTemplateCommand extends AbstractCommand {

    private String id;

    private String name;                   // 文本消息模板名称

    private String content;                // 文本消息模板内容

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
