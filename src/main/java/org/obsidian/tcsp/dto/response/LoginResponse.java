package org.obsidian.tcsp.dto.response;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
public class LoginResponse {
    int status;
    String token;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
