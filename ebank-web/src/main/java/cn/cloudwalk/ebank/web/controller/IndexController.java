package cn.cloudwalk.ebank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liwenhe on 2016/9/20.
 *
 * @author 李文禾
 */
@Controller
public class IndexController {

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

}
