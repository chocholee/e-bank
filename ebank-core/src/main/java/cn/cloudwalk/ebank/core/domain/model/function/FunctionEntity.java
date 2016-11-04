package cn.cloudwalk.ebank.core.domain.model.function;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "function")
public class FunctionEntity extends AbstractEntity {

    private String              name;                   // 名称

    private String              code;                   // 编码

    private String              uri;                    // 标识

    private String              description;            // 描述

    private Integer             order;                  // 排序

    private FunctionEntityType  type;                   // 类型

    private FunctionEntity      parent;                 // 关联父资源

    private IconEntity          iconEntity;             // 图标

    private List<RoleEntity> roleEntities;              // 关联角色

    public FunctionEntity() {
        super();
    }

    public FunctionEntity(String name, String code, String uri, String description, Integer order, FunctionEntityType type,
                          FunctionEntity parent, IconEntity iconEntity, List<RoleEntity> roleEntities) {
        this();
        this.name = name;
        this.code = code;
        this.uri = uri;
        this.description = description;
        this.order = order;
        this.type = type;
        this.parent = parent;
        this.iconEntity = iconEntity;
        this.roleEntities = roleEntities;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "`order`")
    public Integer getOrder() {
        return order;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    public FunctionEntityType getType() {
        return type;
    }

    @OneToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    public FunctionEntity getParent() {
        return parent;
    }

    @OneToOne
    @JoinColumn(name = "icon", referencedColumnName = "id")
    public IconEntity getIconEntity() {
        return iconEntity;
    }

    @ManyToMany(mappedBy = "functionEntities", cascade = CascadeType.ALL)
    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setType(FunctionEntityType type) {
        this.type = type;
    }

    public void setParent(FunctionEntity parent) {
        this.parent = parent;
    }

    public void setIconEntity(IconEntity iconEntity) {
        this.iconEntity = iconEntity;
    }

    public void setRoleEntities(List<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }
}
