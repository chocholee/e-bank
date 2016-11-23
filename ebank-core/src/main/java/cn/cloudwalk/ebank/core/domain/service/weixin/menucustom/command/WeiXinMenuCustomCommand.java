package cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by liwenhe on 16/11/22.
 */
public class WeiXinMenuCustomCommand extends AbstractCommand {

    private String id;

    @NotBlank(message = "{WeiXinMenuCustomCommand.name.NotBlank}")
    private String name;

    private String remark;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
