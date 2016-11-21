package cn.cloudwalk.ebank.core.domain.model.weixin.reply;

/**
 * Created by liwenhe on 16/11/21.
 */
public enum WeiXinReplyEntityType {
    ALL("全部", Boolean.TRUE),
    TEXT("文本回复", Boolean.FALSE),
    IMAGE("图片回复", Boolean.FALSE),
    NEWS("图文回复", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinReplyEntityType(String name, boolean isOnlyQuery) {
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
