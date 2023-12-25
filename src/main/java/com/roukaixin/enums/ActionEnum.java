package com.roukaixin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ActionEnum {

    CREATE("create"),
    DELETE("delete"),
    MOVE("move"),
    UPDATE("update");

    private String action;


}
