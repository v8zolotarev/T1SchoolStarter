package org.zolotarev.t1schoolstarter.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoggingLevel {
    OFF(0),
    DEBUG(1),
    INFO(2),
    WARNING(3),
    ERROR(4),
    CRITICAL(5);

    private final int level;
}

