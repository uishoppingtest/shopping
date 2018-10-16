package com.shop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
public class LoggableAspect {

    @Pointcut("execution(* com.shop.pages.*.*(..))")
    public void anyMethodInPages() {}

    @Pointcut("execution(* com.shop.asserts.*.*(..))")
    public void anyMethodInAssertions() {}

    @Before("anyMethodInPages() || anyMethodInAssertions()")
    public void step(JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final String name = joinPoint.getArgs().length > 0 
                ? String.format("%s (%s)", signature.getName(), arrayToSting(joinPoint.getArgs()))
                : signature.getName() + "()";

        log.info(name);
    }

    private static String arrayToSting(final Object... array) {
        return Stream.of(array).map(object -> {
            if (object.getClass().isArray()) {
                return arrayToSting((Object[]) object);
            }
            return Objects.toString(object);
        }).collect(Collectors.joining(", "));
    }
}
