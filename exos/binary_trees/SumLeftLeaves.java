
/**
 * video: https://www.youtube.com/watch?v=DSqSHwDE82M&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=12&ab_channel=AlgosWithMichael
 */
public class SumLeftLeaves {
  static class Node {
    Integer value;
    Node left;
    Node right;

    Node(Integer value) {
      this.value = value;
    }
  }

  /**
   * time complexity: O(n), n being the number of nodes in the binary tree
   * space complexity: O(n), n being the number of nodes in the binary tree
   *  ^-- because of the calls stack
   */
  public int computeSolution(Node node) {
    if (node == null) return 0;

    int sum = 0;

    if (node.left != null) {
      if (node.left.left == null && node.left.right == null) {
        sum += node.left.value;
      } else sum += computeSolution(node.left);
    }

    sum += computeSolution(node.right);

    return sum;
  }

  public static void main(String[] args) {
    Node root = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Node n8 = new Node(8);
    Node n9 = new Node(9);

    root.left = n2;
    root.right = n3;

    n2.left = n4;
    n2.right = n5;

    n3.left = n6;
    n3.right = n7;

    n5.left = n8;
    n5.right = n9;

    SumLeftLeaves sll = new SumLeftLeaves();
    int sum = sll.computeSolution(root);
    System.out.println("The sum is: " + sum);
  }
}
