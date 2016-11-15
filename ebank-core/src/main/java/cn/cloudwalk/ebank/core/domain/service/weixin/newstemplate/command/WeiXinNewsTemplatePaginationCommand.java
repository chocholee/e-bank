package cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 16/11/14.
 */
public class WeiXinNewsTemplatePaginationCommand extends AbstractPaginationCommand {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
