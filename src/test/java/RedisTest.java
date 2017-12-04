import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @Author Rin
 * @Date 2017/11/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-redis.xml")
//@Transactional
public class RedisTest {
    @Autowired
    private RedisTemplate<String,String> template;

    @Test
    public void testRedis(){
        System.out.println("hha");
        template.opsForValue().set("zhang33", JSON.toJSONString(new User(123,"张三")));
        String user = template.opsForValue().get("zhang33");
        System.out.println(user);
    }
}

class User implements Serializable {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}