package org.obsidian.tcsp.controller;

import org.obsidian.tcsp.service.LoginService;
import org.obsidian.tcsp.dto.request.TokenForReq;
import org.obsidian.tcsp.dto.response.LoginResponse;
import org.obsidian.tcsp.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
@Controller
@RequestMapping(value = "/account")
public class LoginController {
    @Autowired
    LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest, Errors errors){
        return loginService.login(loginRequest,errors.hasErrors());
    }

    @ResponseBody
    @RequestMapping(value = "/loginByToken",method = RequestMethod.POST)
    public LoginResponse checkToken(@RequestBody @Valid TokenForReq tokenForReq, Errors errors){
        return loginService.checkToken(tokenForReq,errors.hasErrors());
    }
}
