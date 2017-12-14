package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.obsidian.tcsp.dto.PositionAndRadius;
import org.obsidian.tcsp.dto.RouteIdAndScore;
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

    @Select("SELECT inn.route_id,AVG(score) score FROM (\n" +
            "  SELECT\n" +
            "    routepoint.*,\n" +
            "    AVG(c.score) score\n" +
            "  FROM routepoint\n" +
            "    LEFT JOIN user_routepoint_comment c\n" +
            "      ON (c.routepoint_id = routepoint.id\n" +
            "          AND routepoint.latitude BETWEEN #{latitude} - #{radius} AND #{latitude} + #{radius}\n" +
            "          AND routepoint.longitude BETWEEN #{longitude} - #{radius} AND #{longitude} + #{radius}\n" +
            "      )\n" +
            "  GROUP BY routepoint.id) inn\n" +
            "  GROUP BY inn.route_id\n" +
            "ORDER BY score DESC\n" +
            "LIMIT 100")
    List<RouteIdAndScore> suggestRoute(PositionAndRadius positionAndRadius);
}