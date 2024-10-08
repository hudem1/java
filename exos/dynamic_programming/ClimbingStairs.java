import java.util.Map;

/**
 * video: https://www.youtube.com/watch?v=Es2JQUMao8g&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=48&ab_channel=AlgosWithMichael
 * input: target stair (Integer) <-- is positive
 * output: the number of ways to reach the input stair
 * You can only go 1 step or 2 steps at once
 * --> pretty much fibonacci actually
 */
public class ClimbingStairs {
  public static void main(String[] args) {
    // result: 1 2 3 5 8 13 21 34 etc
    Integer input = 7;
    Integer result = computeSolution(input);
    System.out.println("The result is: " + result);
    // assert result == 21;
  }

  /**
   * time complexity: O(n), with n being the input
   * space complexity: O(1)
   *
   * reminder: input is garanteed to be positive
   */
  public static Integer computeSolution(Integer input) {
    // if 1 stair, only 1 way
    // if 2 stairs, 2 ways (1 stair at a time OR 2 stairs at once)
    if (input <= 2) return input;

    Integer second_previous = 1;
    Integer previous = 2;

    for (int i = 3; i <= input; i++) {
      Integer next = second_previous + previous;
      second_previous = previous;
      previous = next;
    }

    return previous;
  }


  /**
   * Recursive version with cache
   *
   * WITHOUT CACHE:
   *    - time complexity: O(2^n), because for each function call there are 2 branches (and this until a depth of n)
   *        creating an exponential growth of fct calls as targetStair (n) increases
   *    - space complexity: O(n), because in the calls tree, we have a stack depth of at most n recursive calls
   *
   * WITH CACHE:
   *    - time complexity: O(n), because the solution is computed only once for each subproblem
   *        ^-- in TOTAL, there are only n (recursive) calls happening
   *    - space complexity: O(n), because: - in the calls tree, we have a stack depth of at most n recursive calls --> O(n)
   *                                       - the cache will hold one entry for each stair that is <= targetStair --> O(n)
   */
  public Integer recursiveVersion(int targetStair, Map<Integer, Integer> cache) {
    if (targetStair <= 2) return Integer.valueOf(targetStair);
    if (cache.containsKey(targetStair)) return cache.get(targetStair);

    Integer res = recursiveVersion(targetStair - 1, cache) + recursiveVersion(targetStair - 2, cache);
    cache.put(targetStair, res);

    return res;
  }
}
