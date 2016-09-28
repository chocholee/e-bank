package cn.cloudwalk.ebank.core.domain.model.icon;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public enum IconEntityType {
    ALL("全部", Boolean.TRUE),
    SYS("系统图标", Boolean.FALSE),
    MENU("菜单图标", Boolean.FALSE),
    BUTTON("按扭图标", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    IconEntityType(String name, boolean isOnlyQuery) {
        this.name = name;
        this.isOnlyQuery = isOnlyQuery;
    }

    public String getName() {
        return name;
    }

    public boolean isOnlyQuery() {
        return isOnlyQuery;
    }
}
