package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.tcsp.model.UserRoutepointComment;
import org.obsidian.tcsp.model.UserRoutepointCommentExample;

public interface UserRoutepointCommentMapper {
    long countByExample(UserRoutepointCommentExample example);

    int deleteByExample(UserRoutepointCommentExample example);

    int insert(UserRoutepointComment record);

    int insertSelective(UserRoutepointComment record);

    List<UserRoutepointComment> selectByExampleWithBLOBs(UserRoutepointCommentExample example);

    List<UserRoutepointComment> selectByExample(UserRoutepointCommentExample example);

    int updateByExampleSelective(@Param("record") UserRoutepointComment record, @Param("example") UserRoutepointCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") UserRoutepointComment record, @Param("example") UserRoutepointCommentExample example);

    int updateByExample(@Param("record") UserRoutepointComment record, @Param("example") UserRoutepointCommentExample example);
}