package cn.cloudwalk.ebank.core.domain.model.weixin.receive;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "weixin_receive")
public class WeiXinReceiveEntity extends AbstractEntity {

    private String              msgId;              // 接收消息id

    private String              msgType;            // 接收消息类型

    private String              content;            // 接收消息内容

    private String              fromUserName;       // 来自用户编号

    private String              toUserName;         // 目的用户编号

    private String              nickname;           // 来自用户的名称

    private String              response;           // 回复内容

    private Boolean             isResponse;         // 是否回复

    private Date                createdDate;        // 创建日期

    private WeiXinAccountEntity accountEntity;      // 关联公众号

    public WeiXinReceiveEntity() {
        super();
    }

    public WeiXinReceiveEntity(String msgId, String msgType, String content, String fromUserName,
                               String toUserName, String nickname, String response, boolean isResponse,
                               Date createdDate, WeiXinAccountEntity accountEntity) {
        this();
        this.msgId = msgId;
        this.msgType = msgType;
        this.content = content;
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.nickname = nickname;
        this.response = response;
        this.isResponse = isResponse;
        this.createdDate = createdDate;
        this.accountEntity = accountEntity;
    }

    @Id
    @GenericGenerator(name = "weixin_receive_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "weixin_receive_entity_generator")
    @Override
    public String getId() {
        return super.getId();
    }

    @Version
    @Override
    public Integer getVersion() {
        return super.getVersion();
    }

    @Column(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    @Column(name = "msg_type")
    public String getMsgType() {
        return msgType;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Column(name = "from_user_name")
    public String getFromUserName() {
        return fromUserName;
    }

    @Column(name = "to_user_name")
    public String getToUserName() {
        return toUserName;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @Column(name = "response")
    public String getResponse() {
        return response;
    }

    @Column(name = "is_response")
    public boolean getIsResponse() {
        return isResponse;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", nullable = false)
    public WeiXinAccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setIsResponse(Boolean response) {
        isResponse = response;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAccountEntity(WeiXinAccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
