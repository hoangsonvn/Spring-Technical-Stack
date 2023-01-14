package com.example.replication.controller;

import com.example.replication.entity.Member;
import com.example.replication.service.MemberServiceImpl;
import com.example.replication.util.Common;
import com.example.replication.util.cache.Cache;
import com.example.replication.util.entity.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;
    private final Cache cache;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<?> findOne(@RequestParam long id) {
        Member member = (Member) cache.get(Common.MEMBER + id);
        if (member == null){
            member = memberService.findOne(id).orElseThrow();
            System.out.println("not cache");
        }
        return ResponseEntity.ok(member);
    }

    @PostMapping("")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Member request) {
        Member member = memberService.save(request);
        cache.put(Common.MEMBER + member.getId(), member, 60);
        return Response.data(member);
    }
}
