package cn.cloudwalk.ebank.web.controller.weixin.receive;

import cn.cloudwalk.ebank.core.domain.model.weixin.receive.WeiXinReceiveEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.receive.IWeiXinReceiveService;
import cn.cloudwalk.ebank.core.domain.service.weixin.receive.command.WeiXinReceivePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.web.controller.shared.AlertMessage;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import com.arm4j.weixin.exception.WeiXinRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liwenhe on 16/11/16.
 */
@Controller
@RequestMapping("/weixin/receive")
public class WeiXinReceiveController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinReceiveService weiXinReceiveService;

    @RequestMapping("/list")
    public ModelAndView pagination(@ModelAttribute("receive") WeiXinReceivePaginationCommand command) {
        Pagination<WeiXinReceiveEntity> pagination = weiXinReceiveService.pagination(command);
        return new ModelAndView("weixin/receive/list", "pagination", pagination);
    }

    @RequestMapping(value = "/reply/{id}", method = RequestMethod.GET)
    public ModelAndView reply(@PathVariable String id) {
        return new ModelAndView("weixin/receive/reply", "id", id);
    }

    @RequestMapping(value = "/reply/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AlertMessage reply(@PathVariable String id, String content) {
        try {
            weiXinReceiveService.reply(id, content);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.edit.success.message"));
        } catch (WeiXinRequestException e) {
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("WeiXinRequestException.message"));
        } catch (Exception e) {
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.edit.failure.message"));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertMessage delete(@PathVariable String id) {
        try {
            weiXinReceiveService.delete(id);
            return new AlertMessage(AlertMessage.Type.SUCCESS,
                    getMessageSourceAccessor().getMessage("default.delete.success.message"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AlertMessage(AlertMessage.Type.ERROR,
                    getMessageSourceAccessor().getMessage("default.delete.failure.message"));
        }
    }

}
