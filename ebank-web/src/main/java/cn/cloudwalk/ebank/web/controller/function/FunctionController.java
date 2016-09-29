package cn.cloudwalk.ebank.web.controller.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionCommand;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionPaginationCommand;
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
@RequestMapping("/function")
public class FunctionController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IFunctionService functionService;

    @RequestMapping("/list")
    public ModelAndView list(FunctionPaginationCommand command) {
        // TODO session
        Pagination<FunctionEntity> pagination = functionService.pagination(command);
        return new ModelAndView("/function/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("function") FunctionCommand command, Model model) {
        // TODO session
        List<FunctionEntity> functionEntities = functionService.findAll();
        model.addAttribute("functions", functionEntities);
        return new ModelAndView("/function/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@Validated @ModelAttribute("function") FunctionCommand command,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        // TODO session
        if (bindingResult.hasErrors()) {
            List<FunctionEntity> roleEntities = functionService.findAll();
            return new ModelAndView("/function/add", "functions", roleEntities);
        }

        try {
            functionService.save(command);
            return new ModelAndView("redirect:/function/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // TODO update

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        // TODO
        try {
            functionService.delete(id);
            return new ModelAndView("redirect:/function/list");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
