package cn.cloudwalk.ebank.web.controller;

import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liwenhe on 2016/9/20.
 *
 * @author 李文禾
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @RequestMapping("/forbidden")
    @ResponseBody
    public String forbidden() {
        return "FORBIDDEN";
    }

    @RequestMapping("/page_404")
    @ResponseBody
    public String page404(){
        return "404";
    }

    @RequestMapping("/page_500")
    @ResponseBody
    public String page500(){
        return "500";
    }

}
