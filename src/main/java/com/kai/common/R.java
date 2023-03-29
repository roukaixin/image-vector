package com.kai.common;

import com.kai.enums.HttpStatusEnum;
import lombok.*;
import lombok.experimental.Accessors;

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
