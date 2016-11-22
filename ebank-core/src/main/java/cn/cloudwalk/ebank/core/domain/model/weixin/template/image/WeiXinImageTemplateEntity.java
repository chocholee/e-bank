package cn.cloudwalk.ebank.core.domain.model.weixin.template.image;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/15.
 */
@Entity
@Table(name = "weixin_template_image",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "account_id"}))
public class WeiXinImageTemplateEntity extends AbstractEntity {

    private String  name;                       // 名称

    private String  path;                       // 图片存放地址

    private String  mediaId;                    // 微信素材Id

    private Date    createdDate;                // 创建日期

    private String  accountId;                  // 关联公众号

    public WeiXinImageTemplateEntity() {
        super();
    }

    public WeiXinImageTemplateEntity(String name, String path, String mediaId, Date createdDate, String accountId) {
        this();
        this.name = name;
        this.path = path;
        this.mediaId = mediaId;
        this.createdDate = createdDate;
        this.accountId = accountId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    @Column(name = "media_id")
    public String getMediaId() {
        return mediaId;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
