package cn.cloudwalk.ebank.core.domain.model.weixin.keyword;

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
@Table(name = "weixin_keyword")
public class WeiXinKeywordEntity extends AbstractEntity {

    private String                      keyword;            // 关键字

    private String                      templateId;         // 消息模板编号

    private String                      templateName;       // 消息模板名称

    private Date                        createdDate;        // 创建日期

    private WeiXinKeywordEntityMsgType  msgType;            // 消息类型

    private WeiXinAccountEntity         accountEntity;      // 关联公众号

    public WeiXinKeywordEntity() {
        super();
    }

    public WeiXinKeywordEntity(String keyword, String templateId, String templateName, Date createdDate,
                               WeiXinKeywordEntityMsgType msgType, WeiXinAccountEntity accountEntity) {
        this();
        this.keyword = keyword;
        this.templateId = templateId;
        this.templateName = templateName;
        this.createdDate = createdDate;
        this.msgType = msgType;
        this.accountEntity = accountEntity;
    }

    @Id
    @GenericGenerator(name = "weixin_keyword_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "weixin_keyword_entity_generator")
    @Override
    public String getId() {
        return super.getId();
    }

    @Version
    @Override
    public Integer getVersion() {
        return super.getVersion();
    }

    @Column(name = "keyword", unique = true)
    public String getKeyword() {
        return keyword;
    }

    @Column(name = "template_id")
    public String getTemplateId() {
        return templateId;
    }

    @Column(name = "template_name")
    public String getTemplateName() {
        return templateName;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "msg_type")
    @Enumerated(EnumType.STRING)
    public WeiXinKeywordEntityMsgType getMsgType() {
        return msgType;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", nullable = false)
    public WeiXinAccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setMsgType(WeiXinKeywordEntityMsgType msgType) {
        this.msgType = msgType;
    }

    public void setAccountEntity(WeiXinAccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
