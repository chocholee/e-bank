package cn.cloudwalk.ebank.core.domain.model.weixin.user;

import cn.cloudwalk.ebank.core.domain.model.weixin.channel.WeiXinChannelEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by liwenhe on 16/11/29.
 */
@Entity
@Table(name = "weixin_user")
public class WeiXinUserEntity extends AbstractEntity {

    private String                          nickname;                   // 微信昵称

    private String                          mobileNumber;               // 手机号码

    private String                          openId;                     // 微信用户openId

    private String                          country;                    // 国家

    private String                          city;                       // 城市

    private String                          province;                   // 省份

    private String                          language;                   // 语言

    private String                          headImgUrl;                 // 头像

    private String                          remark;                     // 备注

    private WeiXinUserEntitySex             sex;                        // 性别

    private WeiXinUserEntitySubscribeType   subscribeType;              // 订阅

    private WeiXinGroupWechatEntity         groupWechat;                // 微信分组

    private List<WeiXinGroupVirtualEntity>  groupVirtualEntities;       // 虚拟分组

    private WeiXinChannelEntity             channel;                    // 渠道

    private String                          accountId;                  // 关联公众号

    public WeiXinUserEntity() {
        super();
    }

    public WeiXinUserEntity(String nickname, String mobileNumber, String openId, String country, String city,
                            String province, String language, String headImgUrl, String remark, WeiXinUserEntitySex sex,
                            WeiXinUserEntitySubscribeType subscribeType, WeiXinGroupWechatEntity groupWechat,
                            List<WeiXinGroupVirtualEntity> groupVirtualEntities, WeiXinChannelEntity channel, String accountId) {
        super();
        this.nickname = nickname;
        this.mobileNumber = mobileNumber;
        this.openId = openId;
        this.country = country;
        this.city = city;
        this.province = province;
        this.language = language;
        this.headImgUrl = headImgUrl;
        this.remark = remark;
        this.sex = sex;
        this.subscribeType = subscribeType;
        this.groupWechat = groupWechat;
        this.groupVirtualEntities = groupVirtualEntities;
        this.channel = channel;
        this.accountId = accountId;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @Column(name = "mobile_number")
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    @Column(name = "head_img_url")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    public WeiXinUserEntitySex getSex() {
        return sex;
    }

    @Column(name = "subscribe_type")
    @Enumerated(EnumType.STRING)
    public WeiXinUserEntitySubscribeType getSubscribeType() {
        return subscribeType;
    }

    @ManyToOne
    @JoinColumn(name = "group_wechat_id", referencedColumnName = "id")
    public WeiXinGroupWechatEntity getGroupWechat() {
        return groupWechat;
    }

    @ManyToMany
    @JoinTable(name = "weixin_user_group_virtual",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")})
    public List<WeiXinGroupVirtualEntity> getGroupVirtualEntities() {
        return groupVirtualEntities;
    }

    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    public WeiXinChannelEntity getChannel() {
        return channel;
    }

    @Column(name = "account_id", nullable = false)
    public String getAccountId() {
        return accountId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSex(WeiXinUserEntitySex sex) {
        this.sex = sex;
    }

    public void setSubscribeType(WeiXinUserEntitySubscribeType subscribeType) {
        this.subscribeType = subscribeType;
    }

    public void setGroupWechat(WeiXinGroupWechatEntity groupWechat) {
        this.groupWechat = groupWechat;
    }

    public void setGroupVirtualEntities(List<WeiXinGroupVirtualEntity> groupVirtualEntities) {
        this.groupVirtualEntities = groupVirtualEntities;
    }

    public void setChannel(WeiXinChannelEntity channel) {
        this.channel = channel;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
