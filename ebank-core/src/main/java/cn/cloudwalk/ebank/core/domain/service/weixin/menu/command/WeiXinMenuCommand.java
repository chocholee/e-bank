package cn.cloudwalk.ebank.core.domain.service.weixin.menu.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntityMsgType;
import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public class WeiXinMenuCommand extends AbstractCommand {

    private String                  id;

    private String                  name;               // 微信菜单名称

    private String                  key;                // 菜单KEY值(click等点击类型必须)

    private String                  url;                // 网页链接(view类型必须)

    private String                  templateId;         // 消息模板id

    private Integer                 order;              // 排序

    private WeiXinMenuEntityType    type;               // 菜单类型

    private WeiXinMenuEntityMsgType msgType;            // 消息类型

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

    public String getTemplateId() {
        return templateId;
    }

    public Integer getOrder() {
        return order;
    }

    public WeiXinMenuEntityType getType() {
        return type;
    }

    public WeiXinMenuEntityMsgType getMsgType() {
        return msgType;
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

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setType(WeiXinMenuEntityType type) {
        this.type = type;
    }

    public void setMsgType(WeiXinMenuEntityMsgType msgType) {
        this.msgType = msgType;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
