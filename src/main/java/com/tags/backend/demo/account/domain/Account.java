package com.tags.backend.demo.account.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String displayName;

  private String password;

  public Account() {
  }

  public Account(String email, String displayName, String password) {
    this.email = email;
    this.displayName = displayName;
    this.password = password;
  }

}
