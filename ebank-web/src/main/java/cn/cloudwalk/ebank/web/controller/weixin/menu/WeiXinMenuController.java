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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
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
        List<Map<String, Object>> dataset = new ArrayList<>();
        // 查找一级菜单
        List<WeiXinMenuEntity> firstMenus = weiXinMenuService.findByParentIsNull();
        for (WeiXinMenuEntity first : firstMenus) {
            Map<String, Object> firstMenuMap = new HashMap<>();
            firstMenuMap.put("id", first.getId());
            firstMenuMap.put("name", first.getName());
            firstMenuMap.put("order", first.getOrder());

            // 查找二级菜单
            List<Map<String, Object>> secondMenuList = new ArrayList<>();
            List<WeiXinMenuEntity> secondMenus = weiXinMenuService.findByParentId(first.getId());
            for (WeiXinMenuEntity second : secondMenus) {
                Map<String, Object> secondMenuMap = new HashMap<>();
                secondMenuMap.put("id", second.getId());
                secondMenuMap.put("name", second.getName());
                secondMenuMap.put("order", second.getOrder());
                secondMenuList.add(secondMenuMap);
            }

            // 组装二级菜单数据至一级菜单中
            firstMenuMap.put("children", secondMenuList);
            dataset.add(firstMenuMap);
        }
        return dataset;
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

        List<WeiXinMenuEntity> parents = weiXinMenuService.findByParentIsNull();
        // 微信一级菜单不能超过3个
        if (parents.size() >= 3) {
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor()
                            .getMessage("WeiXinMenuController.menu.first.message", new Object[]{3}));
        }
        // 微信二级菜单不能超过5个
        for (WeiXinMenuEntity entity : parents) {
            List<WeiXinMenuEntity> children = weiXinMenuService.findByParentId(entity.getId());
            if (children.size() >= 5) {
                return new AlertMessage(AlertMessage.Type.ERROR,
                        getMessageSourceAccessor()
                                .getMessage("WeiXinMenuController.menu.second.message", new Object[]{5}));
            }
        }

        try {
            weiXinMenuService.save(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.add.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.add.failure.message"));
        }
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
            boolean result = weiXinMenuService.sync();
            if (result) {
                return new AlertMessage(AlertMessage.Type.SUCCESS,
                        getMessageSourceAccessor().getMessage("WeiXinMenuController.menu.sync.success.message"));
            } else {
                return new AlertMessage(AlertMessage.Type.ERROR,
                        getMessageSourceAccessor().getMessage("WeiXinMenuController.menu.sync.failure.message"));
            }
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
