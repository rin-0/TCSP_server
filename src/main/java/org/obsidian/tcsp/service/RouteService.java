package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.RouteMapper;
import org.obsidian.tcsp.dao.RoutepointMapper;
import org.obsidian.tcsp.dao.UserRouteFavoriteMapper;
import org.obsidian.tcsp.model.Route;
import org.obsidian.tcsp.model.RouteExample;
import org.obsidian.tcsp.model.Routepoint;
import org.obsidian.tcsp.model.RoutepointExample;
import org.obsidian.tcsp.vo.FavoriteRoute;
import org.obsidian.tcsp.vo.RouteInfo;
import org.obsidian.tcsp.vo.SimpleRoutepoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
@Service
public class RouteService {
    @Autowired
    UserRouteFavoriteMapper userRouteFavoriteMapper;
    @Autowired
    RouteMapper routeMapper;
    @Autowired
    RoutepointMapper routepointMapper;
    public List<FavoriteRoute> getFavoriteRoute(int userId){
        return userRouteFavoriteMapper.selectFavoriteRoutesByUserId(userId);
    }

    //获取某行程概述
    public RouteInfo getRoute(int routeId){
        RouteExample routeExample = new RouteExample();
        routeExample.createCriteria()
                .andIdEqualTo(routeId);
        Route route = routeMapper.selectByExample(routeExample).get(0);
        RouteInfo routeInfo = new RouteInfo(route);

        RoutepointExample routepointExample = new RoutepointExample();
        routepointExample.createCriteria()
                .andRouteIdEqualTo(routeId);
        List<Routepoint> routepointModelList =routepointMapper.selectByExampleWithBLOBs(routepointExample);
        List<SimpleRoutepoint> simpleRoutepointList = new ArrayList<SimpleRoutepoint>(routepointModelList.size());
        for (Routepoint routepoint:routepointModelList) {
            SimpleRoutepoint simpleRoutepoint = new SimpleRoutepoint();
            simpleRoutepoint.setId(routepoint.getId());
            simpleRoutepoint.setName(routepoint.getName());
            simpleRoutepointList.add(simpleRoutepoint);
        }
        routeInfo.setRoutepointList(simpleRoutepointList);
        return routeInfo;
    }

    //获取某人的全部行程
    public List<RouteInfo> getAllRouteByCreatorId(int userId){
        RouteExample example = new RouteExample();
        example.createCriteria()
                .andCreatorIdEqualTo(userId);
        List<Route> routeList = routeMapper.selectByExample(example);
        List<RouteInfo> routeInfoList=new ArrayList<RouteInfo>(routeList.size());
        for (Route route:routeList) {
            routeInfoList.add(getRoute(route.getId()));
        }
        return routeInfoList;
    }
}
