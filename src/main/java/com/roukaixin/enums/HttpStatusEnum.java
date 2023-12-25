package com.roukaixin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态吗
 *
 * @author 不北咪
 * @date 2023/12/25 下午9:38
 */
@Getter
@AllArgsConstructor
public enum  HttpStatusEnum {

    /**
     * 成功
     */
    success(200,"执行成功"),

    /**
     * 失败
     */
    error(500,"服务器发生错误");

    /**
     * 状态码
     */
    private final Integer status;

    /**
     * 信息
     */
    private final String message;
}
