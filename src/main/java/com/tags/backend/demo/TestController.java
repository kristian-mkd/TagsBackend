package com.tags.backend.demo;

import static com.tags.backend.demo.ApplicationConstants.MAX_SUGGESTED_KEYWORDS;

import com.tags.backend.demo.util.TextUtil;
import com.tags.backend.demo.util.WebCrawlerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @RequestMapping("/crawler")
  public String webCrawlerTest(@RequestParam(value = "url") String url) {
    String pageTextContent = WebCrawlerUtil.getPageTextContent(url);
    return TextUtil.getFirstNSortedWords(pageTextContent, MAX_SUGGESTED_KEYWORDS).toString();
  }

}
