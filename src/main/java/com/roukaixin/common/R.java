package com.roukaixin.common;

import com.roukaixin.enums.HttpStatusEnum;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 统一返回结果类
 *
 * @author 不北咪
 * @date 2023/12/25 下午9:39
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class R<T> {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public static <T> R<T> ok(String message, T data){
        return new R<T>()
                .setStatus(HttpStatusEnum.success.getStatus())
                .setMessage(message)
                .setData(data);
    }

    public static <T> R<T> ok(T data){
        return new R<T>()
                .setStatus(HttpStatusEnum.success.getStatus())
                .setMessage(HttpStatusEnum.success.getMessage())
                .setData(data);
    }

    public static <T> R<T> ok(){
        return new R<T>()
                .setStatus(HttpStatusEnum.success.getStatus())
                .setMessage(HttpStatusEnum.success.getMessage())
                .setData(null);
    }
}
