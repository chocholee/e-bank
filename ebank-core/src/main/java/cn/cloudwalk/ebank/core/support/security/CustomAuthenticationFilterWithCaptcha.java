package cn.cloudwalk.ebank.core.support.security;

import cn.cloudwalk.ebank.core.support.security.exception.InvalidCaptchaException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by liwenhe on 2016/9/22.
 *
 * @author 李文禾
 */
public class CustomAuthenticationFilterWithCaptcha extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";

    private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

    public CustomAuthenticationFilterWithCaptcha() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 从session中取出captcha
        HttpSession session = request.getSession();
        String cacheCaptcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        // 获取用户输入的captcha并判断与系统生成的验证码是否一致
        String captcha = obtainCaptcha(request);
        if (!cacheCaptcha.equals(captcha)) {
            throw new InvalidCaptchaException(messages.getMessage(
                    "CustomAuthenticationFilterWithCaptcha.captcha",
                    "The captcha invalid"));
        }

        return super.attemptAuthentication(request, response);
    }

    protected String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(captchaParameter);
    }
}
