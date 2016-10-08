package cn.cloudwalk.ebank.web.controller.weixin.account;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.command.WeiXinAccountPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
@Controller
@RequestMapping("/weixin/account")
public class WeiXinAccountController {

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @RequestMapping("/list")
    public ModelAndView pagination(WeiXinAccountPaginationCommand command) {
        Pagination<WeiXinAccountEntity> pagination = weiXinAccountService.pagination(command);
        return new ModelAndView("/weixinaccount/list", "pagination", pagination);
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
        return new ModelAndView("/weixinaccount/edit", "account", account);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView edit(@Validated @ModelAttribute("account") WeiXinAccountCommand command,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/weixinaccount/edit");
        }
        weiXinAccountService.update(command);
        return new ModelAndView("redirect:/weixin/account/list");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String id) {
        weiXinAccountService.delete(id);
        return new ModelAndView("redirect:/weixin/account/list");
    }

}
