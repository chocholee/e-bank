package cn.cloudwalk.ebank.core.support.security.utils;

import cn.cloudwalk.ebank.core.support.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public class CustomSecurityContextHolderUtil {

    public static SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    public static CustomUserDetails getPrincipal() {
        return (CustomUserDetails) getSecurityContext().getAuthentication().getPrincipal();
    }

    public static boolean hasRole(String role) {
        return getPrincipal().hasRole(role);
    }

    public static boolean hasAnyRole(String... roles) {
        return getPrincipal().hasAnyRole(roles);
    }

    public static String getUsername() {
        return getPrincipal().getUsername();
    }

}
