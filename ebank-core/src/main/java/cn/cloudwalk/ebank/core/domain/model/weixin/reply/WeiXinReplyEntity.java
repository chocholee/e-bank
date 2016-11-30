package cn.cloudwalk.ebank.core.domain.model.weixin.reply;

import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/21.
 */
@Entity
@Table(name = "weixin_reply")
public class WeiXinReplyEntity extends AbstractEntity {

    private String                  keyword;                // 关键字

    private WeiXinReplyEntityEvent  event;                  // 事件类型

    private WeiXinReplyEntityType   type;                   // 消息类型

    private WeiXinSceneEntity       scene;                  // 关联场景

    private String                  templateId;             // 关联模板消息

    private String                  accountId;              // 关联公众号

    private Date                    createdDate;            // 创建日期

    public WeiXinReplyEntity() {
        super();
    }

    public WeiXinReplyEntity(String keyword, WeiXinReplyEntityEvent event, WeiXinReplyEntityType type,
                             WeiXinSceneEntity scene, String templateId, String accountId, Date createdDate) {
        this();
        this.keyword = keyword;
        this.event = event;
        this.type = type;
        this.scene = scene;
        this.templateId = templateId;
        this.accountId = accountId;
        this.createdDate = createdDate;
    }

    @Column(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    @Column(name = "event")
    @Enumerated(EnumType.STRING)
    public WeiXinReplyEntityEvent getEvent() {
        return event;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public WeiXinReplyEntityType getType() {
        return type;
    }

    @ManyToOne
    @JoinColumn(name = "scene_id", referencedColumnName = "id")
    public WeiXinSceneEntity getScene() {
        return scene;
    }

    @Column(name = "template_id")
    public String getTemplateId() {
        return templateId;
    }

    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
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

    public void setScene(WeiXinSceneEntity scene) {
        this.scene = scene;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
