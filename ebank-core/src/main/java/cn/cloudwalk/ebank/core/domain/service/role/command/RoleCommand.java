package cn.cloudwalk.ebank.core.domain.service.role.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by liwenhe on 2016/9/29.
 *
 * @author 李文禾
 */
public class RoleCommand extends AbstractCommand {

    private String id;

    @NotBlank(message = "{RoleCommand.name.NotBlank}")
    private String name;                    // 角色名称

    private String description;             // 角色描述

    private Integer order;                  // 角色排序

    private RoleCommand parent;             // 父角色

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getOrder() {
        return order;
    }

    public RoleCommand getParent() {
        return parent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setParent(RoleCommand parent) {
        this.parent = parent;
    }
}
