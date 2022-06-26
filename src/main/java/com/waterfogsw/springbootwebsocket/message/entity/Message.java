package com.waterfogsw.springbootwebsocket.message.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.waterfogsw.springbootwebsocket.channel.entity.Channel;
import com.waterfogsw.springbootwebsocket.member.entity.Member;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private MessageType messageType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "channel_id")
  private Channel channel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sender_id")
  private Member sender;

  private String message;

  protected Message() {/*no-op*/}

  public Message(Builder builder) {
    this.messageType = builder.messageType;
    this.channel = builder.channel;
    this.sender = builder.sender;
    this.message = builder.message;
  }

  public static Builder builder() {
    return new Builder();
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public void promptMessage(String senderName, MessageType messageType) {
    this.message = senderName + messageType.getPromptMessage();
  }

  public Member getSender() {
    return sender;
  }

  public Channel getChannel() {
    return channel;
  }

  public static class Builder {
    private MessageType messageType;
    private Channel channel;
    private Member sender;
    private String message;

    public Builder messageType(MessageType messageType) {
      this.messageType = messageType;
      return this;
    }

    public Builder channel(Channel channel) {
      this.channel = channel;
      return this;
    }

    public Builder sender(Member sender) {
      this.sender = sender;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Message build() {
      return new Message(this);
    }
  }
}
