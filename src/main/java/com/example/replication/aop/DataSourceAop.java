package com.example.replication.aop;

import com.example.replication.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("execution(* com.example.replication.repository..*.find*(..)) " +
            //     " || execution(* com.example.replication.service..*.find*(..))" +
            "|| execution(* com.example.replication.repository..*.exist*(..))")
    public void readPointcut() {

    }

    @Pointcut("execution(* com.example.replication.service..*.delete*(..))" +
            //    "||execution(* com.example.replication.service..*.save*(..)) " +
            "||execution(* com.example.replication.repository..*.save*(..))" +
            "||  execution(* com.example.replication.repository..*.delete*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}
