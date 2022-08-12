package top.hanbing777;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.mapper.UserMapper;
import top.hanbing777.pojo.User;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisxTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test01(){

        Map<String, Object> stringObjectMap = userMapper.selectNameById(1L);
        System.out.println(stringObjectMap);

    }

}
