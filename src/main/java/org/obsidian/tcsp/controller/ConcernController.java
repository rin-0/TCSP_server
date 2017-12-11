package org.obsidian.tcsp.controller;

import org.obsidian.tcsp.service.ConcernService;
import org.obsidian.tcsp.util.TokenUtil;
import org.obsidian.tcsp.vo.ConcernUser;
import org.obsidian.tcsp.vo.Token;
import org.obsidian.tcsp.vo.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
@Controller
public class ConcernController {
    @Autowired
    ConcernService concernService;

    @ResponseBody
    @RequestMapping(value = "/account/my/concernList")
    public List<ConcernUser> getConcernList(@CookieValue("token") String tokenStr){
        Token token = TokenUtil.decryptToken(tokenStr);
        return concernService.getConcernListByUserId(token.getUserId());
    }

    @ResponseBody
    @RequestMapping(value = "/concern/add/{targetId}")
    public Status addConcern(@CookieValue("token") String tokenStr,@PathVariable int targetId){
        Token token = TokenUtil.decryptToken(tokenStr);
        int status = concernService.addConcern(token.getUserId(),targetId);
        return new Status(status);
    }
    @ResponseBody
    @RequestMapping(value = "/concern/remove/{targetId}")
    public Status removeConcern(@CookieValue("token") String tokenStr,@PathVariable int targetId){
        Token token = TokenUtil.decryptToken(tokenStr);
        int status = concernService.removeConcern(token.getUserId(),targetId);
        return new Status(status);
    }
    @ResponseBody
    @RequestMapping(value = "/suggestion/bigVList")
    public List<ConcernUser> getBigVList(@CookieValue("token") String tokenStr){
        Token token = TokenUtil.decryptToken(tokenStr);
        return concernService.getBigVList(token.getUserId());
    }

    @ResponseBody
    @RequestMapping(value = "/search/user")
    public List<ConcernUser> searchUserByName(String name){
        return concernService.searchUser(name);
    }
}
