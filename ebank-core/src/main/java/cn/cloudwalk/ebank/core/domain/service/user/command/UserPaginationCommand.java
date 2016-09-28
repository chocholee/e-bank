package cn.cloudwalk.ebank.core.domain.service.user.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public class UserPaginationCommand extends AbstractPaginationCommand {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
