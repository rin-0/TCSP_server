package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.UserMapper;
import org.obsidian.tcsp.model.User;
import org.obsidian.tcsp.model.UserExample;
import org.obsidian.tcsp.util.TokenUtil;
import org.obsidian.tcsp.vo.Token;
import org.obsidian.tcsp.vo.UserBasicInfo;
import org.obsidian.tcsp.vo.request.TokenForReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
@Service
public class UserBasicInfoService {
    @Autowired
    UserMapper userMapper;
    public UserBasicInfo getUserBasicInfoById(int userId){
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()==0){
            return null;
        } else {
            User u=users.get(0);
            UserBasicInfo userBasicInfo = new UserBasicInfo();
            userBasicInfo.setId(u.getId());
            userBasicInfo.setEmail(u.getEmail());
            userBasicInfo.setTel(u.getTel());
            userBasicInfo.setUserName(u.getUserName());
            return userBasicInfo;
        }
    }
    public UserBasicInfo getUserInfoByToken(TokenForReq tokenForReq,boolean hasErrors){
        if(hasErrors){
            return null;
        }
        String encryptedToken = tokenForReq.getToken();
        try {
            Token token = TokenUtil.decryptToken(encryptedToken);
            return this.getUserBasicInfoById(token.getUserId());
        }catch (Exception e){
            System.err.println("[ERROR]无法解算的Token");
            return null;
        }
    }
}
