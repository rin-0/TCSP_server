package org.obsidian.tcsp.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
public class TokenForReq {
    @NotNull
    @Size(min = 8)
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
