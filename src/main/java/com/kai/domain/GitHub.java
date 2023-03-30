package com.kai.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * github
 *
 * @author 不北咪
 * @date 2023/3/30 21:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConditionalOnProperty(value = "oss.github.enable", havingValue = "true")
@ConfigurationProperties(prefix = "oss.github")
public class GitHub {

    /**
     * 是否启用 github
     */
    private boolean enable = false;

    /**
     * 私人令牌
     */
    private String authorization;

    /**
     * 用户空间
     */
    private String owner;

    /**
     * 仓库名字
     */
    private String repo;

    /**
     * 分支（默认主分支）
     */
    private String branch = "main";

}
