package com.roukaixin.condition;

import com.roukaixin.enums.OssTypeEnum;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;


/**
 * 判断是否注入
 *
 * @author 不北咪
 * @date 2024/1/1 下午10:45
 */
public class GithubTypeConfigCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String ossType = context.getEnvironment().getProperty("oss.type");
        return !StringUtils.hasText(ossType) && OssTypeEnum.GITHUB.name().equals(ossType);
    }
}
