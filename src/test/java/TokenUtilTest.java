import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.obsidian.tcsp.util.TokenUtil;
import org.obsidian.tcsp.vo.Token;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author Rin
 * @Date 2017/11/28
 */
public class TokenUtilTest {
    @Test
    public void decryptTest(){
        Token token = TokenUtil.decryptToken("bcVODLNkM1AMi0e7NtBgEC2K6dgzY2sYlG15gy6RKS3MSaal1Yn1q6lpkMj1oV7Z");
        System.out.println(JSON.toJSONString(token));
    }
}
