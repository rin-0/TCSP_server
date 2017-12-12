package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.obsidian.tcsp.model.UserRouteFavorite;
import org.obsidian.tcsp.model.UserRouteFavoriteExample;
import org.obsidian.tcsp.dto.FavoriteRoute;

public interface UserRouteFavoriteMapper {
    long countByExample(UserRouteFavoriteExample example);

    int deleteByExample(UserRouteFavoriteExample example);

    int insert(UserRouteFavorite record);

    int insertSelective(UserRouteFavorite record);

    List<UserRouteFavorite> selectByExample(UserRouteFavoriteExample example);

    int updateByExampleSelective(@Param("record") UserRouteFavorite record, @Param("example") UserRouteFavoriteExample example);

    int updateByExample(@Param("record") UserRouteFavorite record, @Param("example") UserRouteFavoriteExample example);

    @Select("SELECT route.id,route.name,route.time,route.cover_pic,user.user_name creator_name FROM route,user_route_favorite,user\n" +
            "WHERE route.id=user_route_favorite.route_id AND user_route_favorite.user_id=#{userId} AND user.id=route.creator_id")
    List<FavoriteRoute> selectFavoriteRoutesByUserId(Integer userId);
}