package com.waterfogsw.springbootwebsocket.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  protected Member() {/*no-op*/}

  public Member(String name) {
    this.name = name;
  }
}
