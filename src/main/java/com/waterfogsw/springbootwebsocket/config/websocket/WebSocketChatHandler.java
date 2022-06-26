package com.waterfogsw.springbootwebsocket.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterfogsw.springbootwebsocket.channel.entity.Channel;
import com.waterfogsw.springbootwebsocket.message.entity.Message;
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
    String payload = message.getPayload();
    log.info("payload {}", payload);
    Message channelMessage = objectMapper.readValue(payload, Message.class);
    Channel channel = channelMessage.getChannel();
    channel.handleActions(session, channelMessage, messageService);
  }
}
