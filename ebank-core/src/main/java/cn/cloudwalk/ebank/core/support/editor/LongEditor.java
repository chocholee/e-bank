package cn.cloudwalk.ebank.core.support.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * Created by liwenhe on 2016/11/3.
 *
 * @author 李文禾
 */
public class LongEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(Long.parseLong(text));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
