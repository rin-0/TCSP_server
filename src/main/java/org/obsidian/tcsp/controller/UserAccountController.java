package org.obsidian.tcsp.controller;

import org.obsidian.tcsp.service.RegisterService;
import org.obsidian.tcsp.service.UserAccountService;
import org.obsidian.tcsp.service.UserBasicInfoService;
import org.obsidian.tcsp.dto.UserBasicInfo;
import org.obsidian.tcsp.dto.request.RegisterRequest;
import org.obsidian.tcsp.dto.request.TokenForReq;
import org.obsidian.tcsp.dto.request.UpdateInfoRequest;
import org.obsidian.tcsp.dto.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
@Controller
@RequestMapping(value = "/account")
public class UserAccountController {
    @Autowired
    UserBasicInfoService userBasicInfoService;

    @Autowired
    RegisterService registerService;

    @Autowired
    UserAccountService userAccountService;

    @ResponseBody
    @RequestMapping(value = "/{userId}/basicInfo")
    public UserBasicInfo getUserBasicInfoById(@PathVariable int userId){
        return userBasicInfoService.getUserBasicInfoById(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfoByToken",method = RequestMethod.POST)
    public UserBasicInfo getUserInfoByToken(@RequestBody @Valid TokenForReq tokenForReq, Errors errors){
        return userBasicInfoService.getUserInfoByToken(tokenForReq,errors.hasErrors());
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public LoginResponse register(@RequestBody @Valid RegisterRequest registerRequest, Errors errors){
        return registerService.register(registerRequest,errors.hasErrors());
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public Integer updateUserInfo(@RequestBody UpdateInfoRequest updateInfoRequest,@CookieValue(value = "token") String tokenStr){
        boolean status = userAccountService.updateUserInfo(tokenStr,updateInfoRequest.getPassword(),updateInfoRequest.getEmail(),updateInfoRequest.getTel());
        if (status){
            return 1;
        }else {
            return 0;
        }
    }
}
