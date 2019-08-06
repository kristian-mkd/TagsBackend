package com.tags.backend.demo.util;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawlerUtil {

  private WebCrawlerUtil() {

  }

  public static String getPageTextContent(String url) {
    Document doc = null;
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
    if (doc == null) {
      return null;
    }
    Elements paragraphs = doc.select("p");
    for (Element paragraph : paragraphs) {
      sb.append(paragraph.text());
    }
    return sb.toString();
  }
}
