package cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule;

/**
 * Created by liwenhe on 16/11/25.
 */
public enum WeiXinMenuCustomRuleEntitySex {
    ALL("全部", Boolean.TRUE),
    MALE("男", Boolean.FALSE),
    FEMALE("女", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinMenuCustomRuleEntitySex(String name, boolean isOnlyQuery) {
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
