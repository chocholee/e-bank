package cn.cloudwalk.ebank.web.controller.shared;

import cn.cloudwalk.ebank.core.support.editor.DoubleEditor;
import cn.cloudwalk.ebank.core.support.editor.FloatEditor;
import cn.cloudwalk.ebank.core.support.editor.IntegerEditor;
import cn.cloudwalk.ebank.core.support.editor.LongEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Controller
public class BaseController {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Int
        binder.registerCustomEditor(int.class, new IntegerEditor());
        binder.registerCustomEditor(Integer.class, new IntegerEditor());
        // Long
        binder.registerCustomEditor(long.class, new LongEditor());
        binder.registerCustomEditor(Long.class, new LongEditor());
        // Float
        binder.registerCustomEditor(float.class, new FloatEditor());
        binder.registerCustomEditor(Float.class, new FloatEditor());
        // Double
        binder.registerCustomEditor(double.class, new DoubleEditor());
        binder.registerCustomEditor(Double.class, new DoubleEditor());
    }

    public MessageSourceAccessor getMessageSourceAccessor() {
        return messageSourceAccessor;
    }

}
