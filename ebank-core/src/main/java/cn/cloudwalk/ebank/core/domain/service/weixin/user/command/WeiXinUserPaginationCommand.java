package cn.cloudwalk.ebank.core.domain.service.weixin.user.command;

import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 16/11/29.
 */
public class WeiXinUserPaginationCommand extends AbstractPaginationCommand {

    private String nickname;

    private String groupWechatId;

    private String groupVirtualId;

    public String getNickname() {
        return nickname;
    }

    public String getGroupWechatId() {
        return groupWechatId;
    }

    public String getGroupVirtualId() {
        return groupVirtualId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGroupWechatId(String groupWechatId) {
        this.groupWechatId = groupWechatId;
    }

    public void setGroupVirtualId(String groupVirtualId) {
        this.groupVirtualId = groupVirtualId;
    }

}
