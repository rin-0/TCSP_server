package org.obsidian.tcsp.model;

public class UserRoutepointComment {
    private Integer id;

    private Integer userId;

    private Integer routepointId;

    private Integer score;

    private Long time;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}