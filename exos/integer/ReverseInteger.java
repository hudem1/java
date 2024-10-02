
/**
 * video: https://www.youtube.com/watch?v=B-Yc10DUaM8&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=5&ab_channel=AlgosWithMichael
 * input: an integer to reverse
 * output: the reversed integer
 * info: if the reversed integer overflows, return 0
 * goal: reverse it without using strings and reversing them
 */
public class ReverseInteger {
  /**
   * time complexity: O(n), with n being the number of digits of the input integer to reverse
   * space complexity: O(1)
   */
  public int computeSolution(Integer inputToReverse) {
    long reversedInteger = 0;

    while (inputToReverse != 0) {
      // % 10 isolates the lowest digit of the number
      Integer remainder = inputToReverse % 10;
      reversedInteger = reversedInteger * 10 + remainder;

      if (reversedInteger < Integer.MIN_VALUE || reversedInteger > Integer.MAX_VALUE)
        return 0;

      // / 10 removes the lowest digit of the number
      inputToReverse = inputToReverse / 10;
    }

    return (int) reversedInteger;
  }

  public static void main(String[] args) {
    ReverseInteger ri = new ReverseInteger();
    int result = ri.computeSolution(123);
    System.out.println("The result is: " + result);
  }
}
