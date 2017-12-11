package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.UserConcernMapper;
import org.obsidian.tcsp.model.UserConcern;
import org.obsidian.tcsp.model.UserConcernExample;
import org.obsidian.tcsp.vo.ConcernUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
@Service
public class ConcernService {
    @Autowired
    UserConcernMapper userConcernMapper;
    public List<ConcernUser> getConcernListByUserId(int userId){
        return userConcernMapper.selectConcernListByUserId(userId);
    }

    public List<ConcernUser> getBigVList(int userId){
        return userConcernMapper.selectBigV(userId);
    }

    //这个搜索用户功能，结果是按关注人数降序排列的，只显示前100名
    public List<ConcernUser> searchUser(String keyWordUserName){
        return userConcernMapper.selectBigVByName(keyWordUserName);
    }

    public int addConcern(int selfId,int targetId){
        UserConcern userConcern = new UserConcern();
        userConcern.setSelfUserId(selfId);
        userConcern.setConcernUserId(targetId);
        int status = 0;
        try {
            status = userConcernMapper.insert(userConcern);
        }catch (DuplicateKeyException e){
            status = 0;
        }

        return status;
    }

    public int removeConcern(int selfId,int targetId){
        UserConcernExample example = new UserConcernExample();
        example.createCriteria()
                .andSelfUserIdEqualTo(selfId)
                .andConcernUserIdEqualTo(targetId);
        int status = userConcernMapper.deleteByExample(example);
        return status;
    }
}
