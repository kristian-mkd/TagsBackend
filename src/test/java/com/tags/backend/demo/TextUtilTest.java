package com.tags.backend.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.tags.backend.demo.util.TextUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;

public class TextUtilTest {

  @Test
  public void testWordOccurrencies() {
    Map<String, Integer> wordsFrom = TextUtil.getWordsFrom("The quick brown fox jumps over the lazy dog");
    assertThat(wordsFrom).isNotNull();
    assertThat(wordsFrom.get("brown")).isEqualTo(1);
    assertThat(wordsFrom.get("dog")).isEqualTo(1);
    assertThat(wordsFrom.get("The")).isEqualTo(null);
  }

  @Test
  public void testMultipleOccurrenciesOfAWord() {
    Map<String, Integer> wordsFrom = TextUtil.getWordsFrom("The quick dog brown dog jumps over the lazy dog");
    assertThat(wordsFrom).isNotNull();
    assertThat(wordsFrom.get("dog")).isEqualTo(3);
  }

  @Test
  public void testEmptyText() {
    Map<String, Integer> wordsFrom = TextUtil.getWordsFrom("");
    assertThat(wordsFrom).isNotNull();
    assertThat(wordsFrom.isEmpty()).isTrue();
  }

  @Test
  public void testStopWords() {
    Set<String> stopWords = TextUtil.getStopWords();
    assertThat(stopWords.contains("a")).isTrue();
    assertThat(stopWords.contains("the")).isTrue();
    assertThat(stopWords.contains("and")).isTrue();
    assertThat(stopWords.contains("car")).isFalse();
  }

  @Test
  public void testMostFrequentWord() {
    List<Entry<String, Integer>> firstNSortedWords =
        TextUtil.getFirstNSortedWords("The quick dog brown dog jumps over the lazy dog", 10);
    assertThat(firstNSortedWords.get(0).getKey()).isEqualTo("dog");
  }

  @Test
  public void testLargerUpperLimitDoesNotBrakeIt() {
    List<Entry<String, Integer>> firstNSortedWords = TextUtil.getFirstNSortedWords("The quick dog ", 10);
    assertThat(firstNSortedWords.get(0).getKey()).isEqualTo("quick");
  }
}
