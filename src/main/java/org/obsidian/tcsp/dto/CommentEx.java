package org.obsidian.tcsp.dto;

import org.obsidian.tcsp.model.UserRoutepointComment;

/**
 * @Author Rin
 * @Date 2017/12/12
 */
public class CommentEx extends UserRoutepointComment {
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
