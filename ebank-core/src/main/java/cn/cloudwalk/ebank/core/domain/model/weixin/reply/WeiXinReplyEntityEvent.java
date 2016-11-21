package cn.cloudwalk.ebank.core.domain.model.weixin.reply;

/**
 * Created by liwenhe on 16/11/21.
 */
public enum WeiXinReplyEntityEvent {
    ALL("全部", Boolean.TRUE),
    DEFAULT("默认回复", Boolean.FALSE),
    SUBSCRIBE("关注时回复", Boolean.FALSE),
    KEYWORD("关键词回复", Boolean.FALSE),
    SCENE("场景回复", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinReplyEntityEvent(String name, boolean isOnlyQuery) {
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
