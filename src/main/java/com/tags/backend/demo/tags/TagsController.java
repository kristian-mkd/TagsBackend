package com.tags.backend.demo.tags;


import com.tags.backend.demo.tags.domain.Tag;
import com.tags.backend.demo.tags.service.TagService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tags")
public class TagsController {

  private TagService tagService;

  public TagsController(TagService tagService) {
    this.tagService = tagService;
  }

  @GetMapping("/{linkId}")
  public ResponseEntity<List<String>> findById(@PathVariable Long linkId) {
    List<Tag> allTagsFor = tagService.getAllTagsFor((linkId));
    List<String> list = new ArrayList<>();
    for (Tag tag : allTagsFor) {
      list.add(tag.getValue());
    }
    return ResponseEntity.ok(list);
  }

}
