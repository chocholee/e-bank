package cn.cloudwalk.ebank.core.domain.model.weixin.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwenhe on 16/11/29.
 */
public enum WeiXinUserEntitySubscribeType {
    ALL("全部", Boolean.TRUE),
    NOT_SUBSCRIBE("未关注", Boolean.FALSE),
    SUBSCRIBED("已关注", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinUserEntitySubscribeType(String name, boolean isOnlyQuery) {
        this.name = name;
        this.isOnlyQuery = isOnlyQuery;
    }

    public String getName() {
        return name;
    }

    public boolean isOnlyQuery() {
        return isOnlyQuery;
    }

    private static Map<Integer, WeiXinUserEntitySubscribeType> caches;

    static {
        caches = new HashMap<>(WeiXinUserEntitySubscribeType.values().length * 2);
        for (WeiXinUserEntitySubscribeType subscribeType : WeiXinUserEntitySubscribeType.values()) {
            caches.put(subscribeType.ordinal() - 1, subscribeType);
        }
    }

    public static WeiXinUserEntitySubscribeType byWeiXinValue(int ordinal) {
        return caches.get(ordinal);
    }

}
