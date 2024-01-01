package com.roukaixin.domain;

import com.roukaixin.condition.TypeConfigCondition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * oss 某个类型配置
 *
 * @author 不北咪
 * @date 2023/12/25 下午9:50
 */
@Setter
@Getter
@Configuration
@Conditional(TypeConfigCondition.class)
public class TypeConfig {

    /**
     * 访问令牌令牌
     */
    private String accessToken;

    /**
     * 用户空间（一般是用户名）
     */
    private String owner;

    /**
     * 仓库名字
     */
    private String repo;

    /**
     * 分支（默认主分支）
     */
    private String branch;

}
