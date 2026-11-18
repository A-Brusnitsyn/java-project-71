package hexlet.code.formatters;

import hexlet.code.Diff;
import java.util.ArrayList;
import java.util.List;

public class Plain {
  public String format(Diff diff) {
    List<String> lines = new ArrayList<>();

    for (String key : diff.getKeys()) {
      Object oldValue = diff.getOldValue(key);
      Object newValue = diff.getNewValue(key);

      if (!diff.inFirst(key)) {
        lines.add("Property '" + key + "' was added with value: " + value(newValue));
      } else if (!diff.inSecond(key)) {
        lines.add("Property '" + key + "' was removed");
      } else if (changed(oldValue, newValue)) {
        lines.add(
            "Property '"
                + key
                + "' was updated. From "
                + value(oldValue)
                + " to "
                + value(newValue));
      }
    }

    return String.join("\n", lines);
  }

  private boolean changed(Object oldValue, Object newValue) {
    if (oldValue == null) {
      return newValue != null;
    }
    return !oldValue.equals(newValue);
  }

  private String value(Object value) {
    if (value == null) {
      return "null";
    }
    if (value.getClass() == String.class) {
      return "'" + value + "'";
    }
    String text = String.valueOf(value);
    if (text.startsWith("{") || text.startsWith("[")) {
      return "[complex value]";
    }
    return text;
  }
}
