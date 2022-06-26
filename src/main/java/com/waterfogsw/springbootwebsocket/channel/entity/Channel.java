package com.waterfogsw.springbootwebsocket.channel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.socket.WebSocketSession;

import com.waterfogsw.springbootwebsocket.member.entity.Member;
import com.waterfogsw.springbootwebsocket.message.entity.Message;
import com.waterfogsw.springbootwebsocket.message.entity.MessageType;
import com.waterfogsw.springbootwebsocket.message.service.MessageService;

@Entity
public class Channel {
  @Transient
  private final Set<WebSocketSession> sessions = new HashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", updatable = false)
  private Member creator;

  protected Channel() {/*no-op*/}

  public Channel(String name) {
    this.name = name;
  }

  public void handleActions(WebSocketSession session, Message message,
      MessageService messageService) {
    if (!message.getMessageType().equals(MessageType.TALK)) {
      sessions.add(session);
      message.promptMessage(message.getSender().getName(), message.getMessageType());
    }
    sendMessage(message, messageService);
  }

  public Long getId() {
    return id;
  }

  public void updateCreator(Member creator) {
    this.creator = creator;
  }

  public <T> void sendMessage(T message, MessageService messageService) {
    sessions.parallelStream().forEach(session -> messageService.sendMessage(session, message));
  }
}
