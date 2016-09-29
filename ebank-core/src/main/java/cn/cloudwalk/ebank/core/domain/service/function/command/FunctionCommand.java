package cn.cloudwalk.ebank.core.domain.service.function.command;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liwenhe on 2016/9/29.
 *
 * @author 李文禾
 */
public class FunctionCommand extends AbstractCommand {

    private String              id;

    @NotBlank(message = "{FunctionCommand.name.NotBlank}")
    private String              name;                   // 功能名称

    private String              code;                   // 功能编码

    private String              uri;                    // 功能标识

    private String              iconId;                 // 功能图标

    private String              description;            // 功能描述

    private Integer             order;                  // 功能排序

    @NotNull(message = "{FunctionCommand.type.NotNull}")
    private FunctionEntityType  type;                   // 功能类型

    private FunctionCommand     parent;                 // 关联父资源

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getUri() {
        return uri;
    }

    public String getIconId() {
        return iconId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getOrder() {
        return order;
    }

    public FunctionEntityType getType() {
        return type;
    }

    public FunctionCommand getParent() {
        return parent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setType(FunctionEntityType type) {
        this.type = type;
    }

    public void setParent(FunctionCommand parent) {
        this.parent = parent;
    }
}
