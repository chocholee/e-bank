package cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinTextTemplatePaginationCommand extends AbstractPaginationCommand {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
