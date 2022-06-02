package edu.fy.utils;

/**
 * 结果信息枚举类
 *
 * @author YangAo
 */
public enum ResultCode implements BaseErrorInfoInterface {
    /**
     * 操作成功
     */
    SUCCESS(200, "成功"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(500, "系统错误"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(501, "未知错误"),
    /**
     * 参数传递错误
     */
    PARAM_IS_INVALID(1000, "参数错误"),
    /**
     * 用户已经存在
     */
    USER_IS_EXISTED(1001, "用户已存在"),
    /**
     * 业务逻辑错误
     */
    LOGICAL_ERROR(2000, "业务逻辑错误");
    private final Integer status;
    private final String message;

    ResultCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
