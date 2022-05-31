package edu.fy.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 逻辑删除位: 0-未删除, 1-已删除
 *
 * @author YangAo
 * @Description 删除枚举类
 * @create 2022-05-28 16:07
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeletedEnum implements IEnum<Integer> {
    /**
     * 未删除
     */
    NOT_DELETED(0, "未删除"),
    /**
     * 已删除
     */
    DELETED(1, "已删除");

    /**
     * 枚举值
     */
    private final Integer value;
    /**
     * 对应描述
     */
    private final String desc;
}
