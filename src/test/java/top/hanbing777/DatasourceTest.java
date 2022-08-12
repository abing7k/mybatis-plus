package top.hanbing777;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanbing777.service.ProductService;
import top.hanbing777.service.UserService;

@SpringBootTest
public class DatasourceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Test
    public void testDynamicDataSource() {
        System.err.println(userService.getById(1L));
        System.err.println(productService.getById(1L));
    }
}
