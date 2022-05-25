package edu.fy.utils;

public enum ResultCode {
    SUCCESS(200,"成功"),
    SYSTEM_ERROR(500,"系统错误"),
    PARAM_IS_INVALID(1000,"参数错误"),
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
