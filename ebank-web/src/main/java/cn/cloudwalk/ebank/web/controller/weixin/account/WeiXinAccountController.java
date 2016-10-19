package cn.cloudwalk.ebank.web.controller.weixin.account;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
@Controller
@RequestMapping("/weixin/account")
public class WeiXinAccountController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("account") WeiXinAccountPaginationCommand command) {
        Pagination<WeiXinAccountEntity> pagination = weiXinAccountService.pagination(command);
        return new ModelAndView("/weixin/account/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("account") WeiXinAccountCommand command) {
        return new ModelAndView("/weixinaccount/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@Validated @ModelAttribute("account") WeiXinAccountCommand command,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/weixinaccount/add");
        }
        weiXinAccountService.save(command);
        return new ModelAndView("redirect:/weixin/account/list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute("account") WeiXinAccountCommand command) {
        WeiXinAccountEntity account = weiXinAccountService.findById(command.getId());
        return new ModelAndView("/weixin/account/edit", "account", account);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("account") WeiXinAccountCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinAccountService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinAccountController.edit.success"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("WeiXinAccountController.edit.error"));
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        WeiXinAccountEntity account = weiXinAccountService.findById(id);
        return new ModelAndView("/weixin/account/view", "account", account);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinAccountService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinAccountController.delete.success"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("WeiXinAccountController.delete.error"));
        }
    }

    @RequestMapping(value = "/token/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage token(@PathVariable String id) {
        try {
            weiXinAccountService.token(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinAccountController.token.success"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("WeiXinAccountController.token.error"));
        }
    }

}
