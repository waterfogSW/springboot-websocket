package com.waterfogsw.springbootwebsocket.message.service;

import org.springframework.web.socket.WebSocketSession;

import com.waterfogsw.springbootwebsocket.message.dto.MessageCreateRequest;
import com.waterfogsw.springbootwebsocket.message.entity.Message;

public interface MessageService {
  <T> void sendMessage(WebSocketSession session, T message);

  Message create(MessageCreateRequest messageRequest);
}
