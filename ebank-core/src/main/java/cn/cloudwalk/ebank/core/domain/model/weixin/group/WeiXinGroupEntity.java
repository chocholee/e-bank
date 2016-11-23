package cn.cloudwalk.ebank.core.domain.model.weixin.group;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/23.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class WeiXinGroupEntity extends AbstractEntity {

    private String                  name;               // 组名称

    private Integer                 groupId;            // 组编号

    private Integer                 count;              // 组人数

    private String                  remark;             // 备注

    private WeiXinGroupEntityType   type;               // 组类型

    private Date                    createdDate;        // 创建日期

    private String                  accountId;          // 关联公众号

    public WeiXinGroupEntity() {
        super();
    }

    public WeiXinGroupEntity(String name, Integer groupId, Integer count, String remark,
                             WeiXinGroupEntityType type, Date createdDate, String accountId) {
        this();
        this.name = name;
        this.groupId = groupId;
        this.count = count;
        this.remark = remark;
        this.type = type;
        this.createdDate = createdDate;
        this.accountId = accountId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public WeiXinGroupEntityType getType() {
        return type;
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

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    protected void setType(WeiXinGroupEntityType type) {
        this.type = type;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
