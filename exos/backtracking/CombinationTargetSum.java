import java.util.ArrayList;
import java.util.List;

/**
 * video: https://neetcode.io/problems/combination-target-sum
 *
 * Input: You are given an array of distinct integers nums and a target integer target.
 * Goal: Your task is to return a list of all unique combinations of nums where the chosen numbers sum to target.
 * Info: The same number may be chosen from nums an unlimited number of times. Two combinations are the same if the frequency of each of the chosen numbers is the same, otherwise they are different.
 *       You may return the combinations in any order and the order of the numbers in each combination can be in any order.
 */
public class CombinationTargetSum {
  public static void main(String[] args) {
    List<Integer> nums = new ArrayList<>() {{
      add(2);
      add(5);
      add(6);
      add(9);
    }};

    CombinationTargetSum cts = new CombinationTargetSum();
    List<List<Integer>> combinations = cts.computeSolution(nums, 9);

    for (List<Integer> combination: combinations) {
      for (Integer num: combination) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }

  /**
   * time complexity: O(Math.pow(2, target))
   *  ^-- each time, we make 2 branches, keep the current num and go to next num (hence the Math.pow(2, x))
   *        and then our ("binary" because each node has 2 children) decision tree has a depth of "target"
   *        because in the worst case we have 1s that we add to finally sum up to target number
   * space complexity:
   *    - path variable: O(k), in worst case we have only 1s, so k values in path
   *    - call stack: O(k)
   *    - results: O(k * n) <-- not sure
   */
  public List<List<Integer>> computeSolution(List<Integer> nums, Integer target) {
    List<List<Integer>> results = new ArrayList<>();

    dfs(nums, target, results, new ArrayList<>(), 0);

    return results;
  }

  private void dfs(List<Integer> nums, Integer target, List<List<Integer>> results, List<Integer> path, int index) {
    if (target == 0) {
      results.add(new ArrayList<>(path));
      return;
    } else if (target < 0 || index >= nums.size()) {
      return;
    }

    path.add(nums.get(index));
    dfs(nums, target - nums.get(index), results, path, index);
    path.removeLast();

    dfs(nums, target, results, path, index + 1);
  }
}
