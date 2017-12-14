package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.RouteMapper;
import org.obsidian.tcsp.dao.RoutepointMapper;
import org.obsidian.tcsp.dao.UserRouteFavoriteMapper;
import org.obsidian.tcsp.dto.*;
import org.obsidian.tcsp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;
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

    public List<RouteInfo> getSuggestRouteList(PositionAndRadius positionAndRadius){
        List<RouteIdAndScore> suggestRouteIdAndScoreList = routeMapper.suggestRoute(positionAndRadius);
        List<RouteInfo> suggestList = new ArrayList<RouteInfo>(suggestRouteIdAndScoreList.size());
        for (RouteIdAndScore idAndScore:suggestRouteIdAndScoreList) {
            RouteInfo routeInfo = this.getRoute(idAndScore.getRouteId());
            suggestList.add(routeInfo);
        }
        return suggestList;
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

    public int addToFavorites(int routeId,int userId){
        UserRouteFavorite userRouteFavorite = new UserRouteFavorite();
        userRouteFavorite.setRouteId(routeId);
        userRouteFavorite.setUserId(userId);
        userRouteFavorite.setTime(System.currentTimeMillis());
        int status=0;
        try {
            status = userRouteFavoriteMapper.insert(userRouteFavorite);
        }catch (DuplicateKeyException e){
            System.out.println("[ERROR]===添加收藏失败，重复的添加操作===");
        }
        return status;
    }

    public int removeFromFavorites(int routeId,int userId){
        UserRouteFavoriteExample example = new UserRouteFavoriteExample();
        example.createCriteria()
                .andRouteIdEqualTo(routeId)
                .andUserIdEqualTo(userId);
        return userRouteFavoriteMapper.deleteByExample(example);
    }

    public RouteInfo createRoute(Part coverPic,String title,List<Routepoint> routepointList,int creatorId,String storagePath){
        String fileName = coverPic.getSubmittedFileName();
        String fileNameExt = fileName.substring(fileName.lastIndexOf('.'));
        long time = System.currentTimeMillis();
        String localFileName = Long.toString(time).concat(fileNameExt);
        try {
            coverPic.write(storagePath + localFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Route route = new Route();
        route.setCoverPic(localFileName);
        route.setCreatorId(creatorId);
        route.setName(title);
        route.setTime(time);
        int status = routeMapper.insertSelective(route);
        if (status == 0){
            System.err.println("[ERROR]===新建行程失败===");
            return null;
        }

        for(Routepoint routepoint:routepointList){
            routepoint.setRouteId(route.getId());
            routepointMapper.insertSelective(routepoint);
        }

        return this.getRoute(route.getId());
    }
}
