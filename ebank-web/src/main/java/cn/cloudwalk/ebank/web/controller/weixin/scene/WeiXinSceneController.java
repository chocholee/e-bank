package cn.cloudwalk.ebank.web.controller.weixin.scene;

import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.scene.IWeiXinSceneService;
import cn.cloudwalk.ebank.core.domain.service.weixin.scene.command.WeiXinSceneCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.scene.command.WeiXinScenePaginationCommand;
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
 * Created by liwenhe on 16/11/17.
 */
@Controller
@RequestMapping("/weixin/scene")
public class WeiXinSceneController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinSceneService weiXinSceneService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("scene") WeiXinScenePaginationCommand command) {
        Pagination<WeiXinSceneEntity> pagination = weiXinSceneService.pagination(command);
        return new ModelAndView("weixin/scene/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("scene") WeiXinSceneCommand command) {
        return new ModelAndView("weixin/scene/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("scene") WeiXinSceneCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinSceneService.save(command);
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
        WeiXinSceneEntity entity = weiXinSceneService.findById(id);
        return new ModelAndView("weixin/scene/edit", "scene", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("scene") WeiXinSceneCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinSceneService.update(command);
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
        WeiXinSceneEntity entity = weiXinSceneService.findById(id);
        return new ModelAndView("weixin/scene/view", "scene", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinSceneService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/list/select")
    public ModelAndView listSelect(@ModelAttribute("scene") WeiXinScenePaginationCommand command) {
        Pagination<WeiXinSceneEntity> pagination = weiXinSceneService.pagination(command);
        return new ModelAndView("weixin/scene/list-select", "pagination", pagination);
    }

}
