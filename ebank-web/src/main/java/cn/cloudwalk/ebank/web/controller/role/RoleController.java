package cn.cloudwalk.ebank.web.controller.role;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import cn.cloudwalk.ebank.core.domain.service.role.IRoleService;
import cn.cloudwalk.ebank.core.domain.service.role.command.RoleCommand;
import cn.cloudwalk.ebank.core.domain.service.role.command.RolePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/list")
    public ModelAndView list(RolePaginationCommand command) {
        // TODO session
        Pagination<RoleEntity> pagination = roleService.pagination(command);
        return new ModelAndView("/role/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("role") RoleCommand command, Model model) {
        // TODO session
        List<RoleEntity> roleEntities = roleService.findAll();
        model.addAttribute("roles", roleEntities);
        return new ModelAndView("/role/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@Validated @ModelAttribute("role") RoleCommand command,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        // TODO session
        if (bindingResult.hasErrors()) {
            List<RoleEntity> roleEntities = roleService.findAll();
            return new ModelAndView("/role/add", "roles", roleEntities);
        }

        try {
            roleService.save(command);
            return new ModelAndView("redirect:/role/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute("role") RoleCommand command,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        // TODO session
        List<RoleEntity> roleEntities = roleService.findAll();
        RoleEntity entity = roleService.findById(command.getId());
        if (null == entity) {
            return new ModelAndView("redirect:/role/list");
        } else {
            model.addAttribute("role", entity);
            model.addAttribute("roles", roleEntities);
            return new ModelAndView("/role/edit");
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView edit(@Validated @ModelAttribute("role") RoleCommand command,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        // TODO session
        if (bindingResult.hasErrors()) {
            List<RoleEntity> roleEntities = roleService.findAll();
            return new ModelAndView("/role/add", "roles", roleEntities);
        }

        try {
            roleService.update(command);
            return new ModelAndView("redirect:/role/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        // TODO
        try {
            roleService.delete(id);
            return new ModelAndView("redirect:/role/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
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
        }
    }

}
