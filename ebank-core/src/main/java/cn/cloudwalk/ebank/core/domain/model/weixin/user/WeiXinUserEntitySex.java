package cn.cloudwalk.ebank.core.domain.model.weixin.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwenhe on 16/11/29.
 */
public enum WeiXinUserEntitySex {
    ALL("全部", Boolean.TRUE),
    UNKNOWN("未知", Boolean.FALSE),
    MALE("男", Boolean.FALSE),
    FEMALE("女", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinUserEntitySex(String name, boolean isOnlyQuery) {
        this.name = name;
        this.isOnlyQuery = isOnlyQuery;
    }

    public String getName() {
        return name;
    }

    public boolean isOnlyQuery() {
        return isOnlyQuery;
    }

    private static Map<Integer, WeiXinUserEntitySex> caches;

    static {
        caches = new HashMap<>(WeiXinUserEntitySex.values().length * 2);
        for (WeiXinUserEntitySex sex : WeiXinUserEntitySex.values()) {
            caches.put(sex.ordinal() - 1, sex);
        }
    }

    public static WeiXinUserEntitySex byWeiXinValue(int ordinal) {
        return caches.get(ordinal);
    }

}
