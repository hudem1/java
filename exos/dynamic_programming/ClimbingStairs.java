
/**
 * video: https://www.youtube.com/watch?v=Es2JQUMao8g&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=48&ab_channel=AlgosWithMichael
 * input: target stair (Integer) <-- is positive
 * output: the number of ways to reach the input stair
 * You can only go 1 step or 2 steps at once
 * --> pretty much fibonacci actually
 */
public class ClimbingStairs {
  public static void main(String[] args) {
    Integer input = 7;
    Integer result = computeSolution(input);
    System.out.println("The result is: " + result);
    // assert result == 21;
  }

  // input is garanteed to be positive
  public static Integer computeSolution(Integer input) {
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
}
