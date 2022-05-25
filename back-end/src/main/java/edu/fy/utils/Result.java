package edu.fy.utils;

import lombok.Data;
import lombok.Setter;

@Data
public class Result<T> {

    private Integer status;
    private String desc;
    @Setter
    private T data;

    public static Result<?> success(){
        Result<?> result=new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <D> Result<D> success(D data){
        Result<D> result=new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result<?> fail(Integer status,String desc){
        Result<?> result=new Result<>();
        result.setStatus(status);
        result.setDesc(desc);
        return result;
    }

    public static Result<?> fail(ResultCode resultCode){
        Result<?> result=new Result<>();
        result.setResultCode(resultCode);
        return result;
    }

    private void setResultCode(ResultCode resultCode){
        this.status=resultCode.status();
        this.desc=resultCode.message();
    }
}
