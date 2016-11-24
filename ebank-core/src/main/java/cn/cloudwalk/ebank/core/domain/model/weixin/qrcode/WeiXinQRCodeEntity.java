package cn.cloudwalk.ebank.core.domain.model.weixin.qrcode;

import cn.cloudwalk.ebank.core.domain.model.weixin.channel.WeiXinChannelEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/24.
 */
@Entity
@Table(name = "weixin_qr_code",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "account_id"}))
public class WeiXinQRCodeEntity extends AbstractEntity {

    private String                      name;

    private String                      key;

    private String                      ticket;

    private String                      url;

    private Date                        expireSeconds;

    private Date                        createdDate;

    private WeiXinQRCodeEntityType      type;

    private WeiXinSceneEntity           scene;

    private WeiXinChannelEntity         channel;

    private WeiXinGroupWechatEntity     groupWechat;

    private WeiXinGroupVirtualEntity    groupVirtual;

    private String                      accountId;

    public WeiXinQRCodeEntity() {
        super();
    }

    public WeiXinQRCodeEntity(String name, String key, String ticket, String url, Date expireSeconds,
                              Date createdDate, WeiXinQRCodeEntityType type, WeiXinSceneEntity scene,
                              WeiXinChannelEntity channel, WeiXinGroupWechatEntity groupWechat,
                              WeiXinGroupVirtualEntity groupVirtual, String accountId) {
        this();
        this.name = name;
        this.key = key;
        this.ticket = ticket;
        this.url = url;
        this.expireSeconds = expireSeconds;
        this.createdDate = createdDate;
        this.type = type;
        this.scene = scene;
        this.channel = channel;
        this.groupWechat = groupWechat;
        this.groupVirtual = groupVirtual;
        this.accountId = accountId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "`key`")
    public String getKey() {
        return key;
    }

    @Column(name = "ticket")
    public String getTicket() {
        return ticket;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "expire_seconds")
    public Date getExpireSeconds() {
        return expireSeconds;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public WeiXinQRCodeEntityType getType() {
        return type;
    }

    @ManyToOne
    @JoinColumn(name = "scene_id", referencedColumnName = "id", nullable = false)
    public WeiXinSceneEntity getScene() {
        return scene;
    }

    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false)
    public WeiXinChannelEntity getChannel() {
        return channel;
    }

    @ManyToOne
    @JoinColumn(name = "group_wechat_id", referencedColumnName = "id", nullable = false)
    public WeiXinGroupWechatEntity getGroupWechat() {
        return groupWechat;
    }

    @ManyToOne
    @JoinColumn(name = "group_virtual_id", referencedColumnName = "id")
    public WeiXinGroupVirtualEntity getGroupVirtual() {
        return groupVirtual;
    }

    @Column(name = "account_id", nullable = false)
    public String getAccountId() {
        return accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setExpireSeconds(Date expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setType(WeiXinQRCodeEntityType type) {
        this.type = type;
    }

    public void setScene(WeiXinSceneEntity scene) {
        this.scene = scene;
    }

    public void setChannel(WeiXinChannelEntity channel) {
        this.channel = channel;
    }

    public void setGroupWechat(WeiXinGroupWechatEntity groupWechat) {
        this.groupWechat = groupWechat;
    }

    public void setGroupVirtual(WeiXinGroupVirtualEntity groupVirtual) {
        this.groupVirtual = groupVirtual;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
