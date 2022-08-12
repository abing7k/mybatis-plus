package top.hanbing777;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.mapper.ProductMapper;
import top.hanbing777.pojo.Product;

import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void test() {
        List<Product> products = productMapper.selectList(null);
        products.forEach(System.out::println);
    }

    @Test
    public void testConcurrentUpdate() {
        Product p1 = productMapper.selectById(1);
        System.err.println("小李取出的价格" + p1.getPrice());

        Product p2 = productMapper.selectById(1);
        System.err.println("小王取出的价格" + p2.getPrice());

        p1.setPrice(p1.getPrice() + 50);
        int r1 = productMapper.updateById(p1);
        System.err.println("小李" + r1);

        p2.setPrice(p2.getPrice() - 30);
        int r2 = productMapper.updateById(p2);
        System.err.println(r2);
        if (r2 == 0) {
            p2 = productMapper.selectById(1);
            p2.setPrice(p2.getPrice() - 30);
            int r3 = productMapper.updateById(p2);
            System.err.println("小李"+r3);
        }

        //老板查看价格
        Product product = productMapper.selectById(1);
        System.err.println("老板看到的价格" + product.getPrice());
    }



}
