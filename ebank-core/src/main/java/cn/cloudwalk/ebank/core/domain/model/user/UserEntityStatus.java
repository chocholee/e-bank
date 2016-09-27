package cn.cloudwalk.ebank.core.domain.model.user;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public enum UserEntityStatus {
    ALL("全部", Boolean.TRUE),
    ENABLE("启用", Boolean.FALSE),
    DISABLE("禁用", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    UserEntityStatus(String name, boolean isOnlyQuery) {
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
