package edu.fy.utils;

/**
 * 结果信息枚举类
 * @author YangAo
 */

public enum ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(200,"成功"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(500,"系统错误"),
    /**
     * 参数传递错误
     */
    PARAM_IS_INVALID(1000,"参数错误"),
    /**
     * 用户已经存在
     */
    USER_IS_EXISTED(1001,"用户已存在");
    private final Integer status;
    private final String message;
    ResultCode(Integer status,String message){
        this.status=status;
        this.message=message;
    }
    public Integer status(){
        return this.status;
    }
    public String message(){
        return this.message;
    }
}
