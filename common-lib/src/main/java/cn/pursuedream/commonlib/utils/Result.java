package cn.pursuedream.commonlib.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result <T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> ok(){
        return new Result<>(CommonConstant.SUCCESS, null, null);
    }

    public static <T> Result<T> ok(String msg){
        return new Result(CommonConstant.SUCCESS, msg, null);
    }

    public static <T> Result<T> ok(String msg, T t){
        return new Result(CommonConstant.SUCCESS, msg, t);
    }

    public static <T> Result<T> failed(){
        return new Result<>(CommonConstant.ERROR, null, null);
    }

    public static <T> Result<T> failed(String msg){
        return new Result<>(CommonConstant.ERROR, msg, null);
    }

    public static <T> Result<T> failed(String msg, T t){
        return new Result<>(CommonConstant.ERROR, msg, t);
    }

    public static <T> Result<T> failed(Integer code, String msg, T t){
        return new Result<>(code, msg, t);
    }
}
