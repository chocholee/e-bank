package cn.cloudwalk.ebank.core.domain.model.role;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "role")
public class RoleEntity extends AbstractEntity {

    private String              name;                   // 角色名称

    private String              description;            // 角色描述

    private Integer             order;                  // 角色排序

    private RoleEntity          parent;                 // 父角色

    private List<UserEntity> userEntities;              // 关联用户

    private List<FunctionEntity> functionEntities;      // 关联资源

    public RoleEntity() {
        super();
    }

    public RoleEntity(String name, String description, Integer order, RoleEntity parent, List<FunctionEntity> functionEntities) {
        this();
        this.name = name;
        this.description = description;
        this.order = order;
        this.parent = parent;
        this.functionEntities = functionEntities;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "`order`")
    public Integer getOrder() {
        return order;
    }

    @OneToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    public RoleEntity getParent() {
        return parent;
    }

    @ManyToMany(mappedBy = "roleEntities",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    @ManyToMany
    @JoinTable(name = "role_function",
            joinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "function_id", referencedColumnName = "id")
    )
    public List<FunctionEntity> getFunctionEntities() {
        return functionEntities;
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

    public void setParent(RoleEntity parent) {
        this.parent = parent;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public void setFunctionEntities(List<FunctionEntity> functionEntities) {
        this.functionEntities = functionEntities;
    }
}
