package top.hanbing777;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.mapper.UserMapper;
import top.hanbing777.pojo.User;

import java.util.List;

@SpringBootTest
public class PageTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 5);
        Page<User> userPage = userMapper.selectPage(page, null);
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }

    @Test
    public void test01(){
        Page<User> userPage = new Page<>(1, 5);
        userMapper.selectPageVo(userPage, 18);
        List<User> records = userPage.getRecords();
        System.out.println(records.size());
        records.forEach(System.out::println);
    }





}
