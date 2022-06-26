package com.waterfogsw.springbootwebsocket.channel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waterfogsw.springbootwebsocket.channel.entity.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
