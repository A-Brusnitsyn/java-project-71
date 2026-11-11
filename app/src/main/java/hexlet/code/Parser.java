package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.Map;

public class Parser {

  private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
  private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

  public static Map<String, Object> parse(String content, String dataFormat) throws IOException {
    ObjectMapper mapper = getMapper(dataFormat);
    return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
  }

  private static ObjectMapper getMapper(String dataFormat) {
    return switch (dataFormat.toLowerCase()) {
      case "json" -> JSON_MAPPER;
      case "yaml", "yml" -> YAML_MAPPER;
      default -> throw new IllegalArgumentException("Unsupported format: " + dataFormat);
    };
  }
}
