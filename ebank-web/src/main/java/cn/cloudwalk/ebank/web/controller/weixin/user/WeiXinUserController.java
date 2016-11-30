package cn.cloudwalk.ebank.web.controller.weixin.user;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.user.WeiXinUserEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.virtual.IWeiXinGroupVirtualService;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.wechat.IWeiXinGroupWechatService;
import cn.cloudwalk.ebank.core.domain.service.weixin.user.IWeiXinUserService;
import cn.cloudwalk.ebank.core.domain.service.weixin.user.command.WeiXinUserPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liwenhe on 16/11/29.
 */
@Controller
@RequestMapping("/weixin/user")
public class WeiXinUserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinUserService weiXinUserService;

    @Autowired
    private IWeiXinGroupWechatService weiXinGroupWechatService;

    @Autowired
    private IWeiXinGroupVirtualService weiXinGroupVirtualService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("user") WeiXinUserPaginationCommand command, Model model) {
        // 查询数据
        List<WeiXinGroupWechatEntity> groupWechatEntities = weiXinGroupWechatService.findAll();
        List<WeiXinGroupVirtualEntity> groupVirtualEntities = weiXinGroupVirtualService.findAll();
        Pagination<WeiXinUserEntity> pagination = weiXinUserService.pagination(command);

        // 传递数据至页面
        model.addAttribute("groupWechatEntities", groupWechatEntities);
        model.addAttribute("groupVirtualEntities", groupVirtualEntities);
        model.addAttribute("pagination", pagination);

        return new ModelAndView("weixin/user/list");
    }

    @RequestMapping("/list/group/{groupWechatId}")
    public ModelAndView listView(@ModelAttribute("user") WeiXinUserPaginationCommand command, Model model) {
        // 查询数据
        Pagination<WeiXinUserEntity> pagination = weiXinUserService.pagination(command);

        // 传递数据至页面
        model.addAttribute("pagination", pagination);

        return new ModelAndView("weixin/user/list-group");
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        WeiXinUserEntity entity = weiXinUserService.findByIdAndFetch(id);
        return new ModelAndView("weixin/user/view", "user", entity);
    }

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage sync() {
        try {
            weiXinUserService.sync();
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("WeiXinUserController.sync.success.message"));
        } catch (WeiXinNotFoundException e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("WeiXinUserController.sync.failure.message"));
        }
    }

    @RequestMapping(value = "/group/wechat/{id}", method = RequestMethod.GET)
    public ModelAndView groupWechat(@PathVariable String id) {
        WeiXinUserEntity entity = weiXinUserService.findByIdAndFetch(id);
        return new ModelAndView("weixin/user/group", "user", entity);
    }

    @RequestMapping(value = "/group/wechat/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage groupWechat(@PathVariable String id, String newGroupWechatId) {
        try {
            weiXinUserService.doGroupWechat(id, newGroupWechatId);
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

}
