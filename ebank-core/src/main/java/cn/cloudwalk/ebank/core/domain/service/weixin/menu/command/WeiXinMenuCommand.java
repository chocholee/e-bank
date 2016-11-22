package cn.cloudwalk.ebank.core.domain.service.weixin.menu.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public class WeiXinMenuCommand extends AbstractCommand {

    private String                  id;

    @NotBlank(message = "{WeiXinMenuCommand.name.NotBlank}")
    private String                  name;               // 微信菜单名称

    private String                  key;                // 菜单KEY值(click等点击类型必须)

    private String                  url;                // 网页链接(view类型必须)

    private String                  mediaId;            // 永久素材id

    private String                  templateId;         // 消息模板id

    @NotNull(message = "{WeiXinMenuCommand.order.NotNull}")
    private Integer                 order;              // 排序

    @NotNull(message = "{WeiXinMenuCommand.type.NotNull}")
    private WeiXinMenuEntityType    type;               // 菜单类型

    private String                  parent;             // 关联自身

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public Integer getOrder() {
        return order;
    }

    public WeiXinMenuEntityType getType() {
        return type;
    }

    public String getParent() {
        return parent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setType(WeiXinMenuEntityType type) {
        this.type = type;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
