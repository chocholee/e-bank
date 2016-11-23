package cn.cloudwalk.ebank.core.domain.model.weixin.menucustom;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/22.
 */
@Entity
@Table(name = "weixin_menu_custom",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "account_id"}))
public class WeiXinMenuCustomEntity extends AbstractEntity {

    private String  name;                   // 自定义菜单名称

    private String  remark;                 // 备注

    private Date    createdDate;            // 创建日期

    private String  accountId;              // 关联公众号

    public WeiXinMenuCustomEntity() {
        super();
    }

    public WeiXinMenuCustomEntity(String name, String remark, Date createdDate, String accountId) {
        this();
        this.name = name;
        this.remark = remark;
        this.createdDate = createdDate;
        this.accountId = accountId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
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

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
