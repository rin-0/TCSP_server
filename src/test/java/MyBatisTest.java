import org.junit.Test;
import org.junit.runner.RunWith;
import org.obsidian.tcsp.dao.*;
import org.obsidian.tcsp.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-dao.xml")
public class MyBatisTest {
    @Autowired
    UserRouteFavoriteMapper userRouteFavoriteMapper;
    @Autowired
    UserConcernMapper userConcernMapper;
    @Autowired
    RoutepointMapper routepointMapper;
    @Autowired
    UserRoutepointCommentMapper userRoutepointCommentMapper;
    @Autowired
    RouteMapper routeMapper;

    @Test
    public void testUserRouteFavoriteMapper(){
        List<FavoriteRoute> favoriteRouteList = userRouteFavoriteMapper.selectFavoriteRoutesByUserId(1);
        System.out.println(favoriteRouteList);
    }

    @Test
    public void testSearchBigVByName(){
        List<ConcernUser> concernUserList = userConcernMapper.selectBigVByName("a");
        System.out.println(concernUserList);
    }

    @Test
    public void testRoutepointDetail(){
        List<RoutepointEx> routepointExList = routepointMapper.selectDetailById(1);
        System.out.println(routepointExList);
    }

    @Test
    public void testCommentEx(){
        List<CommentEx> list = userRoutepointCommentMapper.selectExByRoutepointId(1);
    }

    @Test
    public void testSuggestRouteByAverageScoreAndPosition(){
        List<RouteIdAndScore> list= routeMapper.suggestRoute(new PositionAndRadius(30,120,5));
    }
}
