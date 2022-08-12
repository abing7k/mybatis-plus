package top.hanbing777;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.pojo.User;

import java.util.ArrayList;

@SpringBootTest
public class ServiceMapperTest {

    @Autowired
    private ServiceImpl service;

    @Test
    //测试查询记录数
    public void testGetCount(){
        long count = service.count();
        System.out.println(count);
    }

    //测试批量插入
    @Test
    public void testSaveBatch(){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("韩冰"+i);
            user.setAge(20+i);
            users.add(user);
        }
        service.saveBatch(users);

    }

}
