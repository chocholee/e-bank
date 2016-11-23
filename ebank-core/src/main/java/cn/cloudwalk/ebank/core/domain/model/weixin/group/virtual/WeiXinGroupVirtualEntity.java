package cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntityType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/23.
 */
@Entity
@Table(name = "weixin_group_virtual")
public class WeiXinGroupVirtualEntity extends WeiXinGroupEntity {

    public WeiXinGroupVirtualEntity() {
        super();
    }

    public WeiXinGroupVirtualEntity(String name, Integer groupId, Integer count, String remark,
                                    Date createdDate, String accountId) {
        super(name, groupId, count, remark, WeiXinGroupEntityType.VIRTUAL, createdDate, accountId);
    }

}
