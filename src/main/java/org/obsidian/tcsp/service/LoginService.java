package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.UserMapper;
import org.obsidian.tcsp.model.User;
import org.obsidian.tcsp.model.UserExample;
import org.obsidian.tcsp.util.TokenUtil;
import org.obsidian.tcsp.dto.Token;
import org.obsidian.tcsp.dto.request.LoginRequest;
import org.obsidian.tcsp.dto.request.TokenForReq;
import org.obsidian.tcsp.dto.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public LoginResponse login(LoginRequest loginRequest){
        return this.login(loginRequest,false);
    }

    public LoginResponse login(LoginRequest loginRequest,boolean hasErrors){
        LoginResponse loginResponse = new LoginResponse();
        if (hasErrors){
            loginResponse.setStatus(0);
        } else {
            UserExample example = new UserExample();
            example.createCriteria()
                    .andUserNameEqualTo(loginRequest.getUserName())
                    .andPasswordEqualTo(loginRequest.getPassword());
            List<User> users = userMapper.selectByExample(example);
            if(users.size()==0){
                loginResponse.setStatus(0);
            } else {
                loginResponse.setStatus(1);
                User user = users.get(0);
                Token token = new Token();
                token.setUserId(user.getId());
                token.setLastLoginTime(System.currentTimeMillis());
                String encryptedToken = TokenUtil.encryptToken(token);
                redisTemplate.opsForValue().set(Integer.toString(token.getUserId()),Long.toString(token.getLastLoginTime()));
                loginResponse.setToken(encryptedToken);
            }
        }
        return loginResponse;
    }

    //检查Token的有效性及是否过期
    public LoginResponse checkToken(TokenForReq tokenForReq, boolean hasErrors){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus(0);
        if (hasErrors) return loginResponse;
        String encryptedToken = tokenForReq.getToken();
        boolean isGood = this.validateToken(encryptedToken);
        if (isGood){
            loginResponse.setStatus(1);
            loginResponse.setToken(encryptedToken);
        }
        return loginResponse;
    }

    public boolean validateToken(String tokenStr){
        try{
            Token token = TokenUtil.decryptToken(tokenStr);
            return this.validateToken(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateToken(Token token){
        try{
            int userId = token.getUserId();
            String lastLoginTimeStr = redisTemplate.opsForValue().get(Integer.toString(userId));
            if (lastLoginTimeStr!=null){
                long lastLoginTimeRedis = Long.parseLong(lastLoginTimeStr);
                if (lastLoginTimeRedis==token.getLastLoginTime()){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
