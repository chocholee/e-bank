package cn.cloudwalk.ebank.core.domain.service.weixin.keyword.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinKeywordPaginationCommand extends AbstractPaginationCommand {

    private String keyword;            // 关键字

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
