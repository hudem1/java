import java.util.HashSet;
import java.util.Set;

/**
 * video: https://www.youtube.com/watch?v=lYxEdtR5_xQ&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=3&ab_channel=AlgosWithMichael
 * input: array of integers
 * goal: find duplicate numbers in O(n) time complexity and O(1) SPACE complexity
 * output: a list of duplicates
 * info: the numbers x are in between 1 <= x <= n, n being the list size
 */
public class FindDuplicateNumbers {
  /**
   * time complexity: O(n)
   * space complexity: O(1), if we ignore the result array
   *  ^-- we don't use any intermediate data structure / mem alloc for computing solution
   */
  public Set<Integer> computeSolution(int[] numbers) {
    Set<Integer> duplicates = new HashSet<>();

    for (int i = 0; i < numbers.length; i++) {
      Integer currentNumber = Math.abs(numbers[i]);

      if (numbers[currentNumber - 1] < 0) duplicates.add(currentNumber);
      else numbers[currentNumber - 1] *= -1;
    }

    return duplicates;
  }

  public static void main(String[] args) {
    int[] input = {4, 3, 2, 7, 8, 2, 3, 1, 3};

    FindDuplicateNumbers fdn = new FindDuplicateNumbers();
    Set<Integer> res = fdn.computeSolution(input);

    for (Integer duplicate: res)
      System.out.print(duplicate + " ");
    System.out.println();
  }
}
