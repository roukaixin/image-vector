package com.roukaixin.domain;

import com.roukaixin.enums.OssTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * oss 配置
 *
 * @author 不北咪
 * @date 2023/12/25 下午9:42
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "oss")
public class Oss {

    /**
     * 使用那个 oss
     */
    private OssTypeEnum type;
}
