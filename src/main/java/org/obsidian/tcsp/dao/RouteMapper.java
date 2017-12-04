package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.Route;
import org.obsidian.tcsp.model.RouteExample;

public interface RouteMapper {
    long countByExample(RouteExample example);

    int deleteByExample(RouteExample example);

    int insert(Route record);

    int insertSelective(Route record);

    List<Route> selectByExample(RouteExample example);

    int updateByExampleSelective(@Param("record") Route record, @Param("example") RouteExample example);

    int updateByExample(@Param("record") Route record, @Param("example") RouteExample example);
}