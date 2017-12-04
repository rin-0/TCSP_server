package org.obsidian.tcsp.vo;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
public class Token {
    int userId;
    long lastLoginTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
