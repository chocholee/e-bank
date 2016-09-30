package cn.cloudwalk.ebank.web.controller;

import cn.cloudwalk.ebank.core.domain.model.weixinaccount.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.service.weixinaccount.IWeiXinAccountService;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by liwenhe on 2016/9/20.
 *
 * @author 李文禾
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello Kitty";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Test";
    }

    @RequestMapping(value = "/weixin/{appId}", method = RequestMethod.GET)
    public void weixin(@PathVariable String appId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WeiXinAccountEntity account = weiXinAccountService.findByAppId(appId);
        if (null != account) {
            // 获取请求参数
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");

            // 排序加密参数
            String[] encryptArr = new String[]{account.getToken(), timestamp, nonce};
            Arrays.sort(encryptArr);

            // 对参数加密并判断是否与签名相等
            String sign = DigestUtils.sha1Hex(String.join("", encryptArr));
            if (sign.equals(signature)) {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(echostr);
            } else {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("微信加密签名验证失败!");
            }
        } else {
            response.setStatus(HttpStatus.SC_FORBIDDEN);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("访问拒绝!");
        }
    }

    @RequestMapping(value = "/weixin/{appId}", method = RequestMethod.POST)
    public void weixin(@RequestBody String content, HttpServletResponse response) {
        System.out.println(content);
    }

}
