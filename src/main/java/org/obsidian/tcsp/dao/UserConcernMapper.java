package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.UserConcern;
import org.obsidian.tcsp.model.UserConcernExample;

public interface UserConcernMapper {
    long countByExample(UserConcernExample example);

    int deleteByExample(UserConcernExample example);

    int insert(UserConcern record);

    int insertSelective(UserConcern record);

    List<UserConcern> selectByExample(UserConcernExample example);

    int updateByExampleSelective(@Param("record") UserConcern record, @Param("example") UserConcernExample example);

    int updateByExample(@Param("record") UserConcern record, @Param("example") UserConcernExample example);
}