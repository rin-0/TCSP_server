import org.junit.Test;
import org.junit.runner.RunWith;
import org.obsidian.tcsp.dao.UserConcernMapper;
import org.obsidian.tcsp.dao.UserRouteFavoriteMapper;
import org.obsidian.tcsp.dto.ConcernUser;
import org.obsidian.tcsp.dto.FavoriteRoute;
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
}
