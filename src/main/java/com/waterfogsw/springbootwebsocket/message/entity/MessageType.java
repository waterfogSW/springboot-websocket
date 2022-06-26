package com.waterfogsw.springbootwebsocket.message.entity;

public enum MessageType {
  ENTER(" 님이 입장하셨습니다"),
  TALK(""),
  QUITE(" 님이 퇴장하셨습니다");

  private final String promptMessage;

  MessageType(String promptMessage) {
    this.promptMessage = promptMessage;
  }

  public String getPromptMessage() {
    return promptMessage;
  }
}
