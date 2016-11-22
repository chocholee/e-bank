package cn.cloudwalk.ebank.core.support.security;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntityStatus;
import cn.cloudwalk.ebank.core.domain.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSourceAccessor messages;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户是否存在
        UserEntity entity = userService.findByUsername(username);
        if (entity == null) {
            throw new UsernameNotFoundException(messages.getMessage(
                    "CustomUserDetailsService.username",
                    "Can't find this user"));
        }

        // 判断是否启用
        boolean isEnabled = entity.getStatus() == UserEntityStatus.ENABLE;

        // 添加角色集
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity roleEntity : entity.getRoleEntities()) {
            authorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
        }

        return new CustomUserDetails(
                entity.getUsername(),
                entity.getPassword(),
                entity.getSalt(),
                isEnabled,
                true, true, true, authorities);
    }

}
