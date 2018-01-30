package com.dx.ss.data.rebate.aop;

import com.dx.ss.data.rebate.annotation.Domain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 为Mapper的更新方法设置切面，设置Bean中的公共字段值。
 *
 */
@Slf4j
@Aspect
@Order(2)
@Component
public class UpdateAspect {

    @Before("execution(* com.dx.ss.data.rebate.dal.mapper..*..*update*(..))" +
            "||execution(* com.dx.ss.data.rebate.dal.mapper..*..*modify*(..))" +
            "||execution(* com.dx.ss.data.rebate.dal.mapper..*..*edit*(..))" +
            "||execution(* com.dx.ss.data.rebate.dal.mapper..*..*save*(..))" +
            "||execution(* com.dx.ss.data.rebate.dal.mapper..*..*delete*(..))")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            Arrays.stream(args).forEach(arg -> {
                if (arg != null) {
                    if (arg instanceof List) {
                        List<?> argList = (List<?>) arg;
                        if (!CollectionUtils.isEmpty(argList)) {
                            argList.forEach(this::domainHandle);
                        }
                    } else {
                        domainHandle(arg);
                    }
                }
            });
        }
    }

    private void domainHandle(Object object) {
        if (object.getClass().isAnnotationPresent(Domain.class)) {
            try {
                MethodUtils.invokeMethod(object, "setGmtModify", new Date());
            } catch (Exception e) {
                log.error("设置公共字段失败,{}", e);
            }
        }
    }
}

