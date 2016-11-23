package cn.cloudwalk.ebank.core.domain.service.weixin.group.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 16/11/23.
 */
public class WeiXinGroupPaginationCommand extends AbstractPaginationCommand {

    private String name;

    private WeiXinGroupEntityType type;

    public String getName() {
        return name;
    }

    public WeiXinGroupEntityType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(WeiXinGroupEntityType type) {
        this.type = type;
    }

}
