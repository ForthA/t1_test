package ru.tarasov.testing.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.tarasov.testing.exception.AspectMethodProceedingException;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(ru.tarasov.testing.aspect.annotation.TimeExecutionLoggable)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) {
        long start = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        Object proceeded = null;
        try {
            proceeded = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new AspectMethodProceedingException("Exception while proceeding method: "
                    + signature.getMethod().getName() + "in " + signature.getDeclaringType());
        }

        long end = System.currentTimeMillis();
        log.info("Execution time for {} in {}: {}", signature.getMethod().getName(),
                signature.getDeclaringType(), end - start);

        return proceeded;
    }

    @Before("@annotation(ru.tarasov.testing.aspect.annotation.EntryLoggable)")
    public void logEntry(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();

        log.info("Entering method {} in {} with params {}",
                signature.getMethod().getName(), signature.getDeclaringType(),
                args);
    }

    @AfterThrowing(
            pointcut = "@annotation(ru.tarasov.testing.aspect.annotation.ThrowingLoggable)",
            throwing = "exception"
    )
    public void logException(JoinPoint joinPoint, Exception exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.error("method {} in {} thrown exception {}",
                signature.getMethod().getName(), signature.getDeclaringType(), exception.getClass());
    }

    @AfterReturning(
            pointcut = "@annotation(ru.tarasov.testing.aspect.annotation.ReturnLoggable)",
            returning = "result")
    public void logReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info("method {} in {} returned {}",
                signature.getMethod().getName(), signature.getDeclaringType(), result);
    }

}
