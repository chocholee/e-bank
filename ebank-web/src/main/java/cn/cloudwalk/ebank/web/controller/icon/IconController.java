package cn.cloudwalk.ebank.web.controller.icon;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.service.icon.IIconService;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconCommand;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.support.utils.CustomUploadUtil;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 16/11/8.
 */
@Controller
@RequestMapping("/icon")
public class IconController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${temp.host}")
    private String tempHost;

    @Value("${temp.dir}")
    private String tempDir;

    @Value("${icon.host}")
    private String host;

    @Value("${icon.prefix}")
    private String prefix;

    @Value("${icon.saveDir}")
    private String saveDir;

    @Autowired
    private IIconService iconService;

    @RequestMapping("/list")
    public ModelAndView list(@ModelAttribute("icon") IconPaginationCommand command) {
        Pagination<IconEntity> pagination = iconService.pagination(command);
        return new ModelAndView("icon/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("icon") IconCommand command) {
        return new ModelAndView("icon/add", "tempHost", tempHost);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("icon") IconCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            iconService.save(command, tempDir, saveDir);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.add.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.add.failure.message"));
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute("icon") IconCommand command, Model model) {
        IconEntity entity = iconService.findById(command.getId());
        model.addAttribute("icon", entity);
        model.addAttribute("host", host);
        model.addAttribute("tempHost", tempHost);
        return new ModelAndView("icon/edit");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("icon") IconCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            iconService.update(command, tempDir, saveDir);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id, Model model) {
        IconEntity entity = iconService.findById(id);
        model.addAttribute("icon", entity);
        model.addAttribute("host", host);
        return new ModelAndView("icon/view");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            iconService.delete(id, saveDir);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> upload(HttpServletRequest request) {
        return CustomUploadUtil.upload(request, tempDir, prefix);
    }

}
