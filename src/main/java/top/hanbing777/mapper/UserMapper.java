package top.hanbing777.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hanbing777.pojo.User;

import java.util.Map;


@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(long id);

    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);

    Map<String, Object> selectNameById(@Param("id") Long id);

    int insertSelective(User user);

    int updateSelective(User user);

}
