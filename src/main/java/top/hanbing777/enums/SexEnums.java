package top.hanbing777.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SexEnums {

    MALE(1,"男"),
    FeMale(2,"女");


    @EnumValue
    private Integer sex;

    private String sexName;

}
