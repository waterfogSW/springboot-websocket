package com.waterfogsw.springbootwebsocket.channel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.waterfogsw.springbootwebsocket.channel.entity.Channel;
import com.waterfogsw.springbootwebsocket.channel.repository.ChannelRepository;
import com.waterfogsw.springbootwebsocket.common.exception.NotFoundException;
import com.waterfogsw.springbootwebsocket.member.service.MemberService;

@Service
public class DefaultChannelService implements ChannelService {

  private final ChannelRepository channelRepository;
  private final MemberService memberService;

  public DefaultChannelService(ChannelRepository channelRepository, MemberService memberService) {
    this.channelRepository = channelRepository;
    this.memberService = memberService;
  }

  @Override
  public void create(Channel channel, Long creatorId) {
    final var creator = memberService.findById(creatorId);
    channel.updateCreator(creator);
    channelRepository.save(channel);
  }

  @Override
  public List<Channel> findAll() {
    return channelRepository.findAll();
  }

  @Override
  public Channel findById(Long id) {
    return channelRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Channel not found"));
  }
}
