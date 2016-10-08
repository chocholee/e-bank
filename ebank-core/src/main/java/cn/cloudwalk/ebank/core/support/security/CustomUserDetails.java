package cn.cloudwalk.ebank.core.support.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public class CustomUserDetails extends User {

    private String salt;

    public CustomUserDetails(String username, String password, String salt, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.salt = salt;
    }

    public CustomUserDetails(String username, String password, String salt,
                             boolean enabled,
                             boolean accountNonExpired,
                             boolean credentialsNonExpired,
                             boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public final boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    public final boolean hasAnyRole(String... roles) {
        Set<String> roleSet = AuthorityUtils.authorityListToSet(getAuthorities());
        for (String role : roles) {
            if (roleSet.contains(role)) {
                return true;
            }
        }
        return false;
    }
}
