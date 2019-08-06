package com.tags.backend.demo.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class FileUtil {

  private static final String FILE_MESSAGE = "File %s cannot be found.";

  private FileUtil() {
  }

  static List<String> readFile(String fileName) {
    Path path = null;
    try {
      URL resource = FileUtil.class.getClassLoader().getResource(fileName);
      if (resource == null) {
        log.error(String.format(FILE_MESSAGE, fileName));
        return Collections.emptyList();
      }
      path = Paths.get(resource.toURI());
    } catch (URISyntaxException e) {
      log.error(String.format(FILE_MESSAGE, fileName));
    }

    List<String> result = new ArrayList<>();
    try {
      result = Files.readAllLines(path);
    } catch (IOException e) {
      log.error(String.format(FILE_MESSAGE, fileName));
    }

    return result;
  }
}
