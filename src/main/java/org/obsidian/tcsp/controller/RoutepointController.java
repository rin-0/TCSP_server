package org.obsidian.tcsp.controller;

import org.obsidian.tcsp.dto.RoutepointEx;
import org.obsidian.tcsp.dto.Token;
import org.obsidian.tcsp.dto.request.CommentAndScore;
import org.obsidian.tcsp.dto.request.SaveFeelRequest;
import org.obsidian.tcsp.dto.response.Status;
import org.obsidian.tcsp.service.RoutepointService;
import org.obsidian.tcsp.dto.response.RoutepointListResponse;
import org.obsidian.tcsp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Rin
 * @Date 2017/11/30
 */
@Controller
public class RoutepointController {
    @Autowired
    RoutepointService routepointService;

    @ResponseBody
    @RequestMapping(value = "/map/getRoutepointListByPosition/latitude/{latitude}/longitude/{longitude}/radius/{radius}")
    public RoutepointListResponse getRoutepointListByPosition(@PathVariable double latitude,
                                                              @PathVariable double longitude,
                                                              @PathVariable double radius){
        return routepointService.getRoutepointListByPosition(latitude,longitude,radius);
    }

    @ResponseBody
    @RequestMapping(value = "/routepoint/{routepointId}")
    public RoutepointEx getRoutepointDetailById(@PathVariable int routepointId){
        return routepointService.getRoutepointDetailById(routepointId);
    }

    @ResponseBody
    @RequestMapping(value = "/routepoint/{routepointId}/feel/save")
    public Status saveFeel(@PathVariable int routepointId, @CookieValue(name = "token") String tokenStr, @RequestBody SaveFeelRequest saveFeelRequest){
        Token token = TokenUtil.decryptToken(tokenStr);
        if (token==null){
            return new Status(0);
        }
        String feel = saveFeelRequest.getContent();
        return new Status(routepointService.saveFeel(feel,routepointId,token.getUserId()));
    }

    @ResponseBody
    @RequestMapping(value = "/routepoint/{routepointId}/sendComment")
    public Status commentAndScore(@PathVariable int routepointId,
                                  @CookieValue(name = "token") String tokenStr,
                                  @RequestBody CommentAndScore commentAndScore){
        Token token = TokenUtil.decryptToken(tokenStr);
        if (token==null){
            return new Status(0);
        }
        return new Status(routepointService.commentAndScore(commentAndScore.getComment(),
                commentAndScore.getScore(),
                token.getUserId(),
                routepointId));
    }
}
