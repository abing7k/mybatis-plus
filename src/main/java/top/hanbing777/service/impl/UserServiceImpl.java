package top.hanbing777.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanbing777.mapper.UserMapper;
import top.hanbing777.pojo.User;
import top.hanbing777.service.UserService;

@DS("user") //指定所操作的数据源
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
