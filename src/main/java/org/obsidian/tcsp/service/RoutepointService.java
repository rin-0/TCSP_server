package org.obsidian.tcsp.service;

import org.obsidian.tcsp.dao.RouteMapper;
import org.obsidian.tcsp.dao.RoutepointMapper;
import org.obsidian.tcsp.dao.UserRoutepointCommentMapper;
import org.obsidian.tcsp.dto.CommentEx;
import org.obsidian.tcsp.dto.PositionAndRadius;
import org.obsidian.tcsp.dto.RoutepointEx;
import org.obsidian.tcsp.dto.response.RoutepointListResponse;
import org.obsidian.tcsp.model.RouteExample;
import org.obsidian.tcsp.model.Routepoint;
import org.obsidian.tcsp.model.RoutepointExample;
import org.obsidian.tcsp.model.UserRoutepointComment;
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
    RouteMapper routeMapper;

    @Autowired
    RoutepointMapper routepointMapper;

    @Autowired
    UserRoutepointCommentMapper userRoutepointCommentMapper;

    public RoutepointListResponse getRoutepointListByPosition(double latitude, double longitude, double radius){
        RoutepointListResponse routepointListResponse = new RoutepointListResponse();
        List<RoutepointEx> routepointList = routepointMapper.selectByPositionAndRadius(new PositionAndRadius(latitude,longitude,radius));
        routepointListResponse.setRoutepointList(routepointList);
        return routepointListResponse;
    }

    public RoutepointEx getRoutepointDetailById(int id){
        RoutepointEx routepointEx = routepointMapper.selectDetailById(id).get(0);
        if(routepointEx.getScore()==null){
            routepointEx.setScore(3.0);
        }
        List<CommentEx> commentExList = userRoutepointCommentMapper.selectExByRoutepointId(id);
        routepointEx.setCommentList(commentExList);
        return routepointEx;
    }

    public int saveFeel(String feel,int routepointId,int userId){
        Routepoint routepoint = new Routepoint();
        routepoint.setFeel(feel);

        RoutepointExample example = new RoutepointExample();
        example.createCriteria()
                .andIdEqualTo(routepointId);
        int routeId = routepointMapper.selectByExampleWithBLOBs(example).get(0).getRouteId();

        //检查route的创建者是不是这个用户
        RouteExample routeExample = new RouteExample();
        routeExample.createCriteria()
                .andIdEqualTo(routeId)
                .andCreatorIdEqualTo(userId);
        int size = routeMapper.selectByExample(routeExample).size();

        if(size>0){
            return routepointMapper.updateByExampleSelective(routepoint,example);
        }
        return 0;
    }

    public int commentAndScore(String comment,int score,int userId,int routepointId){
        UserRoutepointComment userRoutepointComment = new UserRoutepointComment();
        userRoutepointComment.setContent(comment);
        userRoutepointComment.setScore(score);
        userRoutepointComment.setUserId(userId);
        userRoutepointComment.setRoutepointId(routepointId);
        userRoutepointComment.setTime(System.currentTimeMillis());
        try {
            return userRoutepointCommentMapper.insertSelective(userRoutepointComment);
        }catch (Exception e){
            return 0;
        }
    }
}
