import java.util.HashSet;
import java.util.Set;

/**
 * link: https://neetcode.io/problems/longest-consecutive-sequence
 *
 * difficulty: Medium
 *
 * Input: Given an array of integers nums
 * Output: Return the length of the longest consecutive sequence of elements.
 * Info: A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element.
 *       Input length is max 1000.
 * Requirement: You must write an algorithm that runs in O(n) time.
 *
 * Example:
 *    int[] nums = {3, 20, 4, 10, 3, 2, 5};
 *    the longest consecutive sequence is 2 -> 3 -> 4 -> 5
 */
public class LongestConsecutiveSequence {
  public static void main(String[] args) {
    LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
    int[] nums = {3, 20, 4, 10, 3, 2, 5};
    int longest = lcs.computeSolution(nums);
    // Should print out 4
    System.out.println("The result is: " + longest);
  }

  /**
   * time complexity: O(n), <-- 2 * O(n) in worst case
   * space complexity: O(n)
   *
   * - A brute force algo would be to sort the numbers, and check if (previous value == current value - 1)
   *   ^-- O(nlogn) time complex
   * - Another algo is to create an array of size 1000 (max size),
   *    go through the values and set 1 at index "value" in 1000-elems array
   *    go through the 1000-elems array and count consecutive 1s
   *  ^-- O(n) time complex but less efficient in space complex
   */
  public int computeSolution(int[] nums) {
    Set<Integer> uniqueNumbers = new HashSet<>();
    for (int num: nums) {
      uniqueNumbers.add(num);
    }

    int longest = 0;
    for (int num: uniqueNumbers) {
      // Do not count consecutive values from an intermediate value, only from the minimum value in a consec seq
      if (uniqueNumbers.contains(num - 1)) continue;

      // Count up the consecutive values in a sequence
      int currentLength = 1;
      while (uniqueNumbers.contains(num++ + 1)) {
          ++currentLength;
      }

      if (currentLength > longest) longest = currentLength;
    }

    return longest;
  }
}