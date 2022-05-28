package edu.fy.utils;

/**
 * @author YangAo
 * @Description 自定义异常, 带有状态码信息
 * @create 2022-05-28 11:10
 */
public class CustomException extends RuntimeException{


    public CustomException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    private ResultCode resultCode;
    private static final long serialVersionUID = 7630603437187408838L;
}
