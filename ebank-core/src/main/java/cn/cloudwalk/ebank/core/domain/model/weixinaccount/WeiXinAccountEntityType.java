package cn.cloudwalk.ebank.core.domain.model.weixinaccount;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
public enum WeiXinAccountEntityType {
    ALL("全部", Boolean.TRUE),
    SERVICE("服务号", Boolean.FALSE),
    SUBSCRIPTION("订阅号", Boolean.FALSE),
    ENTERPRISE("企业号", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinAccountEntityType(String name, boolean isOnlyQuery) {
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
