import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DifferTest {

  private final String expected =
      """
          {
            - follow: false
              host: hexlet.io
            - proxy: 123.234.53.22
            - timeout: 50
            + timeout: 20
            + verbose: true
          }""";

  @Test
  void testGenerateFlatJson() throws Exception {
    String path1 = "src/test/resources/file1.json";
    String path2 = "src/test/resources/file2.json";
    String actual = hexlet.code.Differ.generate(path1, path2);
    assertEquals(expected, actual);
  }

  @Test
  void testGenerateFlatYaml() throws Exception {
    String path1 = "src/test/resources/file1.yml";
    String path2 = "src/test/resources/file2.yml";
    String actual = hexlet.code.Differ.generate(path1, path2);
    assertEquals(expected, actual);
  }
}
