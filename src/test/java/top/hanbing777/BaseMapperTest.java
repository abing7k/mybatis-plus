package top.hanbing777;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.mapper.UserMapper;
import top.hanbing777.pojo.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BaseMapperTest {


    @Autowired
    private UserMapper userMapper;


    //查询所有信息
    @Test
    public void testSelectList() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    //根据id查询数据
    public void testSelectById(){
        User user = userMapper.selectById(2);
        System.out.println(user);
    }


    @Test
    //根据对个id查询多个用户信息
    public void testSelectBatchIds(){
        List<Integer> integers = Arrays.asList(1,4, 5);
        List<User> users = userMapper.selectBatchIds(integers);
        for (User user : users) {
            System.out.println(user);
        }
    }


    //通过map条件查询用户信息
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("age",18);
        map.put("name","韩冰");
        map.put("id",1);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.err.println(user);
        }
    }


    //自定义(和普通的mybatis一样)
    @Test
    public void testSelectMapById(){
        Map<String, Object> map = userMapper.selectMapById(1);
        System.out.println(map);
    }



    //插入一条信息
    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(18);
        user.setName("a1");
        user.setEmail(null);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }


    //通过id删除信息
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(1L);
        System.err.println(i);
    }


    @Test
    //通过id批量删除信息
    public void testDeleteBatchIds(){

        List<Integer> integers = Arrays.asList(1, 2, 3);

        int i = userMapper.deleteBatchIds(integers);
        System.err.println(i);
    }

    @Test
    //通过map条件删除对应的信息
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",4);
        map.put("name","Sandy");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    @Test
    //修改
    public void testUpdateById(){
        User user = new User();
        user.setAge(18);
        user.setName("韩冰");
        user.setEmail("742608569@qq.com");
        user.setId(1l);
        userMapper.updateById(user);

    }


}
