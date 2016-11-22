package cn.cloudwalk.ebank.core.domain.service.weixin.menu.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public class WeiXinMenuPaginationCommand extends AbstractPaginationCommand {

    private String key;

    private String name;

    private WeiXinMenuEntityType type;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public WeiXinMenuEntityType getType() {
        return type;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(WeiXinMenuEntityType type) {
        this.type = type;
    }

}
