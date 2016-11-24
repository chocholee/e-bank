package cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liwenhe on 16/11/24.
 */
public class WeiXinQRCodeCommand extends AbstractCommand {

    private String                      id;

    @NotBlank(message = "{WeiXinQRCodeCommand.name.NotBlank}")
    private String                      name;

    private Integer                     expireSeconds;

    @NotNull(message = "{WeiXinQRCodeCommand.type.NotNull}")
    private WeiXinQRCodeEntityType      type;

    @NotBlank(message = "{WeiXinQRCodeCommand.sceneId.NotBlank}")
    private String                      sceneId;

    private String                      channelId;

    @NotBlank(message = "{WeiXinQRCodeCommand.groupWechatId.NotBlank}")
    private String                      groupWechatId;

    private String                      groupVirtualId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public WeiXinQRCodeEntityType getType() {
        return type;
    }

    public String getSceneId() {
        return sceneId;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getGroupWechatId() {
        return groupWechatId;
    }

    public String getGroupVirtualId() {
        return groupVirtualId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public void setType(WeiXinQRCodeEntityType type) {
        this.type = type;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setGroupWechatId(String groupWechatId) {
        this.groupWechatId = groupWechatId;
    }

    public void setGroupVirtualId(String groupVirtualId) {
        this.groupVirtualId = groupVirtualId;
    }
}
