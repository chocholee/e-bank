package cn.cloudwalk.ebank.core.domain.model.weixin.template.news;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    private String              accountId;              // 关联公众号

    public WeiXinNewsTemplateEntity() {
        super();
    }

    public WeiXinNewsTemplateEntity(String name, Date createdDate, String accountId) {
        this();
        this.name = name;
        this.createdDate = createdDate;
        this.accountId = accountId;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "account_id", nullable = false)
    public String getAccountId() {
        return accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
