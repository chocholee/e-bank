package cn.cloudwalk.ebank.core.support.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by liwenhe on 2016/10/13.
 *
 * @author 李文禾
 */
public class OverwriteTag extends BodyTagSupport {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        return isOverrided() ? SKIP_BODY : EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        if(isOverrided()) {
            return EVAL_PAGE;
        }
        BodyContent b = getBodyContent();
        String varName = TagUtils.getOverrideVariableName(name);
        pageContext.getRequest().setAttribute(varName, b.getString());
        return EVAL_PAGE;
    }

    private boolean isOverrided() {
        String varName = TagUtils.getOverrideVariableName(name);
        return pageContext.getRequest().getAttribute(varName) != null;
    }

}
