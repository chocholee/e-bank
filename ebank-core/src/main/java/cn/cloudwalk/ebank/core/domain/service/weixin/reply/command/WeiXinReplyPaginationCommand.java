package cn.cloudwalk.ebank.core.domain.service.weixin.reply.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.reply.WeiXinReplyEntityEvent;
import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 16/11/21.
 */
public class WeiXinReplyPaginationCommand extends AbstractPaginationCommand {

    private WeiXinReplyEntityEvent event;

    public WeiXinReplyEntityEvent getEvent() {
        return event;
    }

    public void setEvent(WeiXinReplyEntityEvent event) {
        this.event = event;
    }

}
