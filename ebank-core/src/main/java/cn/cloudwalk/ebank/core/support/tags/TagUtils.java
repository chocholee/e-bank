package cn.cloudwalk.ebank.core.support.tags;

/**
 * Created by liwenhe on 2016/10/13.
 *
 * @author 李文禾
 */
public class TagUtils {

    public static String BLOCK = "__jsp_override__";

    static String getOverrideVariableName(String name) {
        return BLOCK + name;
    }

}
