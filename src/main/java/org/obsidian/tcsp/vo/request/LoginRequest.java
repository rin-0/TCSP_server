package org.obsidian.tcsp.vo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
public class LoginRequest {
    @NotNull
    @Size(min = 2,max=40)
    String userName;

    @NotNull
    @Size(min = 2,max=40)
    String password;

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
}
