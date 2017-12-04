package org.obsidian.tcsp.model;

public class UserRoutepointScore {
    private Integer userId;

    private Integer routepointId;

    private Integer score;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoutepointId() {
        return routepointId;
    }

    public void setRoutepointId(Integer routepointId) {
        this.routepointId = routepointId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}