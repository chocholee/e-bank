package cn.cloudwalk.ebank.web.controller.weixin.template.text;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.text.WeiXinTextTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.text.IWeiXinTextTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.text.command.WeiXinTextTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.text.command.WeiXinTextTemplatePaginationCommand;
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
 * Created by liwenhe on 16/11/14.
 */
@Controller
@RequestMapping("/weixin/template/text")
public class WeiXinTextTemplateController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinTextTemplateService weiXinTextTemplateService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("text") WeiXinTextTemplatePaginationCommand command) {
        Pagination<WeiXinTextTemplateEntity> pagination = weiXinTextTemplateService.pagination(command);
        return new ModelAndView("weixin/template/text/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("text") WeiXinTextTemplateCommand command) {
        return new ModelAndView("weixin/template/text/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("text") WeiXinTextTemplateCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinTextTemplateService.save(command);
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
        WeiXinTextTemplateEntity entity = weiXinTextTemplateService.findById(id);
        return new ModelAndView("weixin/template/text/edit", "text", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("text") WeiXinTextTemplateCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinTextTemplateService.update(command);
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
        WeiXinTextTemplateEntity entity = weiXinTextTemplateService.findById(id);
        return new ModelAndView("weixin/template/text/view", "text", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinTextTemplateService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

}
