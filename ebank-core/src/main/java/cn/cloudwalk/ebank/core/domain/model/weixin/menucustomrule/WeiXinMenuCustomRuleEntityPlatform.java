package cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule;

/**
 * Created by liwenhe on 16/11/25.
 */
public enum WeiXinMenuCustomRuleEntityPlatform {
    ALL("全部", Boolean.TRUE),
    IOS("IOS系统", Boolean.FALSE),
    ANDROID("Android系统", Boolean.FALSE),
    OTHERS("其它", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinMenuCustomRuleEntityPlatform(String name, boolean isOnlyQuery) {
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
