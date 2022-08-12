package top.hanbing777.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.hanbing777.pojo.Product;

@Repository
@Mapper
public interface ProductMapper extends BaseMapper<Product> {


}
