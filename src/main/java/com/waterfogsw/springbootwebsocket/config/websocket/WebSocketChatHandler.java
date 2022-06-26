package com.waterfogsw.springbootwebsocket.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterfogsw.springbootwebsocket.message.dto.MessageCreateRequest;
import com.waterfogsw.springbootwebsocket.message.service.MessageService;

@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

  private static final Logger log = LoggerFactory.getLogger(WebSocketChatHandler.class);

  private final ObjectMapper objectMapper;
  private final MessageService messageService;

  public WebSocketChatHandler(ObjectMapper objectMapper, MessageService messageService) {
    this.objectMapper = objectMapper;
    this.messageService = messageService;
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    final var payload = message.getPayload();
    log.info("payload {}", payload);
    final var request = objectMapper.readValue(payload, MessageCreateRequest.class);
    final var newMessage = messageService.create(request);
    final var channel = newMessage.getChannel();
    channel.handleActions(session, newMessage, messageService);
  }
}
