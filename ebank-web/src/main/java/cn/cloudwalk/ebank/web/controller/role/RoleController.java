package cn.cloudwalk.ebank.web.controller.role;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import cn.cloudwalk.ebank.core.domain.service.role.IRoleService;
import cn.cloudwalk.ebank.core.domain.service.role.command.RoleCommand;
import cn.cloudwalk.ebank.core.domain.service.role.command.RolePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.support.security.CustomFilterInvocationSecurityMetadataSource;
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
 * Created by liwenhe on 2016/9/29.
 *
 * @author 李文禾
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IFunctionService functionService;

    @Autowired
    private CustomFilterInvocationSecurityMetadataSource securityMetadataSource;

    @RequestMapping("/list")
    public ModelAndView list(@ModelAttribute("role") RolePaginationCommand command) {
        Pagination<RoleEntity> pagination = roleService.pagination(command);
        return new ModelAndView("/role/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("role") RoleCommand command, Model model) {
        return new ModelAndView("/role/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("role") RoleCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            roleService.save(command);
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
    public ModelAndView edit(@ModelAttribute("role") RoleCommand command) {
        RoleEntity entity = roleService.findById(command.getId());
        return new ModelAndView("/role/edit", "role", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("role") RoleCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            roleService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            roleService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.GET)
    public ModelAndView authorize(@PathVariable String id, Model model) {
        // TODO
        List<FunctionEntity> functionEntities = functionService.findAll();
        RoleEntity entity = roleService.findById(id);
        if (null == entity) {
            return new ModelAndView("redirect:/role/list");
        } else {
            model.addAttribute("role", entity);
            model.addAttribute("functions", functionEntities);
            return new ModelAndView("/role/authorize");
        }
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.POST)
    public ModelAndView authorize(@PathVariable String id, String[] functionIds, RedirectAttributes redirectAttributes) {
        // TODO
        try {
            roleService.authorize(id, functionIds);
            redirectAttributes.addAttribute("id", id);
            return new ModelAndView("redirect:/role/authorize/{id}");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            securityMetadataSource.init();
        }
    }

}
