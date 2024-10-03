import java.util.HashSet;
import java.util.Set;

/**
 * video: https://www.youtube.com/watch?v=9SnkdYXNIzM&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=14&ab_channel=AlgosWithMichael
 * input: an array of integers (could be negative, zero or positive)
 * output: the first missing positive integer
 * goal: return the first missing positive integer in O(n) time complexity and 0(1) space complexity
 * examples:
 * 1) [-9, 2, 1] -> 3
 * 2) [7, 8, 9] -> 1
 * 3) [4, 1, 2, 7] -> 3
 */
public class FindFirstMissingPositiveInteger {
  public Integer computeSolution(int[] numbers) {
    // return bruteForce(numbers);
    // return solutionVersion1(numbers);
    return solutionVersion2(numbers);
  }

  /**
   * time complexity: O(n), with n being the length of numbers
   *  ^-- in the worst case, example: [2, 3, 1], as soon as the first iteration we would have n swaps
   *      (but for the next iterations, the array values would be correctly placed)
   *      and therefore, the time complexity would be 2 * O(n) == O(n)
   * space complexity: O(1)
   */
  private Integer solutionVersion1(int[] numbers) {
    // place all IN RANGE values to their correct place, value 2 would be placed at index 1
    for (int i = 0; i < numbers.length; i++) {
      // ignore number if not in range
      if (numbers[i] <= 0 || numbers[i] > numbers.length) continue;

      // while number is in range and not correctly placed in array
      while (1 <= numbers[i] && numbers[i] <= numbers.length && numbers[i] != i + 1) {
        // put current value to correct place and put other place's value here
        int value = numbers[i];
        numbers[i] = numbers[value - 1];
        numbers[value - 1] = value; // value correctly placed
      }
    }

    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] != i + 1) return i + 1;
    }

    // Edge case where all numbers in input are consecutive from 1 to the input length
    // Therefore, we need to return the next positive number (input length + 1)
    return numbers.length + 1;
  }

  /**
   * time complexity: O(n), with n being the length of numbers
   * space complexity: O(1)
   */
  private Integer solutionVersion2(int[] numbers) {
    // First pass to replace negative/zero integers by positive out of range integers
    for (int i = 0; i < numbers.length; i++) {
      // if number is not in valid range (especially negative/zero), we set it to a POSITIVE (important!) value
      // that is strictly superior to the valid range
      if (numbers[i] <= 0) numbers[i] = numbers.length + 1;
    }

    // Second pass to record elements in range (swap corresponding index's value to negative value)
    for (int i = 0; i < numbers.length; i++) {
      int value = Math.abs(numbers[i]);
      // if value is in valid range (and ignore out of range values)
      // and value's sign has not already been swapped to negative sign
      // example: we encounter several times a same value, we want to keep the array value at the corresponding index negative)
      if (value <= numbers.length && numbers[value - 1] > 0) {
        numbers[value - 1] *= -1;
      }
    }

    // Third pass to find the 1st positive index (first missing positive integer)
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] > 0) return i + 1;
    }

    // Edge case where all numbers in input are consecutive from 1 to the input length
    // Therefore, we need to return the next positive number (input length + 1)
    return numbers.length + 1;
  }

  // if we didn't have to care about O(1) space complexity
  // time complexity: O(n)
  // space complexity: O(n)
  private Integer bruteForce(int[] numbers) {
    Set<Integer> positiveIntegersInRange = new HashSet<>();

    for (int num: numbers) {
      // ignore numbers not in range
      if (num <= 0 || num > numbers.length) continue;

      positiveIntegersInRange.add(num);
    }

    for (int i = 1; i <= positiveIntegersInRange.size(); ++i) {
      if (!positiveIntegersInRange.contains(i)) return i;
    }

    // Edge case where the positive numbers in range are consecutive from 1 to their length
    // Therefore, we need to return the next positive number (their length + 1)
    return positiveIntegersInRange.size() + 1;
  }

  public static void main(String[] args) {
    FindFirstMissingPositiveInteger cl = new FindFirstMissingPositiveInteger();

    // should return 4
    int[] numbers = {7, -2, 3, 1, 2, 20, -5};
    // int[] numbers = {2, 3, 1};
    Integer result = cl.computeSolution(numbers);

    System.out.println("The result is: " + result);
  }
}
