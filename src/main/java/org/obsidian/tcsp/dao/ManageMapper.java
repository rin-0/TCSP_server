package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.Manage;
import org.obsidian.tcsp.model.ManageExample;

public interface ManageMapper {
    long countByExample(ManageExample example);

    int deleteByExample(ManageExample example);

    int insert(Manage record);

    int insertSelective(Manage record);

    List<Manage> selectByExample(ManageExample example);

    int updateByExampleSelective(@Param("record") Manage record, @Param("example") ManageExample example);

    int updateByExample(@Param("record") Manage record, @Param("example") ManageExample example);
}