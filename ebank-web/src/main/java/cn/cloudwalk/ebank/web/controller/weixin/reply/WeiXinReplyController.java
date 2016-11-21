package cn.cloudwalk.ebank.web.controller.weixin.reply;

import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntityType;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.image.WeiXinImageTemplateEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.text.WeiXinTextTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.reply.IWeiXinReplyService;
import cn.cloudwalk.ebank.core.domain.service.weixin.reply.command.WeiXinReplyCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.reply.command.WeiXinReplyPaginationCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.IWeiXinImageTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.news.IWeiXinNewsTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.text.IWeiXinTextTemplateService;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liwenhe on 16/11/21.
 */
@Controller
@RequestMapping("/weixin/reply")
public class WeiXinReplyController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinReplyService weiXinReplyService;

    @Autowired
    private IWeiXinTextTemplateService weiXinTextTemplateService;

    @Autowired
    private IWeiXinImageTemplateService weiXinImageTemplateService;

    @Autowired
    private IWeiXinNewsTemplateService weiXinNewsTemplateService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("reply") WeiXinReplyPaginationCommand command) {
        Pagination<WeiXinReplyEntity> pagination = weiXinReplyService.pagination(command);
        return new ModelAndView("/weixin/reply/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("reply") WeiXinReplyCommand command) {
        return new ModelAndView("weixin/reply/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("reply") WeiXinReplyCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinReplyService.save(command);
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
    public ModelAndView edit(@ModelAttribute("reply") WeiXinReplyCommand command, Model model) {
        WeiXinReplyEntity entity = weiXinReplyService.findById(command.getId());
        if (entity.getType() == WeiXinReplyEntityType.TEXT) {
            WeiXinTextTemplateEntity template = weiXinTextTemplateService.findById(entity.getTemplateId());
            model.addAttribute("template", template);
        }
        if (entity.getType() == WeiXinReplyEntityType.IMAGE) {
            WeiXinImageTemplateEntity template = weiXinImageTemplateService.findById(entity.getTemplateId());
            model.addAttribute("template", template);
        }
        if (entity.getType() == WeiXinReplyEntityType.NEWS) {
            WeiXinNewsTemplateEntity template = weiXinNewsTemplateService.findById(entity.getTemplateId());
            model.addAttribute("template", template);
        }
        model.addAttribute("reply", entity);
        return new ModelAndView("weixin/reply/edit");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("reply") WeiXinReplyCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinReplyService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
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
        WeiXinReplyEntity entity = weiXinReplyService.findById(id);
        return new ModelAndView("weixin/reply/view", "reply", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinReplyService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

}
