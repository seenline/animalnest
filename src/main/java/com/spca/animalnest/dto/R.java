package com.spca.animalnest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.bytebuddy.asm.Advice;
import org.springframework.expression.spel.ast.NullLiteral;

/**
 * @author seenline
 * @data 2021/8/23 21:51
 * @description
 */
@ApiModel(description = "通用返回类")
@Accessors(chain = true)
@Data
public class R <T>{
    public interface ResultCode{
        Integer SUCCESS = 20000;
        Integer ERROR = 20001;
    }


    @ApiModelProperty(value = "错误码",required = true)
    private Integer code;
    @ApiModelProperty(value = "提示信息",required = true)
    private String message;
    private T data;

    private R() {}   //私有构造方法
    private R(Integer code,String message)
    {
        this.code=code;
        this.message=message;
        this.data= null;
    }   //私有有参构造

    public static <T> R<T> ok()
    {
        R<T> r=new R<>(ResultCode.SUCCESS,"success");
        return r;
    }
    public static <T> R<T> ok(T data)
    {
        R<T> r=new R<>(ResultCode.SUCCESS,"Success");
        r.setData(data);
        return r;
    }   //成功信息的构造函数

    public static  <T> R<T> error()
    {
        R<T> r=new R<>(ResultCode.ERROR,"Error");
        return r;
    }
    public static <T> R<T> error(T data)
    {
        R<T> r=new R<>(ResultCode.ERROR,"Error");
        r.setData(data);
        return r;
    }   //失败信息的构造函数

}
