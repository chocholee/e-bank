package cn.cloudwalk.ebank.core.support.security;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by liwenhe on 2016/9/22.
 *
 * @author 李文禾
 */
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IFunctionService functionService;

    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new HashMap<>();

    @PostConstruct
    public void init() {
        // 初始化数据,为后面决策管理器提供数据
        // 数据存储形式: <路径地址, 角色集>
        // 通过数据查询资源集合
        List<FunctionEntity> functionEntities = functionService.findAll();
        for (FunctionEntity functionEntity : functionEntities) {
            // 得到角色集合并放入configAttributes中
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            for (RoleEntity roleEntity : functionEntity.getRoleEntities()) {
                configAttributes.add(new SecurityConfig(roleEntity.getName()));
            }
            // 添加一个路径并添加该路径的角色集
            if (!StringUtils.isEmpty(functionEntity.getUri()))
                requestMap.put(new AntPathRequestMatcher(functionEntity.getUri()), configAttributes);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
