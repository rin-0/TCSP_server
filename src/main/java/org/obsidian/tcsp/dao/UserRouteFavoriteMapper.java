package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.UserRouteFavorite;
import org.obsidian.tcsp.model.UserRouteFavoriteExample;
import org.obsidian.tcsp.vo.FavoriteRoute;

public interface UserRouteFavoriteMapper {
    long countByExample(UserRouteFavoriteExample example);

    int deleteByExample(UserRouteFavoriteExample example);

    int insert(UserRouteFavorite record);

    int insertSelective(UserRouteFavorite record);

    List<UserRouteFavorite> selectByExample(UserRouteFavoriteExample example);

    int updateByExampleSelective(@Param("record") UserRouteFavorite record, @Param("example") UserRouteFavoriteExample example);

    int updateByExample(@Param("record") UserRouteFavorite record, @Param("example") UserRouteFavoriteExample example);

    List<FavoriteRoute> selectFavoriteRoutesByUserId(Integer userId);
}