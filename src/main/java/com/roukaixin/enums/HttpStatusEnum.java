package com.roukaixin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum  HttpStatusEnum {

    success(200,"执行成功"),
    error(500,"服务器发生错误");

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 信息
     */
    private String message;
}
