package org.obsidian.tcsp.dto;

import org.obsidian.tcsp.model.Routepoint;

/**
 * @Author Rin
 * @Date 2017/11/30
 */
public class RoutepointEx extends Routepoint {
    Double score;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
