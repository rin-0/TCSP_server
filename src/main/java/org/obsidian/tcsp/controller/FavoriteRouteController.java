package org.obsidian.tcsp.controller;

import org.obsidian.tcsp.service.LoginService;
import org.obsidian.tcsp.service.RouteService;
import org.obsidian.tcsp.service.UserAccountService;
import org.obsidian.tcsp.util.TokenUtil;
import org.obsidian.tcsp.vo.FavoriteRoute;
import org.obsidian.tcsp.vo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
@Controller
public class FavoriteRouteController {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    LoginService loginService;
    @Autowired
    RouteService routeService;

    @ResponseBody
    @RequestMapping(value = "/account/my/favoriteRoute")
    public List<FavoriteRoute> getFavoriteRoute(@CookieValue("token") String tokenStr){
        Token token = TokenUtil.decryptToken(tokenStr);
        return routeService.getFavoriteRoute(token.getUserId());
    }
}
