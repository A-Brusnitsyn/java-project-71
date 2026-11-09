import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DifferTest {
  @Test
  void testGenerateFlatJson() throws Exception {
    String path1 = "src/test/resources/file1.json";
    String path2 = "src/test/resources/file2.json";
    String expected =
"""
{
  - follow: false
    host: hexlet.io
  - proxy: 123.234.53.22
  - timeout: 50
  + timeout: 20
  + verbose: true
}""";
    String actual = hexlet.code.Differ.generate(path1, path2);
    assertEquals(expected, actual);
  }
}
