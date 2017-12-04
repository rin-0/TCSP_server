package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.UserMapper;
import org.obsidian.tcsp.model.User;
import org.obsidian.tcsp.model.UserExample;
import org.obsidian.tcsp.util.TokenUtil;
import org.obsidian.tcsp.vo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
@Service
public class UserAccountService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LoginService loginService;

    @Transactional
    public boolean updateUserInfo(String tokenStr,String password,String email,String tel){
        Token token = TokenUtil.decryptToken(tokenStr);
        if (loginService.validateToken(token)){
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(token.getUserId());
            User user = new User();
            user.setPassword(password);
            user.setEmail(email);
            user.setTel(tel);
            int status = userMapper.updateByExampleSelective(user,example);
            if (status == 1){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
