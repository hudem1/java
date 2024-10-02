import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
  public static void main(String[] args) {
    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    int[] nums = {9, 1, 4, 2, 3, 3, 7};
    // int[] nums = {4, 10, 4, 3, 8, 9};
    int result = lis.computeSolution(nums);
    System.out.println("The result is: " + result);
    // System.out.println("The result is: " + lis.longest);
  }

  /**
   * Optimized
   *
   * Time complexity: O(n^2)
   * Space complexity: O(n)
   */
  public int computeSolution(int[] nums) {
    int[] longest = new int[nums.length];
    // init longest increasing path from all values at 1
    for (int i = 0; i < nums.length; i++) longest[i] = 1;

    // init max to 1
    int max = longest[nums.length - 1];

    for (int i = nums.length - 2; i >= 0; i--) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] < nums[j]) {
          longest[i] = Math.max(longest[i], longest[j] + 1);
        }
      }
      if (longest[i] > max) max = longest[i];
    }

    return max;
}

  private int longest = 0;

  public int computeSolution_bruteForce(int[] nums) {
    dfs(nums, 0, new ArrayList<>());

    return longest;
  }


  /**
   * Brute force
   *
   * time complexity: O(2^n) <-- for each elem, 2 calls
   *   ^-- This results in an exponential number of recursive calls
   *       For an array of size n, there can be up to 2^n recursive calls in the worst case
   *       In the worst case, the decision tree has a depth of n (we take all the ints in our path variable)
   * space complexity: 2 * O(n) == O(n) <-- stack size + path size
   *   ^-- The depth of the recursive stack can go up to n, and same for the path variable
   */
  public void dfs(int[] nums, int index, List<Integer> path) {
    if (index == nums.length) {
      if (path.size() > longest) longest = path.size();
      return;
    }

    if (path.isEmpty() || nums[index] > path.get(path.size() - 1)) {
      path.add(nums[index]);
      dfs(nums, index + 1, path);
      path.remove(path.size() - 1);
    }

    dfs(nums, index + 1, path);
  }
}
