import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * video: https://www.youtube.com/watch?v=BlPwGRQysDI&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=49&ab_channel=AlgosWithMichael
 * example input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * output: 22 <-- ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * valid operators: +, -, *, /
 */
public class ReversePolishNotation {
  public static void main(String[] args) {
    List<String> input = new ArrayList<>(
      Arrays.asList("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")
    );

    Integer result = computeSolution(input);
    System.out.println("The result is: " + result);
    assert(result == 22);
  }

  public static Integer computeSolution(List<String> input) {
    if (input == null || input.isEmpty())
      throw new Error("");

    Stack<Integer> stack = new Stack<>();

    for (String elem: input) {
      switch (elem) {
        case "+":
          stack.push(stack.pop() + stack.pop());
          break;
        case "-":
          Integer substractionSecondOperand = stack.pop();
          stack.push(stack.pop() - substractionSecondOperand);
          break;
        case "*":
          stack.push(stack.pop() * stack.pop());
          break;
        case "/":
          Integer divisionSecondOperand = stack.pop();
          stack.push(stack.pop() / divisionSecondOperand);
          break;
        // default case is the string is an integer
        default:
          stack.push(Integer.parseInt(elem));
          break;
      }
    }

    return stack.pop();
  }
}
