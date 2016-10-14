package cn.cloudwalk.ebank.core.domain.service.icon.command;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liwenhe on 2016/10/13.
 *
 * @author 李文禾
 */
public class IconCommand extends AbstractCommand {

    private String          id;

    @NotBlank(message = "{IconCommand.name.NotBlank}")
    private String          name;                       // 图标名称

    @NotBlank(message = "{IconCommand.beforeHoverPath.NotBlank}")
    private String          beforeHoverPath;            // hover之前图标路径

    @NotBlank(message = "{IconCommand.afterHoverPath.NotBlank}")
    private String          afterHoverPath;             // hover之后图标路径

    private String          description;                // 描述

    @NotNull(message = "{IconCommand.type.NotBlank}")
    private IconEntityType  type;                       // 图标类型

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBeforeHoverPath() {
        return beforeHoverPath;
    }

    public String getAfterHoverPath() {
        return afterHoverPath;
    }

    public String getDescription() {
        return description;
    }

    public IconEntityType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeforeHoverPath(String beforeHoverPath) {
        this.beforeHoverPath = beforeHoverPath;
    }

    public void setAfterHoverPath(String afterHoverPath) {
        this.afterHoverPath = afterHoverPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(IconEntityType type) {
        this.type = type;
    }
}
