package edu.fy.utils;

import lombok.Data;
import lombok.Setter;

/**
 * 统一结果返回信息
 * @author YangAo
 */
@Data
public class Result<T> {

    private Integer status;
    private String desc;
    @Setter
    private T data;

    /**
     * 成功操作, 不返回数据(data)
     * @return 成功结果信息
     */
    public static Result<String> success(){
        Result<String> result=new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功操作, 返回数据(data)
     * @param data 数据
     * @return 成功结果信息
     * @param <D> 数据的类型
     */
    public static <D> Result<D> success(D data){
        Result<D> result=new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 针对未存在的状态码, 允许自定义操作, 进行失败返回
     * @param status 状态码数字
     * @param desc 状态码描述
     * @return 失败结果信息
     */
    public static Result<String> fail(Integer status,String desc){
        Result<String> result=new Result<>();
        // 设置信息
        result.setStatus(status);
        result.setDesc(desc);
        return result;
    }

    /**
     * 针对状态码, 进行失败返回
     * @param resultCode 结果状态码
     * @return 失败结果信息
     */
    public static Result<String> fail(ResultCode resultCode){
        Result<String> result=new Result<>();
        // 设置状态码
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 失败操作, 根据数据码与数据进行返回
     * @param resultCode 数据码
     * @param data 数据
     * @return 失败结果信息
     * @param <D> 数据类型
     */
    public static <D> Result<D> fail(ResultCode resultCode, D data){
        Result<D> result=new Result<>();
        // 设置状态码
        result.setResultCode(resultCode);
        // 设置数据
        result.setData(data);
        return result;
    }

    /**
     * 捕获异常自定义异常时, 进行失败返回, 数据为异常的message
     * @param customException 捕获的自定义异常
     * @return 失败结果信息
     */
    public static Result<String> fail(CustomException customException){
        Result<String> result = Result.fail(customException.getResultCode());
        // 从自定义异常中获取 结果码和信息, 并设置
        result.setResultCode(customException.getResultCode());
        result.setData(customException.getMessage());
        return result;
    }

    /**
     * 设置状态码
     * @param resultCode 状态码
     */
    private void setResultCode(ResultCode resultCode){
        this.status=resultCode.status();
        this.desc=resultCode.message();
    }
}
