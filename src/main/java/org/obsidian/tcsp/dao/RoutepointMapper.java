package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.obsidian.tcsp.model.Routepoint;
import org.obsidian.tcsp.model.RoutepointExample;
import org.obsidian.tcsp.dto.PositionAndRadius;
import org.obsidian.tcsp.dto.RoutepointEx;

public interface RoutepointMapper {
    long countByExample(RoutepointExample example);

    int deleteByExample(RoutepointExample example);

    int insert(Routepoint record);

    int insertSelective(Routepoint record);

    List<Routepoint> selectByExampleWithBLOBs(RoutepointExample example);

    List<Routepoint> selectByExample(RoutepointExample example);

    int updateByExampleSelective(@Param("record") Routepoint record, @Param("example") RoutepointExample example);

    int updateByExampleWithBLOBs(@Param("record") Routepoint record, @Param("example") RoutepointExample example);

    int updateByExample(@Param("record") Routepoint record, @Param("example") RoutepointExample example);

    @Select("SELECT longitude,latitude,AVG(score) score FROM routepoint,user_routepoint_comment\n" +
            "    WHERE routepoint.id = user_routepoint_comment.routepoint_id\n" +
            "    GROUP BY routepoint.id")
    List<RoutepointEx> selectByPositionAndRadius(PositionAndRadius positionAndRadius);

    @Select("SELECT routepoint.*,\n" +
            "  (SELECT count(*) FROM user_routepoint_comment c1 WHERE c1.routepoint_id=routepoint.id) comment_num,\n" +
            "  (SELECT AVG(c2.score) FROM user_routepoint_comment c2 WHERE c2.routepoint_id=routepoint.id) score\n" +
            "FROM routepoint WHERE routepoint.id=#{id}")
    List<RoutepointEx> selectDetailById(Integer id);
}