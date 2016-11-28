package cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/25.
 */
@Entity
@Table(name = "weixin_menu_custom_rule",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "account_id"}))
public class WeiXinMenuCustomRuleEntity extends AbstractEntity {

    private String                              name;               // 规则名称

    private String                              menuId;             // 微信上菜单编号

    private String                              country;            // 国家

    private String                              province;           // 省份

    private String                              city;               // 城市

    private String                              remark;             // 备注

    private Date                                createdDate;        // 创建日期

    private WeiXinMenuCustomRuleEntitySex       sex;                // 性别

    private WeiXinMenuCustomRuleEntityPlatform  platform;           // 平台

    private WeiXinMenuCustomRuleEntityLanguage  language;           // 语言

    private WeiXinMenuCustomRuleEntityStatus    status;             // 同步状态

    private WeiXinMenuCustomEntity              menuCustom;         // 关联自定义菜单

    private WeiXinGroupWechatEntity             groupWechat;        // 关联微信分组

    private String                              accountId;          // 关联公众号

    public WeiXinMenuCustomRuleEntity() {
        super();
    }

    public WeiXinMenuCustomRuleEntity(String name, String menuId, String country, String province, String city, String remark,
                                      Date createdDate, WeiXinMenuCustomRuleEntitySex sex,
                                      WeiXinMenuCustomRuleEntityPlatform platform, WeiXinMenuCustomRuleEntityLanguage language,
                                      WeiXinMenuCustomRuleEntityStatus status, WeiXinMenuCustomEntity menuCustom,
                                      WeiXinGroupWechatEntity groupWechat, String accountId) {
        this();
        this.name = name;
        this.menuId = menuId;
        this.country = country;
        this.province = province;
        this.city = city;
        this.remark = remark;
        this.createdDate = createdDate;
        this.sex = sex;
        this.platform = platform;
        this.language = language;
        this.menuCustom = menuCustom;
        this.status = status;
        this.groupWechat = groupWechat;
        this.accountId = accountId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "menu_id")
    public String getMenuId() {
        return menuId;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    public WeiXinMenuCustomRuleEntitySex getSex() {
        return sex;
    }

    @Column(name = "platform")
    @Enumerated(EnumType.STRING)
    public WeiXinMenuCustomRuleEntityPlatform getPlatform() {
        return platform;
    }

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    public WeiXinMenuCustomRuleEntityLanguage getLanguage() {
        return language;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public WeiXinMenuCustomRuleEntityStatus getStatus() {
        return status;
    }

    @ManyToOne
    @JoinColumn(name = "menu_custom_id", referencedColumnName = "id", nullable = false)
    public WeiXinMenuCustomEntity getMenuCustom() {
        return menuCustom;
    }

    @ManyToOne
    @JoinColumn(name = "group_wechat_id", referencedColumnName = "id", nullable = false)
    public WeiXinGroupWechatEntity getGroupWechat() {
        return groupWechat;
    }

    @Column(name = "account_id", nullable = false)
    public String getAccountId() {
        return accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setSex(WeiXinMenuCustomRuleEntitySex sex) {
        this.sex = sex;
    }

    public void setPlatform(WeiXinMenuCustomRuleEntityPlatform platform) {
        this.platform = platform;
    }

    public void setLanguage(WeiXinMenuCustomRuleEntityLanguage language) {
        this.language = language;
    }

    public void setStatus(WeiXinMenuCustomRuleEntityStatus status) {
        this.status = status;
    }

    public void setMenuCustom(WeiXinMenuCustomEntity menuCustom) {
        this.menuCustom = menuCustom;
    }

    public void setGroupWechat(WeiXinGroupWechatEntity groupWechat) {
        this.groupWechat = groupWechat;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
