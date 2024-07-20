
/**
 * video: https://www.youtube.com/watch?v=RzHzIJYEiys&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=45&ab_channel=AlgosWithMichael
 * input: 2 strings to compare, each composed of digits and periods
 * ouput: return -1 if 1st string is lower, or 1 if 1st string is larger, or 0 if equal
 */
public class CompareVersionNumber {
  public static void main(String[] args) {
    String input1 = "1.13.2.0";
    String input2 = "1.13.000201";
    Integer result = computeSolution(input1, input2);
    // result should be -1
    System.out.println("The result is: " + result);

    input1 = "1";
    input2 = "1.00.00000.0";
    result = computeSolution(input1, input2);
    // result should be 0
    System.out.println("The result is: " + result);
  }

  /**
   * time complexity: O(n + m), with n and m being the length of the 2 input strings respectively
   * ^-- due to both sequential/consecutive split operations
   * ^-- we also considered the while loop ofc [O(max(n, m))], which gives for the whole fct:
   *      - either O(n + m) + O(n) = O(n) + O(m) + O(n) = 2 * O(n) + O(m) = O(n + m)
   *      - or O(n + m) + O(m) = O(n) + O(m) + O(m) = O(n) + 2 * O(m) = O(n + m)
   * space complexity: O(n + m), with n and m being the length of the 2 input strings respectively
   */
  public static Integer computeSolution(String input1, String input2) {
    String[] input1Numbers = input1.split("\\.");
    String[] input2Numbers = input2.split("\\.");

    int input1Pointer = 0;
    int input2Pointer = 0;

    // just fyi, even though the time complexity of the whole function is O(n + m) as stated above
    // the time complexity of this while loop is O(max(n, m)) as we move both pointers simultaneously
    while (input1Pointer < input1Numbers.length || input2Pointer < input2Numbers.length) {
      int input1Value = input1Pointer < input1Numbers.length ?
        Integer.parseInt(input1Numbers[input1Pointer++]) : 0;

      int input2Value = input2Pointer < input2Numbers.length ?
        Integer.parseInt(input2Numbers[input2Pointer++]) : 0;

      // if equal, we go to the next iteration
      int comparisonResult = input1Value - input2Value;
      if (comparisonResult < 0) return -1;
      else if (comparisonResult > 0) return 1;
    }

    // if we get here, it means both revision versions are equal
    return 0;
  }
}
