package cn.cloudwalk.ebank.web.controller.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.IWeiXinMenuService;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.command.WeiXinMenuCommand;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import com.arm4j.weixin.exception.WeiXinRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Controller
@RequestMapping("/weixin/menu")
public class WeiXinMenuController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinMenuService weiXinMenuService;

    @RequestMapping("/list")
    public ModelAndView pagination() {
        return new ModelAndView("/weixin/menu/list");
    }

    @RequestMapping("/dataset")
    @ResponseBody
    public List<Map<String, Object>> dataset() {
        return weiXinMenuService.dataset();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("menu") WeiXinMenuCommand command) {
        return new ModelAndView("/weixin/menu/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("menu") WeiXinMenuCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        // 查询菜单
        List<WeiXinMenuEntity> parents;
        if (!StringUtils.isEmpty(command.getMenuCustom())) {
            parents = weiXinMenuService.findByParentIsNullAndMenuCustom(command.getMenuCustom());
        } else {
            parents = weiXinMenuService.findByParentAndMenuCustomIsNull();
        }

        // 微信一级菜单不能超过3个
        if (parents.size() >= 3) {
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor()
                            .getMessage("WeiXinMenuController.menu.first.message", new Object[]{3}));
        }

        try {
            weiXinMenuService.save(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.add.success.message"));
        } catch (WeiXinNotFoundException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.add.failure.message"));
        }
    }

    @RequestMapping(value = "/add/child/{parent}", method = RequestMethod.GET)
    public ModelAndView addChild(@PathVariable String parent) {
        return new ModelAndView("/weixin/menu/add-child", "parent", parent);
    }

    @RequestMapping(value = "/add/child/{parent}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage addChild(@Validated @ModelAttribute("menu") WeiXinMenuCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        List<WeiXinMenuEntity> children = weiXinMenuService.findByParentId(command.getParent());
        if (children.size() >= 5) {
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor()
                            .getMessage("WeiXinMenuController.menu.second.message", new Object[]{5}));
        }

        try {
            weiXinMenuService.save(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.add.success.message"));
        } catch (WeiXinNotFoundException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.add.failure.message"));
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) {
        WeiXinMenuEntity menu = weiXinMenuService.findByIdAndFetch(id);
        return new ModelAndView("/weixin/menu/edit", "menu", menu);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("menu") WeiXinMenuCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinMenuService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        WeiXinMenuEntity menu = weiXinMenuService.findByIdAndFetch(id);
        return new ModelAndView("/weixin/menu/view", "menu", menu);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinMenuService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "sync", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage sync() {
        try {
            weiXinMenuService.sync();
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinMenuController.menu.sync.success.message"));
        } catch (WeiXinRequestException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinMenuController.menu.sync.failure.message"));
        } catch (WeiXinNotFoundException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.SUCCESS, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinMenuController.menu.sync.failure.message"));
        }
    }

    @RequestMapping("/parent/list")
    public ModelAndView parentList() {
        return new ModelAndView("/weixin/menu/select-parent");
    }

}
