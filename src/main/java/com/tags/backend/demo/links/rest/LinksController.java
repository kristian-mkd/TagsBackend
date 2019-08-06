package com.tags.backend.demo.links.rest;

import com.tags.backend.demo.account.domain.Account;
import com.tags.backend.demo.account.service.AccountService;
import com.tags.backend.demo.exceptions.EntityAlreadyExistsException;
import com.tags.backend.demo.links.domain.Link;
import com.tags.backend.demo.links.dto.LinkDto;
import com.tags.backend.demo.links.service.LinkService;
import com.tags.backend.demo.tags.service.TagService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/links")
public class LinksController {

  private LinkService linkService;
  private AccountService accountService;
  private TagService tagService;

  public LinksController(LinkService linkService, AccountService accountService, TagService tagService) {
    this.linkService = linkService;
    this.accountService = accountService;
    this.tagService = tagService;
  }

  @PostMapping
  public ResponseEntity create(@RequestBody LinkDto linkDto) {
    Account firstAccount = accountService.getAccount(linkDto.getAccountId());
    Link link;
    try {
      link = linkService.save(firstAccount.getId(), linkDto.getUrl());
      tagService.createAll(link, linkDto.getUrl());
    } catch (EntityAlreadyExistsException e) {
      return ResponseEntity.badRequest().body("Link already exists");
    }
    return ResponseEntity.ok(linkService.save(link));
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<List<Link>> findById(@PathVariable Long accountId) {
    return ResponseEntity.ok(linkService.getAllLinksFor(accountId));
  }

}
