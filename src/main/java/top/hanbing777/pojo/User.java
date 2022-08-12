package top.hanbing777.pojo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hanbing777.enums.SexEnums;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@TableName("t_user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name; //也可以直接写成userName
    private Integer age;
    private String email;

    @TableLogic
    private Integer isDeleted;
    private SexEnums sex;
}
