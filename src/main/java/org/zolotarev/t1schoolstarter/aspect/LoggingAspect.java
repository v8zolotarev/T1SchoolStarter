package org.zolotarev.t1schoolstarter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.zolotarev.t1schoolstarter.config.LoggerProperties;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final LoggerProperties loggerProperties;

    public LoggingAspect(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    private boolean isLoggable(LoggingLevel level) {
        return loggerProperties.isEnabled() &&
                level.ordinal() >= loggerProperties.getLevel().ordinal();
    }

    @Before("@annotation(org.zolotarev.t1schoolstarter.aspect.annotations.LogBefore)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method {} executed", joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(org.zolotarev.t1schoolstarter.aspect.annotations.LogAfterThrowing)", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("Method {} threw an exception: {}",
                joinPoint.getSignature().getName(), exception.getMessage());
    }

    @AfterReturning(value = "@annotation(org.zolotarev.t1schoolstarter.aspect.annotations.LogAfterReturning)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method {} executed successfully. Result: {}", joinPoint.getSignature().getName(), result);
    }

    @Around("@annotation(org.zolotarev.t1schoolstarter.aspect.annotations.LogAround)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Execution of method '{}' started. Parameters: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());

        Object result;
        try {
            result = joinPoint.proceed();
            log.info("Execution of method '{}' completed successfully. Result: {}",
                    joinPoint.getSignature().getName(),
                    result);
        } catch (Throwable ex) {
            log.error("Execution of method '{}' failed. Exception: {} - {}",
                    joinPoint.getSignature().getName(),
                    ex.getClass().getSimpleName(),
                    ex.getMessage());
            throw ex;
        }
        return result;
    }
}
