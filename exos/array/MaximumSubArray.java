import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * video: https://www.youtube.com/watch?v=tmakGVOGV3A&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=39&ab_channel=AlgosWithMichael
 * input: array
 * output: the sum of the sub array values giving the maximum sum
 */
public class MaximumSubArray {
  public static void main(String[] args) {
    List<Integer> input = new ArrayList<>(
      Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    );

    Integer maxSum = computeSolution(input);
    // should be 6 <-- [4, -1, 2, 1]
    System.out.println("The result is: " + maxSum);
  }

  /**
   * Also known as Kadane's algo
   * time complexity: O(n), with n being the length of the input
   * space complexity: O(1)
   */
  public static Integer computeSolution(List<Integer> input) {
    if (input == null || input.size() == 0) return Integer.MIN_VALUE;

    Integer maxSum = input.get(0);

    for (int i = 1; i < input.size(); i++) {
      Integer maxVal = Math.max(input.get(i - 1) + input.get(i), input.get(i));
      input.set(i, maxVal);

      if (maxVal > maxSum) maxSum = maxVal;
    }

    return maxSum;
  }
}
