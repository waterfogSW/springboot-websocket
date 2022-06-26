package com.waterfogsw.springbootwebsocket.member.controller.dto;

import javax.validation.constraints.NotBlank;

import com.waterfogsw.springbootwebsocket.member.entity.Member;

public record MemberJoinRequest(
    @NotBlank
    String name
) {
  public static Member toEntity(MemberJoinRequest request) {
    return new Member(request.name());
  }
}
