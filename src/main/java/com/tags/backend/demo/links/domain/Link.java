package com.tags.backend.demo.links.domain;

import com.tags.backend.demo.account.domain.Account;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Entity
@AllArgsConstructor
@Table(name = "link")
public class Link {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  private String baseUrl;

  private String query;

  public Link() {
  }

  public Link(Account account, String baseUrl, String query) {
    this.account = account;
    this.baseUrl = baseUrl;
    this.query = query;
  }
}
