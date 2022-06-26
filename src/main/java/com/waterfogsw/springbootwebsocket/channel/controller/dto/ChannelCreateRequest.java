package com.waterfogsw.springbootwebsocket.channel.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.waterfogsw.springbootwebsocket.channel.entity.Channel;

public record ChannelCreateRequest(
    @NotBlank String name,
    @NotNull Long creatorId
) {
  public static Channel toEntity(ChannelCreateRequest request) {
    return new Channel(request.name());
  }
}
