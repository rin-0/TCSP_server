package org.obsidian.tcsp.dto.request;

/**
 * @Author Rin
 * @Date 2017/12/12
 */
public class CommentAndScore {
    int score;
    String comment;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
