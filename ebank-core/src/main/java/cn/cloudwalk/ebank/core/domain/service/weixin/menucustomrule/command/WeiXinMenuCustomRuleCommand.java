package cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntityLanguage;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntityPlatform;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntitySex;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by liwenhe on 16/11/25.
 */
public class WeiXinMenuCustomRuleCommand extends AbstractCommand {

    private String                              id;

    @NotBlank(message = "{WeiXinMenuCustomRuleCommand.name.NotBlank}")
    private String                              name;

    private String                              country;

    private String                              province;

    private String                              city;

    private String                              remark;

    private WeiXinMenuCustomRuleEntitySex       sex;

    private WeiXinMenuCustomRuleEntityPlatform  platform;

    private WeiXinMenuCustomRuleEntityLanguage  language;

    @NotBlank(message = "{WeiXinMenuCustomRuleCommand.menuCustomId.NotBlank}")
    private String                              menuCustomId;

    private String                              groupWechatId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getRemark() {
        return remark;
    }

    public WeiXinMenuCustomRuleEntitySex getSex() {
        return sex;
    }

    public WeiXinMenuCustomRuleEntityPlatform getPlatform() {
        return platform;
    }

    public WeiXinMenuCustomRuleEntityLanguage getLanguage() {
        return language;
    }

    public String getMenuCustomId() {
        return menuCustomId;
    }

    public String getGroupWechatId() {
        return groupWechatId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setSex(WeiXinMenuCustomRuleEntitySex sex) {
        this.sex = sex;
    }

    public void setPlatform(WeiXinMenuCustomRuleEntityPlatform platform) {
        this.platform = platform;
    }

    public void setLanguage(WeiXinMenuCustomRuleEntityLanguage language) {
        this.language = language;
    }

    public void setMenuCustomId(String menuCustomId) {
        this.menuCustomId = menuCustomId;
    }

    public void setGroupWechatId(String groupWechatId) {
        this.groupWechatId = groupWechatId;
    }
}
