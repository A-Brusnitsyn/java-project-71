import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

public class DifferTest {

  private static final String STYLISH_EXPECTED =
      """
          {
              chars1: [a, b, c]
            - chars2: [d, e, f]
            + chars2: false
            - checked: false
            + checked: true
            - default: null
            + default: [value1, value2]
            - id: 45
            + id: null
            - key1: value1
            + key2: value2
              numbers1: [1, 2, 3, 4]
            - numbers2: [2, 3, 4, 5]
            + numbers2: [22, 33, 44, 55]
            - numbers3: [3, 4, 5]
            + numbers4: [4, 5, 6]
            + obj1: {nestedKey=value, isNested=true}
            - setting1: Some value
            + setting1: Another value
            - setting2: 200
            + setting2: 300
            - setting3: true
            + setting3: none
          }""";

  private static final String PLAIN_EXPECTED =
      """
          Property 'chars2' was updated. From [complex value] to false
          Property 'checked' was updated. From false to true
          Property 'default' was updated. From null to [complex value]
          Property 'id' was updated. From 45 to null
          Property 'key1' was removed
          Property 'key2' was added with value: 'value2'
          Property 'numbers2' was updated. From [complex value] to [complex value]
          Property 'numbers3' was removed
          Property 'numbers4' was added with value: [complex value]
          Property 'obj1' was added with value: [complex value]
          Property 'setting1' was updated. From 'Some value' to 'Another value'
          Property 'setting2' was updated. From 200 to 300
          Property 'setting3' was updated. From true to 'none'""";

  @Test
  void testStylishJson() throws Exception {
    String actual =
        Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
    assertEquals(STYLISH_EXPECTED, actual);
  }

  @Test
  void testStylishYaml() throws Exception {
    String actual = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");
    assertEquals(STYLISH_EXPECTED, actual);
  }

  @Test
  void testPlainJson() throws Exception {
    String actual =
        Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "plain");
    assertEquals(PLAIN_EXPECTED, actual);
  }

  @Test
  void testPlainYaml() throws Exception {
    String actual =
        Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml", "plain");
    assertEquals(PLAIN_EXPECTED, actual);
  }

  @Test
  void testStylishIsDefault() throws Exception {
    String path1 = "src/test/resources/file1.json";
    String path2 = "src/test/resources/file2.json";
    assertEquals(Differ.generate(path1, path2, "stylish"), Differ.generate(path1, path2));
  }

  @Test
  void testUnknownFormat() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Differ.generate(
                "src/test/resources/file1.json", "src/test/resources/file2.json", "xml"));
  }
}
