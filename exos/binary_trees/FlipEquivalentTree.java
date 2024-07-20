
/**
 * video: https://www.youtube.com/watch?v=QrHTec92270&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=42&ab_channel=AlgosWithMichael
 * input: 2 heads of binary trees
 * output: boolean stating if both trees are considered equivalent
 * goal: 2 trees are equivalent:
 *    - if the left and right children of both trees are the same respectively
 *    - or the left child of tree1 and right child of tree2 are the same, and same for the right and left children of both trees respectively
 */
public class FlipEquivalentTree {
  static class ListNode {
    Integer value;
    ListNode left;
    ListNode right;

    ListNode(Integer value) {
      this.value = value;
    }
  }

  private static ListNode buildTree1() {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);
    ListNode n6 = new ListNode(6);
    ListNode n7 = new ListNode(7);
    ListNode n8 = new ListNode(8);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    n3.left = n6;

    n5.left = n7;
    n5.right = n8;

    return n1;
  }

  private static ListNode buildTree2() {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);
    ListNode n6 = new ListNode(6);
    ListNode n7 = new ListNode(7);
    ListNode n8 = new ListNode(8);

    n1.left = n3;
    n1.right = n2;

    n3.right = n6;

    n2.left = n4;
    n2.right = n5;

    n5.left = n8;
    n5.right = n7;

    return n1;
  }

  public static void main(String[] args) {
    ListNode tree1 = buildTree1();
    ListNode tree2 = buildTree2();

    boolean result = computeSolution(tree1, tree2);
    // should be display true
    System.out.println("The result is: " + result);
  }

  /**
   * time complexity: O(min(n, m)), with n and m being the size of tree1 and tree2, respectively
   *  ^-- min(n, m) because if both trees are not the same size, the fct will "go back up the call stack" and exit once it encountered a null node
   * space complexity: O(min(n, m)), with n and m being the size of tree1 and tree2, respectively
   *  ^-- min(n, m) because if both trees just have let's say left children -> left children -> etc...,
   *      it gives a linear complexity, and we take the min() for the same reason than for the time complexity
   *
   * Remark: Even if you have to flip 2 children for the tree to be equivalent,
   * the children's children stay the children's children (they do not get flipped)
   * The flip occurs only at the current node level (not below)
   */
  public static boolean computeSolution(ListNode tree1, ListNode tree2) {
    if (tree1 == null && tree2 == null)
      return true;

    if (tree1 == null || tree2 == null || !tree1.value.equals(tree2.value))
      return false;

    // just fyi, should NOT make this check because
    // we need to go see recursively in the lower levels if ALL levels are really equivalent
    // if (tree1.value == tree2.value)
    //   return true;

    // if the 1st possibility is true, don't need to go compute the 2nd possibility because of the ||
    // probably the compiler knows how about this opti.
    // Otherwise, I can just create a first if statement that returns true if the 1st possibility is true
    // and if it's false, i can return whatever the result of the 2nd possibility is
    return (computeSolution(tree1.left, tree2.left) && computeSolution(tree1.right, tree2.right))
      || (computeSolution(tree1.left, tree2.right) && computeSolution(tree1.right, tree2.left));
  }
}
