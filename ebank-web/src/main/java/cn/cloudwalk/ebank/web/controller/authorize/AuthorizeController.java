package cn.cloudwalk.ebank.web.controller.authorize;

import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * Created by liwenhe on 2016/9/22.
 *
 * @author 李文禾
 */
@Controller
public class AuthorizeController extends BaseController {

    @Autowired
    private Config captchaService;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set standard HTTP/1.1 no-cache headers. And return a jpeg
        resp.setHeader("Cache-Control", "no-store, no-cache");
        resp.setContentType("image/jpeg");

        // create the text for the image
        // create the image with the text
        // write the data out
        String capText = captchaService.getProducerImpl().createText();
        BufferedImage bi = captchaService.getProducerImpl().createImage(capText);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);

        // store the text in the session
        // store the date in the session so that it can be compared
        // against to make sure someone hasn't taken too long to enter
        // their kaptcha
        req.getSession().setAttribute(captchaService.getSessionKey(), capText);
        req.getSession().setAttribute(captchaService.getSessionDate(), new Date());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        try {
            // 验证是否登录,如果登录,重定向至/logined
            CustomSecurityContextHolderUtil.getPrincipal();
            return new ModelAndView("redirect:/logined");
        } catch (AccountExpiredException e) {
            // 未登录时处理错误信息
            String message = (String) request.getSession().getAttribute("message");
            if (!StringUtils.isEmpty(message))
                request.setAttribute("message", message);
            return new ModelAndView("login");
        } finally {
            request.getSession().removeAttribute("message");
        }
    }

    @RequestMapping(value = "/logined", method = RequestMethod.GET)
    public ModelAndView logined() {
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/login_failure")
    public ModelAndView loginFailure(HttpSession session, RedirectAttributes redirectAttributes) {
        Exception ex = null;
        if (session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null) {
            ex = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        } else if (session.getAttribute(WebAttributes.ACCESS_DENIED_403) != null) {
            ex = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
        String errorMsg = (null != ex) ? ex.getMessage() : null;
        redirectAttributes.addFlashAttribute("message", errorMsg);
        return new ModelAndView("redirect:/login");
    }

}
