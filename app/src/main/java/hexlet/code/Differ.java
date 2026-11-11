package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static String generate(String filepath1, String filepath2) throws IOException {
    Path path1 = Path.of(filepath1);
    Path path2 = Path.of(filepath2);

    String content1 = Files.readString(path1);
    String content2 = Files.readString(path2);

    String format1 = getFormat(path1);
    String format2 = getFormat(path2);

    Map<String, Object> data1 = Parser.parse(content1, format1);
    Map<String, Object> data2 = Parser.parse(content2, format2);

    Set<String> keys = new TreeSet<>();
    keys.addAll(data1.keySet());
    keys.addAll(data2.keySet());

    StringBuilder result = new StringBuilder();
    result.append("{\n");

    for (String key : keys) {
      boolean existsInFirst = data1.containsKey(key);
      boolean existsInSecond = data2.containsKey(key);

      Object value1 = data1.get(key);
      Object value2 = data2.get(key);

      if (existsInFirst && existsInSecond) {
        if (java.util.Objects.equals(value1, value2)) {
          result.append("    ").append(key).append(": ").append(value1).append("\n");
        } else {
          appendLine(result, "-", key, value1);
          appendLine(result, "+", key, value2);
        }
      } else if (existsInFirst) {
        appendLine(result, "-", key, value1);
      } else {
        appendLine(result, "+", key, value2);
      }
    }
    result.append("}");
    return result.toString();
  }

  private static String getFormat(Path path) {
    String filename = path.getFileName().toString();
    int lastDotIndex = filename.lastIndexOf('.');
    if (lastDotIndex == -1) {
      return "";
    }
    return filename.substring(lastDotIndex + 1);
  }

  private static void appendLine(StringBuilder result, String sign, String key, Object value) {
    result
        .append("  ")
        .append(sign)
        .append(" ")
        .append(key)
        .append(": ")
        .append(value)
        .append("\n");
  }
}
