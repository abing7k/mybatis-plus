package top.hanbing777;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.enums.SexEnums;
import top.hanbing777.mapper.UserMapper;
import top.hanbing777.pojo.User;

@SpringBootTest
public class EnumTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testSexEnum(){
        User user = new User();
        user.setAge(20);
        user.setName("韩冰666");
        user.setSex(SexEnums.MALE);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
}
