package cn.cloudwalk.ebank.core.support.security;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by liwenhe on 2016/9/22.
 *
 * @author 李文禾
 */
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    private final static Integer DEFAULT_LOGIN_COUNT = 5;

    @Autowired
    private IUserService userService;

    private Integer loginCount = DEFAULT_LOGIN_COUNT;

    private SaltSource saltSource;

    private PasswordEncoder passwordEncoder;

    public CustomDaoAuthenticationProvider() {
        super();
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
                                                  throws AuthenticationException {
        Object salt = null;

        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage(
                    "CustomDaoAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(),
                presentedPassword, salt)) {
            logger.debug("Authentication failed: password does not match stored value");

            // 查询用户信息并增加登录次数,如果登录次数达到规定的次数,禁用账户
            UserEntity entity = userService.findByUsername(userDetails.getUsername());
            entity = userService.increaseLoginCount(entity.getId());
            if (entity.getLoginCount() >= loginCount) {
                userService.lock(entity.getId());
                throw new DisabledException(messages.getMessage(
                        "CustomAccountStatusUserDetailsChecker.disabled",
                        "The account disabled"));
            } else {
                throw new BadCredentialsException(messages.getMessage(
                        "CustomDaoAuthenticationProvider.password", new Object[]{loginCount - entity.getLoginCount()},
                        "The password invalid"));
            }
        }
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
