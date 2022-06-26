package com.waterfogsw.springbootwebsocket.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.waterfogsw.springbootwebsocket.member.controller.dto.MemberJoinRequest;
import com.waterfogsw.springbootwebsocket.member.service.MemberService;

@RestController
@RequestMapping("api/v1/members")
public class MemberApiController {

  private final MemberService memberService;

  public MemberApiController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void join(@RequestBody MemberJoinRequest request) {
    final var member = MemberJoinRequest.toEntity(request);
    memberService.join(member);
  }

}
