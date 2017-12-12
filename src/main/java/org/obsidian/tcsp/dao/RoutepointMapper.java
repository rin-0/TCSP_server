package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
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

    List<RoutepointEx> selectByPositionAndRadius(PositionAndRadius positionAndRadius);
}