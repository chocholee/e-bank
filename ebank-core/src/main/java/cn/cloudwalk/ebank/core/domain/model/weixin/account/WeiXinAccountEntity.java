package cn.cloudwalk.ebank.core.domain.model.weixin.account;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "weixin_account")
public class WeiXinAccountEntity extends AbstractEntity {

    private String                  name;                   // 公众号名称

    private String                  token;                  // 公众号token

    private String                  number;                 // 公众号

    private String                  accountId;              // 公众号微信号

    private String                  appId;                  // 公众号appId

    private String                  appSecret;              // 公众号appSecret

    private String                  accessToken;            // 公众号accessToken

    private Date                    accessTokenTime;        // 公众号accessTokenTime

    private String                  jsApiTicket;            // 公众号jsApiTicket

    private Date                    jsApiTicketTime;        // 公众号jsApiTicketTime

    private String                  apiTicket;              // 公众号apiTicket

    private Date                    apiTicketTime;          // 公众号apiTicketTime

    private String                  email;                  // 公众号email

    private String                  description;            // 公众号描述

    private Date                    createdDate;            // 创建日期

    private WeiXinAccountEntityType type;                   // 公众号类型

    private UserEntity              user;                   // 关联用户

    public WeiXinAccountEntity() {
        super();
    }

    public WeiXinAccountEntity(String name, String token, String number, String accountId, String appId,
                               String appSecret, String email, String description, Date createdDate,
                               WeiXinAccountEntityType type, UserEntity user) {
        this();
        this.name = name;
        this.token = token;
        this.number = number;
        this.accountId = accountId;
        this.appId = appId;
        this.appSecret = appSecret;
        this.email = email;
        this.description = description;
        this.createdDate = createdDate;
        this.type = type;
        this.user = user;
    }

    @Id
    @GenericGenerator(name = "weixin_account_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "weixin_account_entity_generator")
    @Override
    public String getId() {
        return super.getId();
    }

    @Version
    @Override
    public Integer getVersion() {
        return super.getVersion();
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "token", nullable = false)
    public String getToken() {
        return token;
    }

    @Column(name = "number", nullable = false)
    public String getNumber() {
        return number;
    }

    @Column(name = "account_id", nullable = false)
    public String getAccountId() {
        return accountId;
    }

    @Column(name = "app_id", nullable = false)
    public String getAppId() {
        return appId;
    }

    @Column(name = "app_secret", nullable = false)
    public String getAppSecret() {
        return appSecret;
    }

    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @Column(name = "access_token_time")
    public Date getAccessTokenTime() {
        return accessTokenTime;
    }

    @Column(name = "js_api_ticket")
    public String getJsApiTicket() {
        return jsApiTicket;
    }

    @Column(name = "js_api_ticket_time")
    public Date getJsApiTicketTime() {
        return jsApiTicketTime;
    }

    @Column(name = "api_ticket")
    public String getApiTicket() {
        return apiTicket;
    }

    @Column(name = "api_ticket_time")
    public Date getApiTicketTime() {
        return apiTicketTime;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    public WeiXinAccountEntityType getType() {
        return type;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    public UserEntity getUser() {
        return user;
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

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessTokenTime(Date accessTokenTime) {
        this.accessTokenTime = accessTokenTime;
    }

    public void setJsApiTicket(String jsApiTicket) {
        this.jsApiTicket = jsApiTicket;
    }

    public void setJsApiTicketTime(Date jsApiTicketTime) {
        this.jsApiTicketTime = jsApiTicketTime;
    }

    public void setApiTicket(String apiTicket) {
        this.apiTicket = apiTicket;
    }

    public void setApiTicketTime(Date apiTicketTime) {
        this.apiTicketTime = apiTicketTime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setType(WeiXinAccountEntityType type) {
        this.type = type;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
