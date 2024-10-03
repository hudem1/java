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
    TimeBasedKeyValueStore tmkvs = new TimeBasedKeyValueStore();
    tmkvs.set("A", "B", 2);
    System.out.println(tmkvs.map.get("A").get(0));
    System.out.println(tmkvs.get("A", 2)); // B
    System.out.println(tmkvs.get("A", 3)); // B
    System.out.println(tmkvs.get("A", 1)); // no value for timestamp 1

    tmkvs.set("A", "C", 5);
    System.out.println(tmkvs.get("A", 7)); // C
    System.out.println(tmkvs.get("A", 4)); // B

    tmkvs.set("D", "E", 9);
    System.out.println(tmkvs.get("D", 20)); // E

    System.out.println(tmkvs.get("X", 20)); // X key not set
  }

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

    map.get(key).add(new ListData(value, timestamp));
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

    while (left < right) {
      // HAVE to do + 1 because if values contain for example 2 timestamps strictly greater than the timestamp we search for
      // there would be an infinite loop without the + 1
      int mid = (left + right + 1) / 2;

      if (values.get(mid).timestamp > timestamp) right = mid - 1;
      // mid is a potential answer (because mapValue.timestamp is <= timestamp)
      else left = mid;
    }

    // values might still all be strictly > to the researched timestamp
    return values.get(left).timestamp <= timestamp ? values.get(left).value : "no value for timestamp " + timestamp;
    // also, a value is <, then left == right
  }
}