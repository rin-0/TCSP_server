package org.obsidian.tcsp.dto.response;

import org.obsidian.tcsp.dto.RoutepointEx;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/11/30
 */
public class RoutepointListResponse {
    List<RoutepointEx> routepointList;

    public List<RoutepointEx> getRoutepointList() {
        return routepointList;
    }

    public void setRoutepointList(List<RoutepointEx> routepointList) {
        this.routepointList = routepointList;
    }
}
