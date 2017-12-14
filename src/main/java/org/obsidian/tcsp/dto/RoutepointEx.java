package org.obsidian.tcsp.dto;

import org.obsidian.tcsp.model.Routepoint;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/11/30
 */
public class RoutepointEx extends Routepoint {
    Double score;

    Integer commentNum;

    List<CommentEx> commentList;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public List<CommentEx> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentEx> commentList) {
        this.commentList = commentList;
    }
}
