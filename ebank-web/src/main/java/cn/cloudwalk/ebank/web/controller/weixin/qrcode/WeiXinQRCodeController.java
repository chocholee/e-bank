package cn.cloudwalk.ebank.web.controller.weixin.qrcode;

import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntityType;
import cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.IWeiXinQRCodeService;
import cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.command.WeiXinQRCodeCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.command.WeiXinQRCodePaginationCommand;
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
 * Created by liwenhe on 16/11/24.
 */
@Controller
@RequestMapping("/weixin/qrcode")
public class WeiXinQRCodeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinQRCodeService weiXinQRCodeService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("qrcode") WeiXinQRCodePaginationCommand command) {
        Pagination<WeiXinQRCodeEntity> pagination = weiXinQRCodeService.pagination(command);
        return new ModelAndView("weixin/qrcode/list", "pagination", pagination);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute("qrcode") WeiXinQRCodeCommand command) {
        return new ModelAndView("weixin/qrcode/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage add(@Validated @ModelAttribute("qrcode") WeiXinQRCodeCommand command,
                            BindingResult bindingResult) {
        if (command.getType() == WeiXinQRCodeEntityType.QR_SCENE) {
            if (null == command.getExpireSeconds()
                    || command.getExpireSeconds() < 1
                    || command.getExpireSeconds() > 30) {
                bindingResult.rejectValue("expireSeconds",
                        "WeiXinQRCodeCommand.expireSeconds.MinAndMax", new Object[]{1, 30},
                        getMessageSourceAccessor()
                                .getMessage("WeiXinQRCodeCommand.expireSeconds.MinAndMax", new Object[]{1, 30}));
            }
        }

        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinQRCodeService.save(command);
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
        WeiXinQRCodeEntity entity = weiXinQRCodeService.findByIdAndFetch(id);
        return new ModelAndView("weixin/qrcode/edit", "qrcode", entity);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage edit(@Validated @ModelAttribute("qrcode") WeiXinQRCodeCommand command,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new AlertMessage(AlertMessage.Type.ERROR, null, bindingResult.getFieldErrors());
        }

        try {
            weiXinQRCodeService.update(command);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
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
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {
        WeiXinQRCodeEntity entity = weiXinQRCodeService.findByIdAndFetch(id);
        return new ModelAndView("weixin/qrcode/view", "qrcode", entity);
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id) {
        WeiXinQRCodeEntity entity = weiXinQRCodeService.findById(id);
        return new ModelAndView("weixin/qrcode/view-qrcode", "qrcode", entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinQRCodeService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

}
