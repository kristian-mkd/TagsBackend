package com.tags.backend.demo.links.service;

import com.tags.backend.demo.account.domain.Account;
import com.tags.backend.demo.account.service.AccountService;
import com.tags.backend.demo.exceptions.EntityAlreadyExistsException;
import com.tags.backend.demo.links.domain.Link;
import com.tags.backend.demo.links.repository.LinkRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LinkService {

  private final LinkRepository linkRepository;
  private final AccountService accountService;

  public LinkService(LinkRepository linkRepository, AccountService accountService) {
    this.linkRepository = linkRepository;
    this.accountService = accountService;
  }

  public Link get(Long id) {
    return linkRepository.getOne(id);
  }

  public Link save(Long accountId, String url) throws EntityAlreadyExistsException {
    URI uri = null;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      log.error(String.format("Cannot parse url: [%s]", url));
    }
    if (uri == null) {
      // FIXME: this can be designed better
      return null;
    }
    Account account = accountService.getAccount(accountId);
    Link existingLink = linkRepository.findLink(account, uri.getPath(), uri.getQuery());

    // FIXME: sort the query params and check if they are duplicates
    if (existingLink != null) {
      throw new EntityAlreadyExistsException();
    }
    return linkRepository.save(new Link(account, uri.getPath(), uri.getQuery()));
  }

  public Link save(Link link) {
    return linkRepository.save(link);
  }

  public List<Link> getAllLinksFor(Long accountId) {
    Account account = accountService.getAccount(accountId);
    return linkRepository.findByAccount(account);
  }
}
