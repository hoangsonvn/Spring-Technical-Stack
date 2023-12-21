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
            "execution(* com.example.replication.repository..*.get*(..))" +
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



}
