package cn.cloudwalk.ebank.core.domain.model.weixin.account;

/**
 * Created by sherw on 2016/11/4.
 */
public enum WeiXinAccountEntityStatus {
    ALL("全部", Boolean.TRUE),
    AUTHORIZED("已授权", Boolean.FALSE),
    UNAUTHORIZED("未授权", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinAccountEntityStatus(String name, boolean isOnlyQuery) {
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
