package cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule;

/**
 * Created by liwenhe on 16/11/25.
 */
public enum WeiXinMenuCustomRuleEntityLanguage {
    ALL("全部", Boolean.TRUE),
    zh_CN("简体中文", Boolean.FALSE),
    zh_TW("繁体中文TW", Boolean.FALSE),
    zh_HK("繁体中文HK", Boolean.FALSE),
    en("英文 ", Boolean.FALSE),
    id("印尼", Boolean.FALSE),
    ms("马来 ", Boolean.FALSE),
    es("西班牙", Boolean.FALSE),
    ko("韩国", Boolean.FALSE),
    it("意大利", Boolean.FALSE),
    ja("日本", Boolean.FALSE),
    pl("波兰", Boolean.FALSE),
    pt("葡萄牙", Boolean.FALSE),
    ru("俄国", Boolean.FALSE),
    th("泰文", Boolean.FALSE),
    vi("越南", Boolean.FALSE),
    ar("阿拉伯语", Boolean.FALSE),
    hi("北印度", Boolean.FALSE),
    he("希伯来", Boolean.FALSE),
    tr("土耳其", Boolean.FALSE),
    de("德语", Boolean.FALSE),
    fr("法语", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    WeiXinMenuCustomRuleEntityLanguage(String name, boolean isOnlyQuery) {
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
