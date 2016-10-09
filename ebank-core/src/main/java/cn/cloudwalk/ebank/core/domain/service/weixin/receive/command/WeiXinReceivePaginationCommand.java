package cn.cloudwalk.ebank.core.domain.service.weixin.receive.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinReceivePaginationCommand extends AbstractPaginationCommand {

    private String nickname;

    private String content;

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
