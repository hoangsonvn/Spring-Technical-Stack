package com.example.replication.service.serviceimpl;


import com.example.replication.entity.Member;
import com.example.replication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    public Member insert(Member member) {
        return memberRepository.save(member);
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findOne(long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

}