package org.obsidian.tcsp.controller;

import org.obsidian.tcsp.service.RouteService;
import org.obsidian.tcsp.vo.RouteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/11
 */
@Controller
public class RouteController {
    @Autowired
    RouteService routeService;
    @ResponseBody
    @RequestMapping(value = "/route/{routeId}/info")
    public RouteInfo getRouteInfoByRouteId(@PathVariable int routeId){
        return routeService.getRoute(routeId);
    }

    @ResponseBody
    @RequestMapping(value = "/route/creatorId/{creatorId}")
    public List<RouteInfo> getAllRouteInfoByCreatorId(@PathVariable int creatorId){
        return routeService.getAllRouteByCreatorId(creatorId);
    }
}
