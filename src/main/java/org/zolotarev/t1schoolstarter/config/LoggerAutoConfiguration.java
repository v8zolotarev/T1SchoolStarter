package org.zolotarev.t1schoolstarter.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.zolotarev.t1schoolstarter.aspect.LoggingAspect;

@AutoConfiguration
@EnableConfigurationProperties(LoggerProperties.class)
public class LoggerAutoConfiguration {

    private final LoggerProperties loggerProperties;

    public LoggerAutoConfiguration(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    @Bean
    @ConditionalOnProperty(name = "aspect.enabled", havingValue = "true", matchIfMissing = true)
    public LoggingAspect loggingAspect() {
        return new LoggingAspect(loggerProperties);
    }
}
