package cn.cloudwalk.ebank.core.domain.service.user.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;

import java.util.Date;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public class UserEditCommand extends AbstractCommand {

    private String id;

    private String realname;

    private String phone;

    private String email;

    private String remark;

    private Date   lastLoginDate;

    private Date   loginDate;

    private UserEditCommand parent;

    public String getId() {
        return id;
    }

    public String getRealname() {
        return realname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRemark() {
        return remark;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public UserEditCommand getParent() {
        return parent;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public void setParent(UserEditCommand parent) {
        this.parent = parent;
    }
}
