package org.obsidian.tcsp.controller;

import org.obsidian.tcsp.service.RoutepointService;
import org.obsidian.tcsp.dto.response.RoutepointListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
