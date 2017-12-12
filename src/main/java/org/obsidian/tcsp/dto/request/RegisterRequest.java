package org.obsidian.tcsp.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Rin
 * @Date 2017/11/29
 */
public class RegisterRequest {
    @NotNull
    @Size(min = 2,max=40)
    String userName;

    @NotNull
    @Size(min = 2,max=40)
    String password;

    String email;
    String tel;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
