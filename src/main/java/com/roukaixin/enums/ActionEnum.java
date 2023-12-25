package com.roukaixin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 *
 * @author 不北咪
 * @date 2023/12/25 下午9:36
 */
@AllArgsConstructor
@Getter
public enum ActionEnum {

    /**
     * 创建
     */
    CREATE("create"),

    /**
     * 删除
     */
    DELETE("delete"),

    /**
     * 移除
     */
    MOVE("move"),

    /**
     * 更新
     */
    UPDATE("update");

    /**
     * 动作
     */
    private final String action;


}
