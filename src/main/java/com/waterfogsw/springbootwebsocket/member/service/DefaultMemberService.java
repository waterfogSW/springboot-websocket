package com.waterfogsw.springbootwebsocket.member.service;

import org.springframework.stereotype.Service;

import com.waterfogsw.springbootwebsocket.common.exception.NotFoundException;
import com.waterfogsw.springbootwebsocket.member.entity.Member;
import com.waterfogsw.springbootwebsocket.member.repository.MemberRepository;

@Service
public class DefaultMemberService implements MemberService {

  private final MemberRepository memberRepository;

  public DefaultMemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findById(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Member not found"));
  }
}
