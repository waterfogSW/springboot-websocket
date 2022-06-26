package com.waterfogsw.springbootwebsocket.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waterfogsw.springbootwebsocket.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
