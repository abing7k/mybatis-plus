package top.hanbing777.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("t_product")
public class Product {

    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;

}
