package org.obsidian.tcsp.controller;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.alibaba.fastjson.JSON;
import org.obsidian.tcsp.dto.PositionAndRadius;
import org.obsidian.tcsp.dto.Token;
import org.obsidian.tcsp.dto.response.Status;
import org.obsidian.tcsp.model.Routepoint;
import org.obsidian.tcsp.service.RouteService;
import org.obsidian.tcsp.dto.RouteInfo;
import org.obsidian.tcsp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
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

    @ResponseBody
    @RequestMapping(value = "/route/{routeId}/addToFavorites")
    public Status addToFavorites(@PathVariable int routeId, @CookieValue(name = "token") String tokenStr){
        Token token;
        try {
             token = TokenUtil.decryptToken(tokenStr);
        }catch (Exception e){
            return new Status(0);
        }
        return new Status(routeService.addToFavorites(routeId,token.getUserId()));
    }

    @ResponseBody
    @RequestMapping(value = "/route/{routeId}/removeFromFavorites")
    public Status removeFromFavorites(@PathVariable int routeId, @CookieValue(name = "token") String tokenStr){
        Token token = TokenUtil.decryptToken(tokenStr);
        if (token==null){
            return new Status(0);
        }
        return new Status(routeService.removeFromFavorites(routeId,token.getUserId()));
    }

    @ResponseBody
    @RequestMapping(value = "/route/suggest/latitude/{latitude}/longitude/{longitude}/radius/{radius}")
    public List<RouteInfo> suggestRoutes(@PathVariable Double latitude,
                                         @PathVariable Double longitude,
                                         @PathVariable Double radius){
        return routeService.getSuggestRouteList(new PositionAndRadius(latitude,longitude,radius));
    }

    //新建行程
    @ResponseBody
    @RequestMapping(value = "/route/create",method = RequestMethod.POST)
    public RouteInfo createRoute(HttpServletRequest request,
                              @RequestPart("coverPic") Part coverPic,
                              String title,
                              String routepointList,
                              @CookieValue(name = "token") String tokenStr){
        Token token;
        try {
            token = TokenUtil.decryptToken(tokenStr);
        }catch (Exception e){
            return null;
        }
        int userId = token.getUserId();
        List<Routepoint> routepointList0 = JSON.parseArray(routepointList,Routepoint.class);
        String root=request.getServletContext().getRealPath("/uploads/cover/");
        RouteInfo routeInfo = routeService.createRoute(coverPic,title,routepointList0,userId,root);

        return routeInfo;
    }
}
