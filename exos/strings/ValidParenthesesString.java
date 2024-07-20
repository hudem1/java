import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * video: https://www.youtube.com/watch?v=PDO3vvly7eU&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=46&ab_channel=AlgosWithMichael
 * input: String to verify/validate
 * output: valid String (String with minimum of invalid parenthese removed)
 * goal: make the string valid by removing the minimum of invalid parentheses
 */
public class ValidParenthesesString {
  public static void main(String[] args) {
    String input = "fo(o(bar)ba)z)";
    String result = computeSolution(input);
    // The result should be: "fo(o(bar)ba)z"
    System.out.println("The result is: " + result);
  }

  /**
   * time complexity: 2 * 0(n) = O(n), with n being the length of the input string
   * space complexity: 0(n), with n being the length of the input string <-- for example if we have only '(' or ')'
   */
  public static String computeSolution(String input) {
    Stack<Integer> openParenthesesIndices = new Stack<>();
    Set<Integer> invalidParenthesesIndices = new HashSet<>();

    for (int i = 0; i < input.length(); i++) {
      char elem = input.charAt(i);
      if (elem == '(') {
        openParenthesesIndices.push(i);
      } else if (elem == ')') {
        if (!openParenthesesIndices.isEmpty()) openParenthesesIndices.pop();
        else invalidParenthesesIndices.add(i);
      }
    }

    while (!openParenthesesIndices.isEmpty())
      invalidParenthesesIndices.add(openParenthesesIndices.pop());

    StringBuilder validString = new StringBuilder("");
    for (int i = 0; i < input.length(); i++) {
      if (invalidParenthesesIndices.contains(i))
        continue;

      validString.append(input.charAt(i));
    }

    return validString.toString();
  }
}
