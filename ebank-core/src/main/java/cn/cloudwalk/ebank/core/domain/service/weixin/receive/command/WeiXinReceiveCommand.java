package cn.cloudwalk.ebank.core.domain.service.weixin.receive.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinReceiveCommand extends AbstractCommand {

    private String msgId;              // 接收消息id

    private String msgType;            // 接收消息类型

    private String content;            // 接收消息内容

    private String fromUserName;       // 来自用户编号

    private String toUserName;         // 目的用户编号

    private String nickname;           // 来自用户的名称

    public String getMsgId() {
        return msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getContent() {
        return content;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
