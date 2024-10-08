import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://www.youtube.com/watch?v=REWa8dauVuA&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=51&ab_channel=AlgosWithMichael
 *
 */
public class StockPrice {
  private Map<Integer, Integer> records;
  private TreeMap<Integer, Integer> prices;
  private int latestTimestamp;

  // public static void main(String[] args) {
    
  // }

  public StockPrice() {
    this.records = new HashMap<>();
    this.prices = new TreeMap<>();
    this.latestTimestamp = 0;
  }

  public void update(int timestamp, int price) {
    if (this.records.containsKey(timestamp) && this.records.get(timestamp) == price)
      return;

    Integer prevPrice = this.records.put(timestamp, price);

    if (prevPrice != null) {
      Integer counter = this.prices.get(prevPrice);

      if (counter == 1) this.prices.remove(timestamp);
      else this.prices.put(prevPrice, counter - 1);
    }

    this.prices.put(price, this.prices.getOrDefault(price, 0) + 1);

    if (timestamp > this.latestTimestamp) this.latestTimestamp = timestamp;
  }

  public int current() {
    return this.records.get(this.latestTimestamp);
  }

  public int maximum() {
    return this.prices.lastKey();
  }

  public int minimum() {
    return this.prices.firstKey();
  }
}
