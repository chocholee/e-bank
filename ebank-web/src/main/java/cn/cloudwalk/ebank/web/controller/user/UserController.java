package cn.cloudwalk.ebank.web.controller.user;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.service.role.IRoleService;
import cn.cloudwalk.ebank.core.domain.service.user.IUserService;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserAddCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserEditCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("user") UserPaginationCommand command) {
        Pagination<UserEntity> pagination = userService.pagination(command);
        return new ModelAndView("user/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("user") UserAddCommand command) {
        return new ModelAndView("/user/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("user") UserAddCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            userService.save(command);
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
    public ModelAndView edit(@PathVariable String id) {
        UserEntity entity = userService.findById(id);
        return new ModelAndView("user/edit", "user", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("user") UserEditCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            userService.update(command);
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
        UserEntity entity = userService.findById(id);
        return new ModelAndView("user/view", "user", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            userService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/lock/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage lock(@PathVariable String id) {
        try {
            userService.lock(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.GET)
    public ModelAndView authorize(@PathVariable String id, Model model) {
        List<RoleEntity> roleEntities = roleService.findAll();
        UserEntity entity = userService.findById(id);
        model.addAttribute("user", entity);
        model.addAttribute("roles", roleEntities);
        return new ModelAndView("user/authorize");
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage authorize(@PathVariable String id, String[] roleIds) {
        try {
            userService.authorize(id, roleIds);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/parent/list")
    public ModelAndView parentList(@ModelAttribute("user") UserPaginationCommand command) {
        Pagination<UserEntity> pagination = userService.paginationWithoutSelf(command);
        return new ModelAndView("user/select-parent", "pagination", pagination);
    }

}
