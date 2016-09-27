package cn.cloudwalk.ebank.core.domain.model.resource;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "resource")
public class ResourceEntity extends AbstractEntity {

    private String              name;                   // 资源名称

    private String              uri;                    // 资源标识

    private String              icon;                   // 资源图标

    private String              description;            // 资源描述

    private Integer             order;                  // 资源排序

    private ResourceEntityType  type;                   // 资源类型

    private ResourceEntity      parent;                 // 关联父资源

    private Set<RoleEntity>     roleEntities;           // 关联角色

    @Id
    @GenericGenerator(name = "resource_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "resource_entity_generator")
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

    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    @Column(name = "icon")
    public String getIcon() {
        return icon;
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
    public ResourceEntityType getType() {
        return type;
    }

    @OneToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    public ResourceEntity getParent() {
        return parent;
    }

    @ManyToMany(mappedBy = "resourceEntities", cascade = CascadeType.ALL)
    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setType(ResourceEntityType type) {
        this.type = type;
    }

    public void setParent(ResourceEntity parent) {
        this.parent = parent;
    }

    public void setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }
}
