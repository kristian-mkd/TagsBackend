package com.tags.backend.demo.tags.service;

import com.tags.backend.demo.links.domain.Link;
import com.tags.backend.demo.links.service.LinkService;
import com.tags.backend.demo.tags.domain.Tag;
import com.tags.backend.demo.tags.repository.TagRepository;
import com.tags.backend.demo.util.TextUtil;
import com.tags.backend.demo.util.WebCrawlerUtil;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TagService {

  private TagRepository tagRepository;
  private LinkService linkService;

  public TagService(TagRepository tagRepository, LinkService linkService) {
    this.tagRepository = tagRepository;
    this.linkService = linkService;
  }

  public Tag create(Tag tag) {
    return tagRepository.save(tag);
  }

  public void createAll(Link link, String url) {
    String pageTextContent = WebCrawlerUtil.getPageTextContent(url);
    List<String> tags = TextUtil.getFirstSortedWordsFrom(pageTextContent);
    for (String tag : tags) {
      create(new Tag(link, tag));
    }
  }

  public List<Tag> getAllTagsFor(Long linkId) {
    Link link = linkService.get(linkId);
    return tagRepository.findByLink(link);
  }

}
