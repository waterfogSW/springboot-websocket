package com.waterfogsw.springbootwebsocket.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waterfogsw.springbootwebsocket.message.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
