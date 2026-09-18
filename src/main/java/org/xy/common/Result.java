package org.xy.common;

import lombok.Data;

//项目文档中的统一格式
//{"code":200,"msg":"操作成功","data":{}}
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private  T data;

    public static<T> Result<T> success(T data){
        Result<T> result =new Result<>();
        result.code=200;
        result.msg="操作成功";
        result.data=data;
        return result;
    }

    public static<T> Result<T> error(Integer code,String msg){
        Result<T> result=new Result<>();
        result.code=code;
        result.msg=msg;
        return result;
    }

}

