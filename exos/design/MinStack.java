import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * video: https://www.youtube.com/watch?v=3hd7zLNesaE&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=13&ab_channel=AlgosWithMichael
 * input: Design a stack that supports push, pop, top, and retrieving the minimum element in constant time
 */
public class MinStack {
  private Stack<Integer> stack = new Stack<>();
  private Stack<Integer> minimums = new Stack<>();

  public void push(Integer value) {
    this.stack.push(value);

    int minElem = minimums.isEmpty() || value < minimums.peek() ? value : minimums.peek();
    minimums.push(minElem);
  }

  public void pop() {
    this.stack.pop();
    this.minimums.pop();
  }

  public Integer top() {
    return this.stack.peek();
  }

  public Integer getMin() {
    return this.minimums.peek();
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();

    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    System.out.println("Top: " + minStack.top());
    System.out.println("Min: " + minStack.getMin());
    minStack.pop();
    System.out.println("Top: " + minStack.top());
    System.out.println("Min: " + minStack.getMin());
  }
}
