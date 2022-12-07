package com.example.replication.service;


import com.example.replication.entity.Member;
import com.example.replication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    public int insert(Member member) {
        memberRepository.save(member);
        return 1;
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public String getToken(String appId) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return null;
    }
}