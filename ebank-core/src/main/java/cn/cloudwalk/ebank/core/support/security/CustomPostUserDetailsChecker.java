package cn.cloudwalk.ebank.core.support.security;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.service.user.IUserService;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserEditCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

import java.util.Date;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public class CustomPostUserDetailsChecker implements UserDetailsChecker {

    @Autowired
    private IUserService userService;

    @Override
    public void check(UserDetails user) {
        // 修改用户登录时期
        UserEntity entity = userService.findByUsername(user.getUsername());
        UserEditCommand command = new UserEditCommand();
        command.setId(entity.getId());
        command.setLastLoginDate(entity.getLoginDate());
        command.setLoginDate(new Date());
        userService.update(command);

        // 重置登录次数
        userService.resetLoginCount(entity.getId());
    }
}
