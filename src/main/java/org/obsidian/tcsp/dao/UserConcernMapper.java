package org.obsidian.tcsp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.obsidian.tcsp.model.UserConcern;
import org.obsidian.tcsp.model.UserConcernExample;
import org.obsidian.tcsp.dto.ConcernUser;

public interface UserConcernMapper {
    long countByExample(UserConcernExample example);

    int deleteByExample(UserConcernExample example);

    int insert(UserConcern record);

    int insertSelective(UserConcern record);

    List<UserConcern> selectByExample(UserConcernExample example);

    int updateByExampleSelective(@Param("record") UserConcern record, @Param("example") UserConcernExample example);

    int updateByExample(@Param("record") UserConcern record, @Param("example") UserConcernExample example);

    @Select("SELECT user.id,user.user_name,count(*) concern_num FROM user,user_concern\n" +
            "INNER JOIN user_concern inner_cnt\n" +
            "WHERE user_concern.self_user_id = #{userId} AND user_concern.concern_user_id = user.id\n" +
            "AND inner_cnt.concern_user_id = user.id\n" +
            "GROUP BY inner_cnt.concern_user_id")
    List<ConcernUser> selectConcernListByUserId(Integer userId);

    @Select("SELECT user.id,user.user_name,(SELECT count(*) FROM user_concern WHERE user.id=user_concern.concern_user_id) concern_num\n" +
            "FROM user\n" +
            "WHERE user.user_name LIKE concat('%',#{userName},'%')\n" +
            "ORDER BY concern_num DESC\n" +
            "LIMIT 100")
    List<ConcernUser> selectBigVByName(String userName);

    @Select("SELECT inner_table.*,count(*) concern_num FROM\n" +
            "  (SELECT user.id,user.user_name FROM user,user_concern\n" +
            "WHERE user_concern.concern_user_id = user.id ) AS inner_table\n" +
            "GROUP BY id\n" +
            "HAVING id NOT IN (SELECT uc.concern_user_id FROM user_concern uc WHERE uc.self_user_id=#{userId} UNION SELECT (#{userId}) concern_user_id)\n" +
            "ORDER BY concern_num DESC\n" +
            "LIMIT 100")
    List<ConcernUser> selectBigV(Integer userId);
}