package com.example.replication.controller;

import com.example.replication.entity.Member;
import com.example.replication.service.serviceimpl.MemberServiceImpl;
import com.example.replication.util.Common;
import com.example.replication.util.cache.Cache;
import com.example.replication.util.entity.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;
    private final Cache cache;

 /*   @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.forEach((s)->CompletableFuture.runAsync(()-> System.out.println(s)));

        CompletableFuture.runAsync(()->{
//           try {
//               Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"1");
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
            System.out.println("runned");});
        System.out.println("123");
        return Response.data(memberService.findAll());
    }*/

    @GetMapping("/find")
    public ResponseEntity<?> findOne(@RequestParam long id) {
        Member member = (Member) cache.get(Common.MEMBER + id);
        if (member == null){
            member = memberService.findOne(id).orElseThrow();
            log.info("not cache");
        }
        return Response.data(member);
    }

    @PostMapping("")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Member request) {
        Member member = memberService.save(request);
        cache.put(Common.MEMBER + member.getId(), member, 60);
        return Response.data(member);
    }
}
