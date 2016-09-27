package cn.cloudwalk.ebank.core.domain.model.user;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Entity
@Table(name = "user")
public class UserEntity extends AbstractEntity {

    private String              username;               // 用户名

    private String              password;               // 密码

    private String              salt;                   // 密码盐

    private String              realname;               // 真实姓名

    private String              remark;                 // 备注

    private Date                loginDate;              // 登录时间

    private Date                lastLoginDate;          // 上次登录时间

    private Date                createdDate;            // 创建时间

    private Date                updatedDate;            // 更新时间

    private UserEntityStatus    status;                 // 用户状态

    private Set<RoleEntity>     roleEntities;           // 关联角色

    @Id
    @GenericGenerator(name = "user_entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "user_entity_generator")
    @Override
    public String getId() {
        return super.getId();
    }

    @Version
    @Override
    public Integer getVersion() {
        return super.getVersion();
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "salt", nullable = false)
    public String getSalt() {
        return salt;
    }

    @Column(name = "realname")
    public String getRealname() {
        return realname;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "login_date")
    public Date getLoginDate() {
        return loginDate;
    }

    @Column(name = "last_login_date")
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "updated_date")
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public UserEntityStatus getStatus() {
        return status;
    }

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setStatus(UserEntityStatus status) {
        this.status = status;
    }

    public void setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }
}