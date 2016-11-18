package cn.cloudwalk.ebank.web.controller.weixin.template.image;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.image.WeiXinImageTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.IWeiXinImageTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.command.WeiXinImageTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.template.image.command.WeiXinImageTemplatePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomUploadUtil;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
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
 * Created by liwenhe on 16/11/15.
 */
@Controller
@RequestMapping("/weixin/template/image")
public class WeiXinImageTemplateController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${temp.host}")
    private String tempHost;

    @Value("${temp.dir}")
    private String tempDir;

    @Value("${imageTemplate.host}")
    private String host;

    @Value("${imageTemplate.prefix}")
    private String prefix;

    @Value("${imageTemplate.saveDir}")
    private String saveDir;

    @Autowired
    private IWeiXinImageTemplateService weiXinImageTemplateService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("image") WeiXinImageTemplatePaginationCommand command) {
        Pagination<WeiXinImageTemplateEntity> pagination = weiXinImageTemplateService.pagination(command);
        return new ModelAndView("weixin/template/image/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("image") WeiXinImageTemplateCommand command) {
        return new ModelAndView("weixin/template/image/add", "tempHost", tempHost);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("image") WeiXinImageTemplateCommand command,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinImageTemplateService.save(command, tempDir, saveDir);
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
    public ModelAndView edit(@ModelAttribute("image") WeiXinImageTemplateCommand command, Model model) {
        WeiXinImageTemplateEntity entity = weiXinImageTemplateService.findById(command.getId());
        model.addAttribute("templateHost", host);
        model.addAttribute("tempHost", tempHost);
        model.addAttribute("image", entity);
        return new ModelAndView("weixin/template/image/edit");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("image") WeiXinImageTemplateCommand command,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinImageTemplateService.update(command, tempDir, saveDir);
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
    public ModelAndView view(@PathVariable String id, Model model) {
        WeiXinImageTemplateEntity entity = weiXinImageTemplateService.findById(id);
        model.addAttribute("image", entity);
        model.addAttribute("templateHost", host);
        return new ModelAndView("weixin/template/image/view");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinImageTemplateService.delete(id, saveDir);
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
