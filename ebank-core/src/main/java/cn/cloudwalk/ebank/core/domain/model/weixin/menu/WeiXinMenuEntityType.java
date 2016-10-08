package cn.cloudwalk.ebank.core.domain.model.weixin.menu;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public enum WeiXinMenuEntityType {
    ALL("全部", Boolean.TRUE),
    CLICK("消息触发", Boolean.FALSE),
    VIEW("网页链接", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinMenuEntityType(String name, boolean isOnlyQuery) {
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
