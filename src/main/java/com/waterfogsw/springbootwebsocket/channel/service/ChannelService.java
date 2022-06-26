package com.waterfogsw.springbootwebsocket.channel.service;

import java.util.List;

import com.waterfogsw.springbootwebsocket.channel.entity.Channel;

public interface ChannelService {
  void create(Channel channel, Long creatorId);

  List<Channel> findAll();
}
