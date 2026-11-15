package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
  private static final Stylish STYLISH = new Stylish();

  public static String generate(String filepath1, String filepath2) throws IOException {
    return STYLISH.format(diff(filepath1, filepath2));
  }

  public static Diff diff(String filepath1, String filepath2) throws IOException {
    Path path1 = Path.of(filepath1);
    Path path2 = Path.of(filepath2);

    Map<String, Object> data1 = Parser.parse(Files.readString(path1), getFormat(path1));
    Map<String, Object> data2 = Parser.parse(Files.readString(path2), getFormat(path2));

    return Diff.of(data1, data2);
  }

  private static String getFormat(Path path) {
    String filename = path.getFileName().toString();
    int lastDotIndex = filename.lastIndexOf('.');
    if (lastDotIndex == -1) {
      return "";
    }
    return filename.substring(lastDotIndex + 1);
  }
}
