package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Diff {
  private final Map<String, Object> first;
  private final Map<String, Object> second;
  private final List<String> keys;

  public Diff(Map<String, Object> first, Map<String, Object> second, List<String> keys) {
    this.first = first;
    this.second = second;
    this.keys = keys;
  }

  public List<String> getKeys() {
    return keys;
  }

  public boolean inFirst(String key) {
    return first.containsKey(key);
  }

  public boolean inSecond(String key) {
    return second.containsKey(key);
  }

  public Object getOldValue(String key) {
    return first.get(key);
  }

  public Object getNewValue(String key) {
    return second.get(key);
  }

  public static Diff of(Map<String, Object> data1, Map<String, Object> data2) {
    TreeSet<String> keys = new TreeSet<>(data1.keySet());
    keys.addAll(data2.keySet());
    return new Diff(data1, data2, new ArrayList<>(keys));
  }
}
