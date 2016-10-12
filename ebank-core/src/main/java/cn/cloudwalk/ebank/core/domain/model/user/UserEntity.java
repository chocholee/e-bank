package cn.cloudwalk.ebank.core.domain.model.user;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;
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

    private String              phone;                  // 手机号码

    private String              email;                  // 邮箱

    private String              remark;                 // 备注

    private Integer             loginCount;             // 登录次数

    private Date                loginDate;              // 登录时间

    private Date                lastLoginDate;          // 上次登录时间

    private Date                createdDate;            // 创建时间

    private Date                updatedDate;            // 更新时间

    private UserEntityStatus    status;                 // 用户状态

    private UserEntity          parent;                 // 父用户

    private Set<RoleEntity>     roleEntities;           // 关联角色

    public UserEntity() {
        super();
    }

    public UserEntity(String username, String password, String salt, String realname, String phone, String email,
                      String remark, Integer loginCount, Date loginDate, Date lastLoginDate, Date createdDate,
                      Date updatedDate, UserEntityStatus status, UserEntity parent, Set<RoleEntity> roleEntities) {
        this();
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.realname = realname;
        this.phone = phone;
        this.email = email;
        this.remark = remark;
        this.loginCount = loginCount;
        this.loginDate = loginDate;
        this.lastLoginDate = lastLoginDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
        this.parent = parent;
        this.roleEntities = roleEntities;
    }

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

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "login_count")
    public Integer getLoginCount() {
        return loginCount;
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

    @OneToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    public UserEntity getParent() {
        return parent;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
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

    public void setParent(UserEntity parent) {
        this.parent = parent;
    }

    public void setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }
}
