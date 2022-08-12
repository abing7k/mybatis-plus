package top.hanbing777.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanbing777.mapper.ProductMapper;
import top.hanbing777.pojo.Product;
import top.hanbing777.service.ProductService;


@DS("product") //指定所操作的数据源
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
