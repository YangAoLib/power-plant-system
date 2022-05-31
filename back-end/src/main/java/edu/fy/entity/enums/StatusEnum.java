package edu.fy.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态: 0-在职, 1-离职
 *
 * @author YangAo
 * @Description 人员状态枚举类
 * @create 2022-05-28 10:08
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum implements IEnum<Integer> {
    /**
     * 在职
     */
    WORK(0,"在职"),

    /**
     * 离职
     */
    RESIGN(1,"离职");


    /**
     * 枚举值
     */
    private final Integer value;
    /**
     * 对应描述
     */
    private final String desc;
}
