package com.roukaixin.domain;

import com.roukaixin.condition.GithubTypeConfigCondition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Github Oss配置
 *
 * @author 不北咪
 * @date 2023/12/27 下午11:16
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "oss.config.github")
@Conditional(GithubTypeConfigCondition.class)
public class GitHubTypeConfig extends TypeConfig{

}
