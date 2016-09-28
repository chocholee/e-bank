package cn.cloudwalk.ebank.web.controller.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Controller
public class BaseController {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    public MessageSourceAccessor getMessageSourceAccessor() {
        return messageSourceAccessor;
    }

}
