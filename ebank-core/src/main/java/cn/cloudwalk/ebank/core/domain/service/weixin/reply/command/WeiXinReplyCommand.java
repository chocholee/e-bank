package cn.cloudwalk.ebank.core.domain.service.weixin.reply.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntityEvent;
import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liwenhe on 16/11/21.
 */
public class WeiXinReplyCommand extends AbstractCommand {

    private String                  id;

    private String                  keyword;

    @NotNull(message = "{WeiXinReplyCommand.event.NotNull}")
    private WeiXinReplyEntityEvent  event;

    @NotNull(message = "{WeiXinReplyCommand.type.NotNull}")
    private WeiXinReplyEntityType   type;

    @NotBlank(message = "{WeiXinReplyCommand.templateId.NotBlank}")
    private String                  templateId;

    private String                  sceneId;

    public String getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public WeiXinReplyEntityEvent getEvent() {
        return event;
    }

    public WeiXinReplyEntityType getType() {
        return type;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setEvent(WeiXinReplyEntityEvent event) {
        this.event = event;
    }

    public void setType(WeiXinReplyEntityType type) {
        this.type = type;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }
}
