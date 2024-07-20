import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * video: https://www.youtube.com/watch?v=kZAZqn_J8Fo&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=40&ab_channel=AlgosWithMichael
 * code 2 operations:
 * - set(string key, string value, int timestamp)
 * - get(string key, int timestamp)
 * info:
 * - the get returns, for a specific key, the value of the closest timestamp <= to the provided timestamp
 * - all the data arriving into the set function is of increasing timestamp
 */
public class TimeBasedKeyValueStore {
  public static void main(String[] args) {
    InternalClass cl = new InternalClass();
    cl.set("A", "B", 2);
    System.out.println(cl.map.get("A").get(0));
    System.out.println(cl.get("A", 2)); // B
    System.out.println(cl.get("A", 3)); // B
    System.out.println(cl.get("A", 1)); // no value for timestamp 1

    cl.set("A", "C", 5);
    System.out.println(cl.get("A", 7)); // C
    System.out.println(cl.get("A", 4)); // B

    cl.set("D", "E", 9);
    System.out.println(cl.get("D", 20)); // E

    System.out.println(cl.get("X", 20)); // X key not set
  }

  static class InternalClass {
    class ListData {
      String value;
      int timestamp;

      public ListData(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
      }
    }

    private Map<String, List<ListData>> map = new HashMap<>();

    /**
     * time complexity: O(1)
     * space complexity: O(n), with n being the number of key-value pairs stored in the map
     */
    public void set(String key, String value, int timestamp) {
      if (!map.containsKey(key)) map.put(key, new ArrayList<>());
      List<ListData> values = map.get(key);
      // List<ListData> values = map.getOrDefault(key, new ArrayList<>());
      values.add(new ListData(value, timestamp));
      // map.put(key, values);
    }

    /**
     * time complexity: O(log(n)), with n being the number of values/timestamps for a specific key
     * space complexity: O(1)
     */
    public String get(String key, int timestamp) {
      List<ListData> values = this.map.get(key);

      if (values == null || values.size() == 0) return key + " not set";

      int left = 0;
      int right = values.size() - 1;

      while (left != right) {
        int mid = (left + right + 1) / 2;
        if (values.get(mid).timestamp > timestamp) {
          right = mid - 1;
        } else {
          // mid is a potential answer (because mapValue.timestamp is <= timestamp)
          left = mid;
        }
      }

      // values might still all be strictly > to the researched timestamp
      return values.get(left).timestamp <= timestamp ? values.get(left).value : "no value for timestamp " + timestamp;
    }
  }
}