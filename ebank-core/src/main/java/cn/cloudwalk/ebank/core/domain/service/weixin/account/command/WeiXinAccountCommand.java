package cn.cloudwalk.ebank.core.domain.service.weixin.account.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
public class WeiXinAccountCommand extends AbstractCommand {

    private String id;

    @NotBlank(message = "{WeiXinAccountCommand.name.NotBlank}")
    private String name;                   // 公众号名称

    @NotBlank(message = "{WeiXinAccountCommand.token.NotBlank}")
    private String token;                  // 公众号token

    @NotBlank(message = "{WeiXinAccountCommand.number.NotBlank}")
    private String number;                 // 公众号

    @NotBlank(message = "{WeiXinAccountCommand.accountId.NotBlank}")
    private String accountId;              // 公众号微信号

    @NotBlank(message = "{WeiXinAccountCommand.appId.NotBlank}")
    private String appId;                  // 公众号appId

    @NotBlank(message = "{WeiXinAccountCommand.appSecret.NotBlank}")
    private String appSecret;              // 公众号appSecret

    private String email;                  // 公众号email

    private String description;            // 公众号描述

    private String userId;                 // 关联用户

    @NotNull(message = "{WeiXinAccountCommand.type.NotNull}")
    private WeiXinAccountEntityType type;  // 公众号类型

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getNumber() {
        return number;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }

    public WeiXinAccountEntityType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setType(WeiXinAccountEntityType type) {
        this.type = type;
    }
}
