package cn.cloudwalk.ebank.core.domain.model.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "weixin_menu")
public class WeiXinMenuEntity extends AbstractEntity {

    private String                  name;               // 微信菜单名称

    private String                  key;                // 菜单KEY值(click等点击类型必须)

    private String                  url;                // 网页链接(view类型必须)

    private String                  templateId;         // 消息模板id

    private Integer                 order;              // 排序

    private WeiXinMenuEntityType    type;               // 菜单类型

    private WeiXinMenuEntityMsgType msgType;            // 消息类型

    private WeiXinMenuEntity        parent;             // 关联自身

    private WeiXinAccountEntity     accountEntity;      // 关联公众号

    public WeiXinMenuEntity() {
        super();
    }

    public WeiXinMenuEntity(String name, String key, String url, String templateId, Integer order,
                            WeiXinMenuEntityType type, WeiXinMenuEntityMsgType msgType, WeiXinMenuEntity parent,
                            WeiXinAccountEntity accountEntity) {
        this();
        this.name = name;
        this.key = key;
        this.url = url;
        this.templateId = templateId;
        this.order = order;
        this.type = type;
        this.msgType = msgType;
        this.parent = parent;
        this.accountEntity = accountEntity;
    }

    @Id
    @GenericGenerator(name = "weixin_menu_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "weixin_menu_entity_generator")
    @Override
    public String getId() {
        return super.getId();
    }

    @Version
    @Override
    public Integer getVersion() {
        return super.getVersion();
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "`key`")
    public String getKey() {
        return key;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "template_id")
    public String getTemplateId() {
        return templateId;
    }

    @Column(name = "`order`")
    public Integer getOrder() {
        return order;
    }

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    public WeiXinMenuEntityType getType() {
        return type;
    }

    @Column(name = "msg_type")
    @Enumerated(value = EnumType.STRING)
    public WeiXinMenuEntityMsgType getMsgType() {
        return msgType;
    }

    @OneToOne
    @JoinColumn(name = "parent")
    public WeiXinMenuEntity getParent() {
        return parent;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", nullable = false)
    public WeiXinAccountEntity getAccountEntity() {
        return accountEntity;
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

    public void setParent(WeiXinMenuEntity parent) {
        this.parent = parent;
    }

    public void setAccountEntity(WeiXinAccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
