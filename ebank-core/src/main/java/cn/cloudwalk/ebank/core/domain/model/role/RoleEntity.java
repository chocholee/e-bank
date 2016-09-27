package cn.cloudwalk.ebank.core.domain.model.role;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import cn.cloudwalk.ebank.core.domain.model.resource.ResourceEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

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

    private Set<UserEntity>     userEntities;           // 关联用户

    private Set<ResourceEntity> resourceEntities;       // 关联资源

    @Id
    @GenericGenerator(name = "role_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "role_entity_generator")
    @Override
    public String getId() {
        return super.getId();
    }

    @Version
    @Override
    public Integer getVersion() {
        return super.getVersion();
    }

    @Column(name = "name", nullable = false)
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

    @ManyToMany(mappedBy = "roleEntities", cascade = CascadeType.ALL)
    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    @ManyToMany
    @JoinTable(name = "role_resource",
            joinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "resource_id", referencedColumnName = "id")
    )
    public Set<ResourceEntity> getResourceEntities() {
        return resourceEntities;
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

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public void setResourceEntities(Set<ResourceEntity> resourceEntities) {
        this.resourceEntities = resourceEntities;
    }
}
