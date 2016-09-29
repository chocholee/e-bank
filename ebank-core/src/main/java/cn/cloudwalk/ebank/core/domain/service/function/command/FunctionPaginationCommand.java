package cn.cloudwalk.ebank.core.domain.service.function.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/9/29.
 *
 * @author 李文禾
 */
public class FunctionPaginationCommand extends AbstractPaginationCommand {

    private String name;                   // 功能名称

    private String code;                   // 功能编码

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
