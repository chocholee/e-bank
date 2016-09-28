package cn.cloudwalk.ebank.core.domain.model.icon;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "icon")
public class IconEntity extends AbstractEntity {

    private String          name;                       // 图标名称

    private String          path;                       // 图标路径

    private String          suffix;                     // 后缀

    private String          description;                // 描述

    private IconEntityType  type;                       // 图标类型

    @Id
    @GenericGenerator(name = "icon_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "icon_entity_generator")
    @Override
    public String getId() {
        return super.getId();
    }

    @Version
    @Override
    public Integer getVersion() {
        return super.getVersion();
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    @Column(name = "suffix")
    public String getSuffix() {
        return suffix;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public IconEntityType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(IconEntityType type) {
        this.type = type;
    }
}
