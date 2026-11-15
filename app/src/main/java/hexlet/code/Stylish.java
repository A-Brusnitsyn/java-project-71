package hexlet.code;

import java.util.Objects;

public class Stylish {
  public String format(Diff diff) {
    StringBuilder result = new StringBuilder("{\n");

    for (String key : diff.getKeys()) {
      if (!diff.inFirst(key)) {
        // ключа не было в первом файле — добавился
        line(result, "+", key, diff.getNewValue(key));
      } else if (!diff.inSecond(key)) {
        // ключа не было во втором файле — удалился
        line(result, "-", key, diff.getOldValue(key));
      } else if (Objects.equals(diff.getOldValue(key), diff.getNewValue(key))) {
        // значение не изменилось
        result.append("    ").append(key).append(": ").append(diff.getOldValue(key)).append("\n");
      } else {
        // значение изменилось
        line(result, "-", key, diff.getOldValue(key));
        line(result, "+", key, diff.getNewValue(key));
      }
    }

    result.append("}");
    return result.toString();
  }

  private void line(StringBuilder result, String sign, String key, Object value) {
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
