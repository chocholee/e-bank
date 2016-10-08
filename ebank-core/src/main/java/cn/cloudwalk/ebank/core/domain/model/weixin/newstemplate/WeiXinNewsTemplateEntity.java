package cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "weixin_template_news")
public class WeiXinNewsTemplateEntity extends AbstractEntity {

    private String              name;                   // 图文模板名称

    private Date                createdDate;            // 创建日期

    private WeiXinAccountEntity accountEntity;          // 关联公众号

    public WeiXinNewsTemplateEntity() {
        super();
    }

    public WeiXinNewsTemplateEntity(String name, Date createdDate, WeiXinAccountEntity accountEntity) {
        this();
        this.name = name;
        this.createdDate = createdDate;
        this.accountEntity = accountEntity;
    }

    @Id
    @GenericGenerator(name = "weixin_news_template_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "weixin_news_template_entity_generator")
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

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", nullable = false)
    public WeiXinAccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAccountEntity(WeiXinAccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
