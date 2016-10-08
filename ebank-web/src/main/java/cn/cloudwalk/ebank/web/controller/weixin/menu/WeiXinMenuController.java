package cn.cloudwalk.ebank.web.controller.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.IWeiXinMenuService;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.command.WeiXinMenuCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.command.WeiXinMenuPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import com.arm4j.weixin.exception.WeiXinRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Controller
@RequestMapping("/weixin/menu")
public class WeiXinMenuController {

    @Autowired
    private IWeiXinMenuService weiXinMenuService;

    @RequestMapping("/list")
    public ModelAndView pagination(WeiXinMenuPaginationCommand command) {
        Pagination<WeiXinMenuEntity> pagination = weiXinMenuService.pagination(command);
        return new ModelAndView("/weixinmenu/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("menu") WeiXinMenuCommand command) {
        List<WeiXinMenuEntity> weiXinMenuEntities = weiXinMenuService.findAll();
        return new ModelAndView("/weixinmenu/add", "menus", weiXinMenuEntities);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("menu") WeiXinMenuCommand command, RedirectAttributes redirectAttributes) {
        weiXinMenuService.save(command);
        return new ModelAndView("redirect:/weixin/menu/list");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String id) {
        weiXinMenuService.delete(id);
        return new ModelAndView("redirect:/weixin/menu/list");
    }

    @RequestMapping(value = "sync", method = RequestMethod.GET)
    public ModelAndView sync() {
        try {
            boolean result = weiXinMenuService.sync();
            System.out.println(result);
        } catch (WeiXinRequestException e) {
            e.printStackTrace();
        }
        return null;
    }

}
