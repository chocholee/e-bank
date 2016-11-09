package cn.cloudwalk.ebank.web.controller.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionCommand;
import cn.cloudwalk.ebank.core.domain.service.icon.IIconService;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 2016/9/29.
 *
 * @author 李文禾
 */
@Controller
@RequestMapping("/function")
public class FunctionController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IFunctionService functionService;

    @Autowired
    private IIconService iconService;

    @RequestMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/function/list");
    }

    @RequestMapping("/dataset")
    @ResponseBody
    public List<Map<String, Object>> dataset() {
        List<Map<String, Object>> dataset = new ArrayList<>();
        // 查找一级菜单
        List<FunctionEntity> firstMenus = functionService.findForFirstMenu();
        for (FunctionEntity first : firstMenus) {
            Map<String, Object> firstMenuMap = new HashMap<>();
            firstMenuMap.put("id", first.getId());
            firstMenuMap.put("name", first.getName());
            firstMenuMap.put("order", first.getOrder());
            firstMenuMap.put("type", first.getType());
            firstMenuMap.put("icon", (null != first.getIconEntity()) ? first.getIconEntity().getBeforeHoverPath() : null);

            // 查找二级菜单
            List<Map<String, Object>> secondMenuList = new ArrayList<>();
            List<FunctionEntity> secondMenus = functionService.findByParentId(first.getId());
            for (FunctionEntity second : secondMenus) {
                Map<String, Object> secondMenuMap = new HashMap<>();
                secondMenuMap.put("id", second.getId());
                secondMenuMap.put("name", second.getName());
                secondMenuMap.put("order", second.getOrder());
                secondMenuMap.put("type", second.getType());
                secondMenuMap.put("icon", (null != second.getIconEntity()) ? second.getIconEntity().getBeforeHoverPath() : null);

                // 查找三级菜单
                List<Map<String, Object>> thirdMenuList = new ArrayList<>();
                List<FunctionEntity> thirdMenus = functionService.findByParentId(second.getId());
                for (FunctionEntity third : thirdMenus) {
                    Map<String, Object> thirdMenuMap = new HashMap<>();
                    thirdMenuMap.put("id", third.getId());
                    thirdMenuMap.put("name", third.getName());
                    thirdMenuMap.put("order", third.getOrder());
                    thirdMenuMap.put("type", third.getType());
                    thirdMenuMap.put("icon", (null != third.getIconEntity()) ? third.getIconEntity().getBeforeHoverPath() : null);
                    thirdMenuList.add(thirdMenuMap);
                }
                // 组装三级菜单数据至二级菜单中
                secondMenuMap.put("children", thirdMenuList);
                secondMenuList.add(secondMenuMap);
            }
            // 组装二级菜单数据至一级菜单中
            firstMenuMap.put("children", secondMenuList);
            dataset.add(firstMenuMap);
        }
        return dataset;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("function") FunctionCommand command) {
        List<IconEntity> iconEntities = iconService.findAll();
        return new ModelAndView("/function/add", "icons", iconEntities);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("function") FunctionCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            functionService.save(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.add.success.message"));
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.not.unique.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.add.failure.message"));
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute("function") FunctionCommand command, Model model) {
        FunctionEntity entity = functionService.findById(command.getId());
        List<IconEntity> iconEntities = iconService.findAll();
        model.addAttribute("function", entity);
        model.addAttribute("icons", iconEntities);
        return new ModelAndView("/function/edit");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("function") FunctionCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            functionService.update(command);
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
        FunctionEntity entity = functionService.findById(id);
        return new ModelAndView("/function/view", "function", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            functionService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/parent/list")
    public ModelAndView parentList() {
        return new ModelAndView("/function/select-parent");
    }

}
