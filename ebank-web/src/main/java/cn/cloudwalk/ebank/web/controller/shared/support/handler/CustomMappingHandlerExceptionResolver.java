package cn.cloudwalk.ebank.web.controller.shared.support.handler;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class CustomMappingHandlerExceptionResolver extends SimpleMappingExceptionResolver {

    private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 记录日志
        this.doLog(request, (HandlerMethod) handler, ex);

        // 选择相应异常错误页面
        String viewName = super.determineViewName(ex, request);
        if (viewName != null) {
            // 指定http status
            Integer statusCode = super.determineStatusCode(request, viewName);
            if (statusCode != null) {
                super.applyStatusCodeIfPossible(request, response, statusCode);
            }
            // 跳转页面
            return getModelAndView(viewName, ex, request);
        }
        else {
            return null;
        }
    }

    @Override
    protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView(viewName);
        if (this.exceptionAttribute != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exposing Exception as model attribute '" + this.exceptionAttribute + "'");
            }
            HttpSession session = request.getSession();
            session.setAttribute(this.exceptionAttribute, ex.getMessage());
        }
        return mv;
    }

    @Override
    public void setExceptionAttribute(String exceptionAttribute) {
        this.exceptionAttribute = exceptionAttribute;
    }

    /**
     * 记录异常日志
     *
     * @param request
     * @param handler
     * @param ex
     */
    private void doLog(HttpServletRequest request, HandlerMethod handler, Exception ex) {

    }

}
