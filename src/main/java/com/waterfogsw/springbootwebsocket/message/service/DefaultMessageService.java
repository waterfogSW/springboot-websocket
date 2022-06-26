package com.waterfogsw.springbootwebsocket.message.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterfogsw.springbootwebsocket.channel.service.ChannelService;
import com.waterfogsw.springbootwebsocket.member.service.MemberService;
import com.waterfogsw.springbootwebsocket.message.dto.MessageCreateRequest;
import com.waterfogsw.springbootwebsocket.message.entity.Message;
import com.waterfogsw.springbootwebsocket.message.repository.MessageRepository;

@Service
public class DefaultMessageService implements MessageService {

  private static final Logger log = LoggerFactory.getLogger(DefaultMessageService.class);

  private final ObjectMapper objectMapper;
  private final MemberService memberService;
  private final ChannelService channelService;
  private final MessageRepository messageRepository;

  public DefaultMessageService(
      ObjectMapper objectMapper,
      MemberService memberService,
      ChannelService channelService,
      MessageRepository messageRepository
  ) {

    this.objectMapper = objectMapper;
    this.memberService = memberService;
    this.channelService = channelService;
    this.messageRepository = messageRepository;
  }

  @Override
  public <T> void sendMessage(WebSocketSession session, T message) {
    try {
      session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    } catch (IOException e) {
      log.warn(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Message create(MessageCreateRequest request) {
    final var member = memberService.findById(request.senderId());
    final var channel = channelService.findById(request.channelId());

    final var message = Message.builder()
        .messageType(request.messageType())
        .message(request.message())
        .sender(member)
        .channel(channel)
        .build();

    return messageRepository.save(message);
  }
}
