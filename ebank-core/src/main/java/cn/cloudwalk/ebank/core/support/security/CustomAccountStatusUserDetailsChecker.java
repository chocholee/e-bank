package cn.cloudwalk.ebank.core.support.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public class CustomAccountStatusUserDetailsChecker implements UserDetailsChecker {

    @Autowired
    private MessageSourceAccessor messages;

    @Override
    public void check(UserDetails user) {
        // 检查登录用户状态
        if (!user.isAccountNonLocked()) {
            throw new LockedException(messages.getMessage(
                    "CustomAccountStatusUserDetailsChecker.locked", "User account is locked"));
        }

        if (!user.isEnabled()) {
            throw new DisabledException(messages.getMessage(
                    "CustomAccountStatusUserDetailsChecker.disabled", "User is disabled"));
        }

        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException(
                    messages.getMessage("CustomAccountStatusUserDetailsChecker.expired",
                            "User account has expired"));
        }

        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(messages.getMessage(
                    "CustomAccountStatusUserDetailsChecker.credentialsExpired",
                    "User credentials have expired"));
        }
    }

}
