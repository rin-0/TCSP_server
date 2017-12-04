package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.UserRoutepointScore;
import org.obsidian.tcsp.model.UserRoutepointScoreExample;

public interface UserRoutepointScoreMapper {
    long countByExample(UserRoutepointScoreExample example);

    int deleteByExample(UserRoutepointScoreExample example);

    int insert(UserRoutepointScore record);

    int insertSelective(UserRoutepointScore record);

    List<UserRoutepointScore> selectByExample(UserRoutepointScoreExample example);

    int updateByExampleSelective(@Param("record") UserRoutepointScore record, @Param("example") UserRoutepointScoreExample example);

    int updateByExample(@Param("record") UserRoutepointScore record, @Param("example") UserRoutepointScoreExample example);
}