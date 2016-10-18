package cn.cloudwalk.ebank.core.support.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by liwenhe on 2016/10/13.
 *
 * @author 李文禾
 */
public class BlockTag extends TagSupport {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return EVAL_BODY_INCLUDE or EVAL_BODY_BUFFERED or SKIP_BODY
     */
    @Override
    public int doStartTag() throws JspException {
        return getOverrideContent() == null ? EVAL_BODY_INCLUDE : SKIP_BODY;
    }

    /**
     * @return EVAL_PAGE or SKIP_PAGE
     */
    @Override
    public int doEndTag() throws JspException {
        String overrideContent = getOverrideContent();
        if(overrideContent == null) {
            return EVAL_PAGE;
        }

        try {
            pageContext.getOut().write(overrideContent);
        } catch (IOException e) {
            throw new JspException("write overrideContent occer IOException,block name:" + name, e);
        }
        return EVAL_PAGE;
    }

    private String getOverrideContent() {
        String varName = TagUtils.getOverrideVariableName(name);
        return (String) pageContext.getRequest().getAttribute(varName);
    }

}
