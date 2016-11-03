package cn.cloudwalk.ebank.core.support.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * Created by liwenhe on 2016/11/3.
 *
 * @author 李文禾
 */
public class IntegerEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
