package org.zolotarev.t1schoolstarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.zolotarev.t1schoolstarter.aspect.LoggingLevel;

@Getter
@Setter
@ConfigurationProperties(prefix = "aspect.logger")
public class LoggerProperties {

    private boolean enabled;
    private LoggingLevel level;

    LoggerProperties(@Value("${aspect.logger.enabled}") boolean enabled, @Value("${aspect.logger.level}") LoggingLevel level) {
        this.enabled = enabled;
        this.level = level;
    }
}
