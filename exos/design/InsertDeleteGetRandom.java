import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * video: https://www.youtube.com/watch?v=TD2g8UjXMLA&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=37&ab_channel=AlgosWithMichael
 * goal: We want to be able to insert, delete and get a random value, all these operations in O(1)
 * solution:
 *  - time complexity: O(1)
 *  - space complexity: O(n) for insert function only, with n being the number of elements being inserted
 */
public class InsertDeleteGetRandom {
  static Random random = new Random();
  static Map<Integer, Integer> elemToIndex = new HashMap<>();
  static List<Integer> elems = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println("Operation 1 is: " + insert(1));
    System.out.println("Operation 2 is: " + insert(2));
    System.out.println("Operation 2 is: " + insert(2));
    System.out.println("Operation 3 is: " + getRandom());
    System.out.println("Operation 4 is: " + remove(1));
    System.out.println("Operation 5 is: " + remove(2));
  }

  public static boolean insert(Integer value) {
    if (elemToIndex.containsKey(value))
      return false;

    Integer index = elems.size();
    elems.add(value);

    elemToIndex.put(value, index);

    return true;
  }

  public static boolean remove(Integer value) {
    if (!elemToIndex.containsKey(value))
      return false;

    Integer index = elemToIndex.remove(value);

    Integer lastElem = elems.get(elems.size() - 1);
    elems.set(index, lastElem);

    elemToIndex.put(lastElem, index);

    elems.removeLast();

    return true;
  }

  public static Integer getRandom() {
    int randomIndex = random.nextInt(elems.size());

    return elems.get(randomIndex);
  }
}