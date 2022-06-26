package com.waterfogsw.springbootwebsocket.member.service;

import com.waterfogsw.springbootwebsocket.member.entity.Member;

public interface MemberService {
  void join(Member member);

  Member findById(Long id);
}
