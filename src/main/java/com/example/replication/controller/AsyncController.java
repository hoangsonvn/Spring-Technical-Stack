package com.example.replication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
@Slf4j
@RequestMapping("/async")
@RestController
public class AsyncController {
    @GetMapping(path = "/stream")
//    @Async
    public CompletableFuture callCableTest() throws InterruptedException {

        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "done";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });
        System.out.println("start");
        Thread.sleep(10000);
        System.out.println("finished");

        return future;
        // Trả về 1 compleFuture như kiểu 1 callable vậy, bởi vì mặc định tomcat 200 thread , nên nếu quá tải 200 Thread sẽ hẹo
        // nên ở đây khi 1 request vào  sẽ có 1 thread đảm nhận request, nếu trả về CompleteFuture nó sẽ giải phóng cái luồng tommcat
        // đang xử lí, để nó xử lý request khác, đẩy sang 1 luồng khác của java application, luồng mới này sẽ response request
    }
    @RequestMapping("callable") // cach viet nay kha lau r
    public @ResponseBody
    Callable<String> handleTestRequest() {
        log.info("handler started");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call () throws Exception {
                log.info("async task started");
                Thread.sleep(2000);
                log.info("async task finished");
                return "async result";
            }
        };

        log.info("handler finished");
        return callable;
    }

}
