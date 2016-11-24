package cn.cloudwalk.ebank.core.domain.model.weixin.qrcode;

/**
 * Created by liwenhe on 16/11/24.
 */
public enum WeiXinQRCodeEntityType {
    ALL("全部", Boolean.TRUE),
    QR_SCENE("临时二维码", Boolean.FALSE),
    QR_LIMIT_STR_SCENE("永久的字符串", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinQRCodeEntityType(String name, boolean isOnlyQuery) {
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
