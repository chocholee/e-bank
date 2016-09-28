package cn.cloudwalk.ebank.core.domain.service.user.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public class UserAddCommand extends AbstractCommand {

    @NotBlank(message = "{UserAddCommand.username.NotBlank}")
    private String username;

    @NotBlank(message = "{UserAddCommand.password.NotBlank}")
    private String password;

    private String realname;

    private String phone;

    private String email;

    private String remark;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
