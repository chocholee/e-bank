package cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule;

/**
 * Created by liwenhe on 16/11/25.
 */
public enum WeiXinMenuCustomRuleEntityStatus {
    ALL("全部", Boolean.TRUE),
    NOT_SYNC("未同步", Boolean.FALSE),
    EDITED_SYNC("已修改需再次同步", Boolean.FALSE),
    SYNCED("已同步", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinMenuCustomRuleEntityStatus(String name, boolean isOnlyQuery) {
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
