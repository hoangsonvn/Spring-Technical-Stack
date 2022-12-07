package com.example.replication.controller;

import com.example.replication.entity.Member;
import com.example.replication.repository.MemberRepository;
import com.example.replication.service.MemberServiceImpl;
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

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.save(member));
    }
}
