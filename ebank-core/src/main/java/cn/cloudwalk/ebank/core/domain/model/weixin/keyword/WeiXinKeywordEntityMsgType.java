package cn.cloudwalk.ebank.core.domain.model.weixin.keyword;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public enum WeiXinKeywordEntityMsgType {
    ALL("全部", Boolean.TRUE),
    TEXT("文本", Boolean.FALSE),
    NEWS("图文", Boolean.FALSE),
    EXPAND("扩展", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinKeywordEntityMsgType(String name, boolean isOnlyQuery) {
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
