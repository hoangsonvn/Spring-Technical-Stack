package com.example.replication.repository;

import com.example.replication.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(long id);
    Member getAllByAge(long age);
}
