package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.UserRouteFavoriteMapper;
import org.obsidian.tcsp.vo.FavoriteRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
@Service
public class RouteService {
    @Autowired
    UserRouteFavoriteMapper userRouteFavoriteMapper;
    public List<FavoriteRoute> getFavoriteRoute(int userId){
        return userRouteFavoriteMapper.selectFavoriteRoutesByUserId(userId);
    }
}
