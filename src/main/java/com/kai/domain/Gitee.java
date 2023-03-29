package com.kai.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * gitee
 *
 * @author 不北咪
 * @date 2023/3/28 21:21
 */

@Data
@Component
@ConditionalOnProperty(value = "oss.gitee.enabled",havingValue = "true")
@ConfigurationProperties(prefix = "oss.gitee")
public class Gitee {

    /**
     * 是否开始 gitee 图床
     */
    private boolean enabled = false;

    /**
     * 私人令牌
     */
    private String accessToken;

    /**
     * gitee 个人空间地址
     */
    private String owner;

    /**
     * 仓库名
     */
    private String repo;

}
