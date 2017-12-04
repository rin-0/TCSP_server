package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.Push;
import org.obsidian.tcsp.model.PushExample;

public interface PushMapper {
    long countByExample(PushExample example);

    int deleteByExample(PushExample example);

    int insert(Push record);

    int insertSelective(Push record);

    List<Push> selectByExampleWithBLOBs(PushExample example);

    List<Push> selectByExample(PushExample example);

    int updateByExampleSelective(@Param("record") Push record, @Param("example") PushExample example);

    int updateByExampleWithBLOBs(@Param("record") Push record, @Param("example") PushExample example);

    int updateByExample(@Param("record") Push record, @Param("example") PushExample example);
}