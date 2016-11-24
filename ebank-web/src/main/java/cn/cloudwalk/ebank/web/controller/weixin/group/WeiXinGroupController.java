package cn.cloudwalk.ebank.web.controller.weixin.group;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.IWeiXinGroupService;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupPaginationCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.virtual.IWeiXinGroupVirtualService;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.wechat.IWeiXinGroupWechatService;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
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

/**
 * Created by liwenhe on 16/11/23.
 */
@Controller
@RequestMapping("/weixin/group")
public class WeiXinGroupController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinGroupService weiXinGroupService;

    @Autowired
    private IWeiXinGroupWechatService weiXinGroupWechatService;

    @Autowired
    private IWeiXinGroupVirtualService weiXinGroupVirtualService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("group") WeiXinGroupPaginationCommand command) {
        Pagination<WeiXinGroupEntity> pagination = weiXinGroupService.pagination(command);
        return new ModelAndView("weixin/group/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("group") WeiXinGroupCommand command) {
        return new ModelAndView("weixin/group/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("group") WeiXinGroupCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinGroupService.save(command);
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
        WeiXinGroupEntity entity = weiXinGroupService.findById(id);
        return new ModelAndView("weixin/group/edit", "group", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("group") WeiXinGroupCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinGroupService.update(command);
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
        WeiXinGroupEntity entity = weiXinGroupService.findById(id);
        return new ModelAndView("weixin/group/edit", "group", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinGroupService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage sync() {
        try {
            weiXinGroupService.sync();
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinGroupController.sync.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("WeiXinGroupController.sync.failure.message"));
        }
    }

    @RequestMapping("/list/select/wechat")
    public ModelAndView listSelectWechat(@ModelAttribute("group") WeiXinGroupPaginationCommand command) {
        Pagination<WeiXinGroupWechatEntity> pagination = weiXinGroupWechatService.pagination(command);
        return new ModelAndView("weixin/group/list-select", "pagination", pagination);
    }

    @RequestMapping("/list/select/virtual")
    public ModelAndView listSelectVirtual(@ModelAttribute("group") WeiXinGroupPaginationCommand command) {
        Pagination<WeiXinGroupVirtualEntity> pagination = weiXinGroupVirtualService.pagination(command);
        return new ModelAndView("weixin/group/list-select", "pagination", pagination);
    }

}
