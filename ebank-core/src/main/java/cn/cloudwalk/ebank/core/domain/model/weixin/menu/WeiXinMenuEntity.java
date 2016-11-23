package cn.cloudwalk.ebank.core.domain.model.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

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

    private String                  mediaId;            // 微信永久素材id

    private Integer                 order;              // 排序

    private WeiXinMenuEntityType    type;               // 菜单类型

    private WeiXinMenuEntity        parent;             // 关联自身

    private WeiXinMenuCustomEntity  menuCustom;         // 关联个性化菜单

    private String                  accountId;          // 关联公众号

    public WeiXinMenuEntity() {
        super();
    }

    public WeiXinMenuEntity(String name, String key, String url, String mediaId, Integer order,
                            WeiXinMenuEntityType type, WeiXinMenuEntity parent, WeiXinMenuCustomEntity menuCustom,
                            String accountId) {
        this();
        this.name = name;
        this.key = key;
        this.url = url;
        this.mediaId = mediaId;
        this.order = order;
        this.type = type;
        this.parent = parent;
        this.menuCustom = menuCustom;
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

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "media_id")
    public String getMediaId() {
        return mediaId;
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

    @OneToOne
    @JoinColumn(name = "parent")
    public WeiXinMenuEntity getParent() {
        return parent;
    }

    @ManyToOne
    @JoinColumn(name = "menu_custom_id", referencedColumnName = "id")
    public WeiXinMenuCustomEntity getMenuCustom() {
        return menuCustom;
    }

    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
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

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setType(WeiXinMenuEntityType type) {
        this.type = type;
    }

    public void setParent(WeiXinMenuEntity parent) {
        this.parent = parent;
    }

    public void setMenuCustom(WeiXinMenuCustomEntity menuCustom) {
        this.menuCustom = menuCustom;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
