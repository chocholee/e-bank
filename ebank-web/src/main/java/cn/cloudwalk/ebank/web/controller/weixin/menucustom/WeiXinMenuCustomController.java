package cn.cloudwalk.ebank.web.controller.weixin.menucustom;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.IWeiXinMenuCustomService;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.command.WeiXinMenuCustomCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.command.WeiXinMenuCustomPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 16/11/22.
 */
@Controller
@RequestMapping("/weixin/menu/custom")
public class WeiXinMenuCustomController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinMenuCustomService weiXinMenuCustomService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("menuCustom") WeiXinMenuCustomPaginationCommand command) {
        Pagination<WeiXinMenuCustomEntity> pagination = weiXinMenuCustomService.pagination(command);
        return new ModelAndView("weixin/menucustom/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("menuCustom") WeiXinMenuCustomCommand command) {
        return new ModelAndView("weixin/menucustom/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("menuCustom") WeiXinMenuCustomCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinMenuCustomService.save(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.add.success.message"));
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.not.unique.message"));
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
        WeiXinMenuCustomEntity entity = weiXinMenuCustomService.findById(id);
        return new ModelAndView("weixin/menucustom/edit", "menuCustom", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("menuCustom") WeiXinMenuCustomCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinMenuCustomService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.not.unique.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        WeiXinMenuCustomEntity entity = weiXinMenuCustomService.findById(id);
        return new ModelAndView("weixin/menucustom/view", "menuCustom", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinMenuCustomService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/menu/list/{id}", method = RequestMethod.GET)
    public ModelAndView menuList(@PathVariable String id) {
        return new ModelAndView("weixin/menucustom/list-menu", "id", id);
    }

    @RequestMapping(value = "/menu/dataset/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> dataset(@PathVariable String id) {
        return weiXinMenuCustomService.menuPackageMenuDataSet(id);
    }

    @RequestMapping(value = "/menu/add/{id}", method = RequestMethod.GET)
    public ModelAndView menuAdd(@PathVariable String id) {
        return new ModelAndView("weixin/menu/add", "menuCustom", id);
    }

}
