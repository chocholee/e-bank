package cn.cloudwalk.ebank.web.controller.user;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.service.role.IRoleService;
import cn.cloudwalk.ebank.core.domain.service.user.IUserService;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserAddCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserEditCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView pagination(UserPaginationCommand command) {
        Pagination<UserEntity> pagination = userService.pagination(command);
        return new ModelAndView("/user/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("user") UserAddCommand command) {
        return new ModelAndView("/user/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@Validated @ModelAttribute("user") UserAddCommand command,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        // TODO
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/add");
        }

        try {
            userService.save(command);
            return new ModelAndView("redirect:/user/list");
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getMessage(), e);
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id, Model model) {
        UserEntity entity = userService.findById(id);
        if (null == entity) {
            // TODO
            return new ModelAndView("redirect:/user/list");
        } else {
            model.addAttribute("user", entity);
            return new ModelAndView("/user/edit");
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView edit(@Validated @ModelAttribute("user") UserEditCommand command,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        // TODO
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/edit");
        }

        try {
            userService.update(command);
            return new ModelAndView("redirect:/user/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        // TODO
        try {
            userService.delete(id);
            return new ModelAndView("redirect:/user/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/lock/{id}", method = RequestMethod.GET)
    public ModelAndView lock(@PathVariable String id) {
        // TODO
        try {
            userService.lock(id);
            return new ModelAndView("redirect:/user/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.GET)
    public ModelAndView authorize(@PathVariable String id, Model model) {
        // TODO
        List<RoleEntity> roleEntities = roleService.findAll();
        UserEntity entity = userService.findById(id);
        if (null == entity) {
            return new ModelAndView("redirect:/user/list");
        } else {
            model.addAttribute("user", entity);
            model.addAttribute("roles", roleEntities);
            return new ModelAndView("/user/authorize");
        }
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.POST)
    public ModelAndView authorize(@PathVariable String id, String[] roleIds, RedirectAttributes redirectAttributes) {
        // TODO
        try {
            userService.authorize(id, roleIds);
            redirectAttributes.addAttribute("id", id);
            return new ModelAndView("redirect:/user/authorize/{id}");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}