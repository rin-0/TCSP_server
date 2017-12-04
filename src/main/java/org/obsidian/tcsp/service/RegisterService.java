package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.UserMapper;
import org.obsidian.tcsp.model.User;
import org.obsidian.tcsp.vo.request.LoginRequest;
import org.obsidian.tcsp.vo.request.RegisterRequest;
import org.obsidian.tcsp.vo.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * @Author Rin
 * @Date 2017/11/29
 */
@Service
public class RegisterService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    LoginService loginService;

    public LoginResponse register(RegisterRequest registerRequest, boolean hasErrors) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus(0);
        if (hasErrors){
            return loginResponse;
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setTel(registerRequest.getTel());
        int effect = userMapper.insertSelective(user);
        if(effect==0){
            return loginResponse;
        } else {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUserName(user.getUserName());
            loginRequest.setPassword(user.getPassword());
            return loginService.login(loginRequest);
        }
    }
}
