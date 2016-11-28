package cn.cloudwalk.ebank.web.controller.weixin.menucustomrule;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.IWeiXinMenuCustomRuleService;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.command.WeiXinMenuCustomRuleCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.command.WeiXinMenuCustomRulePaginationCommand;
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

/**
 * Created by liwenhe on 16/11/25.
 */
@Controller("weiXinMenuCustomRuleController")
@RequestMapping("/weixin/menu/custom/rule")
public class WeiXinMenuCustomRuleController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinMenuCustomRuleService weiXinMenuCustomRuleService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("menuCustomRule") WeiXinMenuCustomRulePaginationCommand command) {
        Pagination<WeiXinMenuCustomRuleEntity> pagination = weiXinMenuCustomRuleService.pagination(command);
        return new ModelAndView("weixin/menucustomrule/list", "pagination", pagination);
    }

    @RequestMapping("/add")
    public ModelAndView add(@ModelAttribute("menuCustomRule") WeiXinMenuCustomRuleCommand command) {
        return new ModelAndView("weixin/menucustomrule/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("menuCustomRule") WeiXinMenuCustomRuleCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinMenuCustomRuleService.save(command);
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

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id) {
        WeiXinMenuCustomRuleEntity entity = weiXinMenuCustomRuleService.findByIdAndFetch(id);
        return new ModelAndView("weixin/menucustomrule/edit", "menuCustomRule", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("menuCustomRule") WeiXinMenuCustomRuleCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinMenuCustomRuleService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
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
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        WeiXinMenuCustomRuleEntity entity = weiXinMenuCustomRuleService.findByIdAndFetch(id);
        return new ModelAndView("weixin/menucustomrule/view", "menuCustomRule", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinMenuCustomRuleService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (WeiXinNotFoundException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/sync/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage sync(@PathVariable String id) {
        try {
            weiXinMenuCustomRuleService.sync(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinMenuCustomRuleController.sync.success.message"));
        } catch (WeiXinNotFoundException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("WeiXinMenuCustomRuleController.sync.failure.message"));
        }
    }

}
