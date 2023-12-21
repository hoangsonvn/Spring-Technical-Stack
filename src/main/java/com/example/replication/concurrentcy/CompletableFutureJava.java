package com.example.replication.concurrentcy;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureJava {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String a = "a";
      CompletableFuture.runAsync(()->{
//           try {
//               Thread.sleep(5000);
               System.out.println(Thread.currentThread().getName()+"1");
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
           System.out.println("runned");
      //     return  "hello";
       });
        System.out.println(Thread.currentThread().getName());
//        System.out.println(completableFuture.get());
        System.out.println(Thread.currentThread().getName());

        System.out.println("final");

    }
}
