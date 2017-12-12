package org.obsidian.tcsp.dto.request;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
public class UpdateInfoRequest {
    String password;
    String email;
    String tel;

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
