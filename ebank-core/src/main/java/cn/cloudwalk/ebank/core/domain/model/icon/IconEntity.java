package cn.cloudwalk.ebank.core.domain.model.icon;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "icon")
public class IconEntity extends AbstractEntity {

    private String          name;                       // 图标名称

    private String          beforeHoverPath;            // hover之前图标路径

    private String          afterHoverPath;             // hover之后图标路径

    private String          suffix;                     // 后缀

    private String          description;                // 描述

    public IconEntity() {
        super();
    }

    public IconEntity(String name, String beforeHoverPath, String afterHoverPath, String suffix,
                      String description) {
        this();
        this.name = name;
        this.beforeHoverPath = beforeHoverPath;
        this.afterHoverPath = afterHoverPath;
        this.suffix = suffix;
        this.description = description;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "before_hover_path")
    public String getBeforeHoverPath() {
        return beforeHoverPath;
    }

    @Column(name = "after_hover_path")
    public String getAfterHoverPath() {
        return afterHoverPath;
    }

    @Column(name = "suffix")
    public String getSuffix() {
        return suffix;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
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

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
