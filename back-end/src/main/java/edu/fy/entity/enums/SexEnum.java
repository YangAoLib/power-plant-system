package edu.fy.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别: 0-男, 1-女
 *
 * @author YangAo
 * @Description 性别枚举类
 * @create 2022-05-28 17:41
 */
@AllArgsConstructor
@Getter
public enum SexEnum implements IEnum<Integer> {
    /**
     * 男
     */
    MAN(0, "男"),
    /**
     * 女
     */
    WOMAN(1, "女");

    /**
     * 枚举值
     */
    private final Integer value;
    /**
     * 对应描述
     */
    private final String desc;
}
