package cn.cloudwalk.ebank.web.controller.shared.support.interceptor;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 2016/10/14.
 *
 * @author 李文禾
 */
public class CustomMenuInterceptor extends HandlerInterceptorAdapter {

    @Value("${icon.host}")
    private String iconHost;

    @Autowired
    private IFunctionService functionService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 传入页面参数
        List<FunctionEntity> firstFuncCopyList = new ArrayList<>();
        Map<String, List<FunctionEntity>> secondFuncCopyMap = new HashMap<>();

        // 过滤重复数据判断该集合中是否存在相同值
        List<String> filterFirstFuncList = new ArrayList<>();
        List<String> filterSecondFuncList = new ArrayList<>();

        List<FunctionEntity> firstFuncList = functionService.findForFirstMenu();
        for (FunctionEntity func : firstFuncList) {
            // 过滤一级菜单
            if (!filterFirstFuncList.contains(func.getId())) {
                filterFirstFuncList.add(func.getId());
                for (RoleEntity role : func.getRoleEntities()) {
                    boolean hasRole = CustomSecurityContextHolderUtil.hasRole(role.getName());
                    if (hasRole) {
                        FunctionEntity tmpEntity = new FunctionEntity();
                        BeanUtils.copyProperties(func, tmpEntity, "parent");
                        firstFuncCopyList.add(tmpEntity);
                        break;
                    }
                }
            }

            // 过滤二级菜单
            List<FunctionEntity> secondFuncCopyList = new ArrayList<>();
            List<FunctionEntity> secondFuncList = functionService.findByParentId(func.getId());
            for (FunctionEntity secondFunc : secondFuncList) {
                if (!filterSecondFuncList.contains(secondFunc.getId())) {
                    filterSecondFuncList.add(secondFunc.getId());
                    for (RoleEntity role : secondFunc.getRoleEntities()) {
                        boolean hasRole = CustomSecurityContextHolderUtil.hasRole(role.getName());
                        if (hasRole) {
                            FunctionEntity tmpEntity = new FunctionEntity();
                            BeanUtils.copyProperties(secondFunc, tmpEntity, "roleEntities", "parent");
                            secondFuncCopyList.add(tmpEntity);
                            break;
                        }
                    }
                    secondFuncCopyMap.put(func.getId(), secondFuncCopyList);
                }
            }
        }

        modelAndView.addObject("firstFuncList", firstFuncCopyList);
        modelAndView.addObject("secondFuncMap", secondFuncCopyMap);
        modelAndView.addObject("iconHost", iconHost);
    }

}
