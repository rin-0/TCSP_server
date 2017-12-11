package org.obsidian.tcsp.vo;

import org.obsidian.tcsp.model.Route;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/11
 */
public class RouteInfo extends Route {
    public RouteInfo(){

    }
    public RouteInfo(Route route){
        this.setId(route.getId());
        this.setName(route.getName());
        this.setCreatorId(route.getCreatorId());
        this.setCoverPic(route.getCoverPic());
        this.setTime(route.getTime());
    }

    List<SimpleRoutepoint> routepointList;

    public List<SimpleRoutepoint> getRoutepointList() {
        return routepointList;
    }

    public void setRoutepointList(List<SimpleRoutepoint> routepointList) {
        this.routepointList = routepointList;
    }
}
