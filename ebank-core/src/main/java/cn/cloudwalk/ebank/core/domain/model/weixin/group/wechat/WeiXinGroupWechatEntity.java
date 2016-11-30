package cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntityType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/23.
 */
@Entity
@Table(name = "weixin_group_wechat",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "account_id"}))
public class WeiXinGroupWechatEntity extends WeiXinGroupEntity {

    public WeiXinGroupWechatEntity() {
        super();
    }

    public WeiXinGroupWechatEntity(String name, Integer groupId, Integer count, String remark,
                                   Date createdDate, String accountId) {
        super(name, groupId, count, remark, WeiXinGroupEntityType.WECHAT, createdDate, accountId);
    }
}
