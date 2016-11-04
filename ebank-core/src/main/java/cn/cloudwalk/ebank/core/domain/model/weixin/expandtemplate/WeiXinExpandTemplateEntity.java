package cn.cloudwalk.ebank.core.domain.model.weixin.expandtemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "weixin_template_expand")
public class WeiXinExpandTemplateEntity extends AbstractEntity {

    private String              name;                       // 扩展信息模板名称

    private String              keyword;                    // 扩展信息模板关键字

    private String              description;                // 扩展信息模板描述

    private String              classname;                  // 扩展信息模板完整类名称

    private WeiXinAccountEntity accountEntity;              // 关联公众号

    public WeiXinExpandTemplateEntity() {
        super();
    }

    public WeiXinExpandTemplateEntity(String name, String keyword, String description, String classname,
                                      WeiXinAccountEntity accountEntity) {
        this();
        this.name = name;
        this.keyword = keyword;
        this.description = description;
        this.classname = classname;
        this.accountEntity = accountEntity;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "classname")
    public String getClassname() {
        return classname;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", nullable = false)
    public WeiXinAccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setAccountEntity(WeiXinAccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
