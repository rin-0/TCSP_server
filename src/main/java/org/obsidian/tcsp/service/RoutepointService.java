package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.RoutepointMapper;
import org.obsidian.tcsp.dto.PositionAndRadius;
import org.obsidian.tcsp.dto.RoutepointEx;
import org.obsidian.tcsp.dto.response.RoutepointListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/11/30
 */
@Service
public class RoutepointService {
    @Autowired
    RoutepointMapper routepointMapper;
    public RoutepointListResponse getRoutepointListByPosition(double latitude, double longitude, double radius){
        RoutepointListResponse routepointListResponse = new RoutepointListResponse();
        List<RoutepointEx> routepointList = routepointMapper.selectByPositionAndRadius(new PositionAndRadius(latitude,longitude,radius));
        routepointListResponse.setRoutepointList(routepointList);
        return routepointListResponse;
    }
}
