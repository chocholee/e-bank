package cn.cloudwalk.ebank.core.domain.service.weixin.group.command;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liwenhe on 16/11/23.
 */
public class WeiXinGroupCommand extends AbstractCommand {

    private String                  id;

    @NotBlank(message = "{WeiXinGroupCommand.name.NotBlank}")
    private String                  name;               // 组名称

    private String                  remark;             // 备注

    @NotNull(message = "{WeiXinGroupCommand.type.NotNull}")
    private WeiXinGroupEntityType   type;               // 组类型

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public WeiXinGroupEntityType getType() {
        return type;
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

    public void setType(WeiXinGroupEntityType type) {
        this.type = type;
    }

}
