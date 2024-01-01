package com.roukaixin.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * 校验是否有 oss.config 节点提示
 *
 * @author 不北咪
 * @date 2023/12/27 下午11:11
 */
public class TypeConfigCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String ossType = context.getEnvironment().getProperty("oss.type");
        return StringUtils.hasText(ossType);
    }
}
