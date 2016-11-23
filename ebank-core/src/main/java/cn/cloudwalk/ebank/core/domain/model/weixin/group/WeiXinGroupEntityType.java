package cn.cloudwalk.ebank.core.domain.model.weixin.group;

/**
 * Created by liwenhe on 16/11/23.
 */
public enum WeiXinGroupEntityType {
    ALL("全部", Boolean.TRUE),
    WECHAT("微信分组", Boolean.FALSE),
    VIRTUAL("虚拟分组", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinGroupEntityType(String name, boolean isOnlyQuery) {
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
