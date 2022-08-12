package top.hanbing777;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.mapper.UserMapper;
import top.hanbing777.pojo.User;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class QueryWrapperTest {

    @Autowired
    UserMapper userMapper;


    @Test
    public void test01() {
        //查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
        //SELECT id AS uid,name,age,email,is_deleted FROM user
        // WHERE is_deleted=0 AND (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test02() {
        //按年龄降序查询用户，如果年龄相同则按id升序排列
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03() {
        //删除email为空的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int delete = userMapper.delete(queryWrapper);
        System.out.println(delete);
    }

    @Test
    public void test04() {
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age", 20)
                .like("name", "a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("韩冰666");
        user.setEmail("123456");
        user.setAge(18);
        int update = userMapper.update(user, userQueryWrapper);
        System.out.println(update);
    }

    @Test
    public void test05() {
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("韩冰666");
        user.setEmail("123456");
        user.setAge(18);
        int update = userMapper.update(user, userQueryWrapper);
        System.out.println(update);
    }

    @Test
    public void test06() {
        //查询用户信息的username和age字段
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name", "age");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        maps.forEach(System.out::println);
    }

    //实现子查询
    @Test
    public void test07() {
        //查询id小于等于3的用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("id", "SELECT id from user WHERE age<20");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }


    //UpdateWrapper
    @Test
    public void test08() {
        //将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
        // 组装set子句以及修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .set("name", "aaa")
                .set("email", "742608569@qq.com")
                .like("name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println(update);
    }

    /**
     * 在真正开发的过程中，组装条件是常见的功能，而这些条件数据来源于用户输入，是可选的，因
     * 此我们在组装这些条件时，必须先判断用户是否选择了这些条件，若选择则需要组装该条件，若
     * 没有选择则一定不能组装，以免影响SQL执行的结果
     **/
    @Test
    public void test09() {
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 19;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //StringUtils.isNotBlank()
        //判断某字符串是否不为空且长度不为0且不由空白符(whitespace) 构成
        if (StringUtils.isNotBlank(username)) {
            userQueryWrapper.like("name", username);
        }

        if (ageBegin != null) {
            userQueryWrapper.ge("age", ageBegin);
        }

        if (ageEnd != null) {
            userQueryWrapper.le("age", ageEnd);
        }

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.err::println);

    }

    /*
        上面的实现方案没有问题，但是代码比较复杂，我们可以使用带condition参数的重载方法构建查
        询条件，简化代码的编写
     */

    @Test
    public void test10() {
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 19;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like(StringUtils.isNotBlank(username), "name", "a")
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
//        Function<User, Integer> getAge = User::getAge;
//        String s = getAge.toString();
//        System.err.println(s);
    }


    //LambdaQueryWrapper
    @Test
    public void test11(){
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 19;
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, "a")
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    //LambdaUpdateWrapper
    @Test
    public void test12(){
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .set(User::getName, "a")
                .set(User::getEmail, "742608569@qq.com")
                .like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        int update = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println(update);
    }



}
