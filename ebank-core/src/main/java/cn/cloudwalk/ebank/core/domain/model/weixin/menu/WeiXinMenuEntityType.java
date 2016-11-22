package cn.cloudwalk.ebank.core.domain.model.weixin.menu;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public enum WeiXinMenuEntityType {
    ALL("全部", Boolean.TRUE),
    CLICK("关键字", Boolean.FALSE),
    VIEW("网页链接", Boolean.FALSE),
    SCANCODE_PUSH("弹出扫码功能", Boolean.FALSE),
    SCANCODE_WAITMSG("弹出扫码功能并返回信息", Boolean.FALSE),
    PIC_SYSPHOTO("弹出系统拍照", Boolean.FALSE),
    PIC_PHOTO_OR_ALBUM("弹出拍照或者相册发图", Boolean.FALSE),
    PIC_WEIXIN("弹出微信相册发图器", Boolean.FALSE),
    LOCATION_SELECT("弹出地理位置选择器", Boolean.FALSE),
    MEDIA_ID("下发消息", Boolean.FALSE),
    VIEW_LIMITED("跳转图文消息URL", Boolean.FALSE);

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
