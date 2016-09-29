package cn.cloudwalk.ebank.core.domain.model.function;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public enum FunctionEntityType {
    ALL("全部", Boolean.TRUE),
    FIRST("一级菜单", Boolean.FALSE),
    SECOND("二级菜单", Boolean.FALSE),
    THIRD("三级菜单", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    FunctionEntityType(String name, boolean isOnlyQuery) {
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
