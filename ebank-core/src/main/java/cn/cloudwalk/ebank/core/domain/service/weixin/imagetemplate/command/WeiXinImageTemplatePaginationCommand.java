package cn.cloudwalk.ebank.core.domain.service.weixin.imagetemplate.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 16/11/15.
 */
public class WeiXinImageTemplatePaginationCommand extends AbstractPaginationCommand {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
