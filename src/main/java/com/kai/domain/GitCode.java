package com.kai.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * csdn 的 Gitcode
 *
 * @author 不北咪
 * @date 2023/3/29 20:42
 */
@Data
@Component
@ConditionalOnProperty(value = "oss.gitcode.enable", havingValue = "true")
@ConfigurationProperties(prefix = "oss.gitcode")
public class GitCode {

    /**
     * 是否开始 gitcode 图床
     */
    private boolean enable = false;

    /**
     * 私人令牌
     */
    private String privateToken;

    /**
     * 项目id （仓库id）
     */
    private Integer id;

    /**
     * 仓库分支名
     */
    private String branch;
}
