package com.waterfogsw.springbootwebsocket.message.dto;

import com.waterfogsw.springbootwebsocket.message.entity.MessageType;

public record MessageCreateRequest(
    MessageType messageType,
    Long senderId,
    Long channelId,
    String message
) {
}
