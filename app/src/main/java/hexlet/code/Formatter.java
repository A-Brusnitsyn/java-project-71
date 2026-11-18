package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

  private static final Stylish STYLISH = new Stylish();
  private static final Plain PLAIN = new Plain();

  public static String format(Diff diff, String formatName) {
    if (formatName == null) {
      throw new IllegalArgumentException("Unsupported format: null");
    }
    return switch (formatName.toLowerCase()) {
      case "stylish" -> STYLISH.format(diff);
      case "plain" -> PLAIN.format(diff);
      default -> throw new IllegalArgumentException("Unsupported format: " + formatName);
    };
  }
}
