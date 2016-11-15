package cn.cloudwalk.ebank.web.controller.weixin.template.news;

import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsItemsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.template.news.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.IWeiXinNewsItemsTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.IWeiXinNewsTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.command.WeiXinNewsItemsTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.command.WeiXinNewsTemplateCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.command.WeiXinNewsTemplatePaginationCommand;
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
 * Created by liwenhe on 16/11/14.
 */
@Controller
@RequestMapping("/weixin/template/news")
public class WeiXinNewsTemplateController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${temp.host}")
    private String tempHost;

    @Value("${temp.dir}")
    private String tempDir;

    @Value("${newsItemTemplate.host}")
    private String host;

    @Value("${newsItemTemplate.prefix}")
    private String prefix;

    @Value("${newsItemTemplate.saveDir}")
    private String saveDir;

    @Autowired
    private IWeiXinNewsTemplateService weiXinNewsTemplateService;

    @Autowired
    private IWeiXinNewsItemsTemplateService weiXinNewsItemsTemplateService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("news") WeiXinNewsTemplatePaginationCommand command) {
        Pagination<WeiXinNewsTemplateEntity> pagination = weiXinNewsTemplateService.pagination(command);
        return new ModelAndView("weixin/template/news/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("news") WeiXinNewsTemplateCommand command) {
        return new ModelAndView("weixin/template/news/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("news") WeiXinNewsTemplateCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinNewsTemplateService.save(command);
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
        WeiXinNewsTemplateEntity entity = weiXinNewsTemplateService.findById(id);
        return new ModelAndView("weixin/template/news/edit", "news", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("news") WeiXinNewsTemplateCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinNewsTemplateService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/item/add/{newsTemplateId}", method = RequestMethod.GET)
    public ModelAndView itemAdd(@ModelAttribute("item") WeiXinNewsItemsTemplateCommand command) {
        return new ModelAndView("weixin/template/news/item-add", "tempHost", tempHost);
    }

    @RequestMapping(value = "/item/add/{newsTemplateId}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage itemAdd(@Validated @ModelAttribute("item") WeiXinNewsItemsTemplateCommand command,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinNewsItemsTemplateService.save(command, tempDir, saveDir);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.add.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.add.failure.message"));
        }
    }

    @RequestMapping(value = "/item/edit/{id}", method = RequestMethod.GET)
    public ModelAndView itemEdit(@ModelAttribute("item") WeiXinNewsItemsTemplateCommand command, Model model) {
        WeiXinNewsItemsTemplateEntity entity = weiXinNewsItemsTemplateService.findById(command.getId());
        model.addAttribute("templateHost", host);
        model.addAttribute("tempHost", tempHost);
        model.addAttribute("item", entity);
        return new ModelAndView("weixin/template/news/item-edit");
    }

    @RequestMapping(value = "/item/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage itemEdit(@Validated @ModelAttribute("item") WeiXinNewsItemsTemplateCommand command,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinNewsItemsTemplateService.update(command, tempDir, saveDir);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/item/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage deleteItem(@PathVariable String id) {
        try {
            weiXinNewsItemsTemplateService.delete(id, saveDir);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/item/view/{newsTemplateId}", method = RequestMethod.GET)
    public ModelAndView viewItem(@PathVariable String newsTemplateId, Model model) {
        List<WeiXinNewsItemsTemplateEntity> itemsTemplateEntities =
                weiXinNewsItemsTemplateService.findByNewsTemplateId(newsTemplateId);
        model.addAttribute("items", itemsTemplateEntities);
        model.addAttribute("templateHost", host);
        return new ModelAndView("weixin/template/news/item-view");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinNewsTemplateService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

    @RequestMapping(value = "/item/upload", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> upload(HttpServletRequest request) {
        return CustomUploadUtil.upload(request, tempDir, prefix);
    }

}
