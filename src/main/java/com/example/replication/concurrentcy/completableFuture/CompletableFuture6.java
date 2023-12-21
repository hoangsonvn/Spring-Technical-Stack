package com.example.replication.concurrentcy.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuture6 {
    public  static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
             executor.execute(()-> System.out.println("123123123"));
        // thenAccept() example
        Thread.currentThread().getId();
        CompletableFuture.supplyAsync(() -> {
            return MailUtil.getMailInfo();
        },executor).thenAccept(content -> {
            System.out.println("Mail content: " + content);
        });

        // thenRun() example
        CompletableFuture.supplyAsync(() -> {
            return MailUtil.sendMail();
        }).thenRun(() -> {
            MailUtil.logging();
        });
    }
}