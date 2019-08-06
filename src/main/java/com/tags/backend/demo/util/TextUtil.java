package com.tags.backend.demo.util;

import static com.tags.backend.demo.ApplicationConstants.MAX_SUGGESTED_KEYWORDS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

  private static final String STOP_WORDS_FILE = "english_stopwords.txt";

  private TextUtil() {
  }

  public static Map<String, Integer> getWordsFrom(String text) {
    Pattern pattern = Pattern.compile("[\\w']+");
    Matcher matcher = pattern.matcher(text);
    Set<String> stopWords = getStopWords();
    Map<String, Integer> wordsMap = new HashMap<>();

    while (matcher.find()) {
      String word = text.substring(matcher.start(), matcher.end()).toLowerCase();
      if (stopWords.contains(word)) {
        continue;
      }
      int count = 1;
      if (wordsMap.containsKey(word)) {
        count = wordsMap.get(word) + 1;
      }
      wordsMap.put(word, count);
    }
    return wordsMap;
  }

  public static SortedSet<Entry<String, Integer>> getSortedWordsFrom(String text) {
    Map<String, Integer> wordsMap = getWordsFrom(text);
    return entriesSortedByValues(wordsMap);
  }

  public static List<Entry<String, Integer>> getFirstNSortedWords(String text, int N) {
    ArrayList<Entry<String, Integer>> list = new ArrayList<>(getSortedWordsFrom(text));
    if (list.size() < N) {
      return list;
    }
    return list.subList(0, N);
  }

  public static List<String> getFirstSortedWordsFrom(String text) {
    List<Entry<String, Integer>> firstNSortedWords = getFirstNSortedWords(text, MAX_SUGGESTED_KEYWORDS);
    List<String> result = new ArrayList<>();
    for (Entry<String, Integer> entry : firstNSortedWords) {
      result.add(entry.getKey());
    }
    return result;
  }

  static <K, V extends Comparable<? super V>> SortedSet<Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
    SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>(
        new Comparator<Entry<K, V>>() {
          @Override
          public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
            int res = e2.getValue().compareTo(e1.getValue());
            return res != 0 ? res : 1;
          }
        }
    );
    sortedEntries.addAll(map.entrySet());
    return sortedEntries;
  }

  public static Set<String> getStopWords() {
    return new HashSet<>(FileUtil.readFile(STOP_WORDS_FILE));
  }
}