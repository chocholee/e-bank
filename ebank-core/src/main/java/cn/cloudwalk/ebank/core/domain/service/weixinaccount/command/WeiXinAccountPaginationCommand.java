package cn.cloudwalk.ebank.core.domain.service.weixinaccount.command;

import cn.cloudwalk.ebank.core.domain.model.weixinaccount.WeiXinAccountEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
public class WeiXinAccountPaginationCommand extends AbstractPaginationCommand {

    private String name;

    private String appId;

    private WeiXinAccountEntityType type;

    public String getName() {
        return name;
    }

    public String getAppId() {
        return appId;
    }

    public WeiXinAccountEntityType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setType(WeiXinAccountEntityType type) {
        this.type = type;
    }
}
