package cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntityType;
import cn.cloudwalk.ebank.core.domain.model.weixin.user.WeiXinUserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.Set;

/**
 * Created by liwenhe on 16/11/23.
 */
@Entity
@Table(name = "weixin_group_virtual",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "account_id"}))
public class WeiXinGroupVirtualEntity extends WeiXinGroupEntity {

    private Set<WeiXinUserEntity> weiXinUserEntities;

    public WeiXinGroupVirtualEntity() {
        super();
    }

    public WeiXinGroupVirtualEntity(String name, Integer groupId, Integer count, String remark,
                                    Date createdDate, String accountId) {
        super(name, groupId, count, remark, WeiXinGroupEntityType.VIRTUAL, createdDate, accountId);
    }

    @ManyToMany(mappedBy = "groupVirtualEntities")
    public Set<WeiXinUserEntity> getWeiXinUserEntities() {
        return weiXinUserEntities;
    }

    public void setWeiXinUserEntities(Set<WeiXinUserEntity> weiXinUserEntities) {
        this.weiXinUserEntities = weiXinUserEntities;
    }
}
