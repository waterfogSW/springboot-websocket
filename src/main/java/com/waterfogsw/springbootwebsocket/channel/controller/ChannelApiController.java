package com.waterfogsw.springbootwebsocket.channel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.waterfogsw.springbootwebsocket.channel.controller.dto.ChannelCreateRequest;
import com.waterfogsw.springbootwebsocket.channel.service.ChannelService;

@RestController
@RequestMapping("api/v1/channel")
public class ChannelApiController {
  private final ChannelService channelService;

  public ChannelApiController(ChannelService channelService) {
    this.channelService = channelService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody ChannelCreateRequest request) {
    final var newChannel = ChannelCreateRequest.toEntity(request);
    channelService.create(newChannel, request.creatorId());
  }
}
