package cn.cloudwalk.ebank.core.domain.model.weixin.account;

/**
 * Created by liwenhe on 16/11/11.
 */
public enum WeiXinAccountEntityActivated {

    ALL("全部", Boolean.TRUE),
    ACTIVATED("激活", Boolean.FALSE),
    NONACTIVATED("未激活", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinAccountEntityActivated(String name, boolean isOnlyQuery) {
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
