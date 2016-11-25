package cn.cloudwalk.ebank.web.controller.weixin;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntityStatus;
import cn.cloudwalk.ebank.core.domain.service.weixin.WeiXinService;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Controller
public class WeiXinController {

    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

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
            StringBuilder sb = new StringBuilder();
            for (String str : encryptArr) {
                sb.append(str);
            }
            String sign = DigestUtils.sha1Hex(sb.toString());
            if (sign.equals(signature)) {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(echostr);
                weiXinAccountService.authorize(account.getId(), WeiXinAccountEntityStatus.AUTHORIZED);
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
    public void weixin(HttpServletRequest request, HttpServletResponse response) {
        weiXinService.core(request, response);
    }

}
