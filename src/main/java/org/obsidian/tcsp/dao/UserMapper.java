package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.User;
import org.obsidian.tcsp.model.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
}