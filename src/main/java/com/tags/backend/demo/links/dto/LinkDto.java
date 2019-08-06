package com.tags.backend.demo.links.dto;

import lombok.Data;

@Data
public class LinkDto {

  private String url;

  // FIXME: this is workaround for user management not being implemented
  private Long accountId;

  public LinkDto() {
  }

}
