package edu.fy.entity.enums;

/**
 * @author YangAo
 * @Description 枚举基础信息接口
 * @create 2022-06-13 21:55
 */
public interface BaseEnum {

    /**
     * 返回描述
     * @return 描述
     */
    String getDesc();

    /**
     * 返回具体值
     * @return 具体值
     */
    Integer getValue();
}
