
/**
 * video: https://www.youtube.com/watch?v=6cA_NDtpyz8&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=38&ab_channel=AlgosWithMichael
 * Given a binary tree, find the maximum path sum, which does not necessarily start at the root
 */
public class MaximumPathSum {
  static class TreeNode {
    Integer value;
    TreeNode left;
    TreeNode right;

    public TreeNode(Integer value) {
      this.value = value;
    }
  }

  static Integer maxPathSum = Integer.MIN_VALUE;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    TreeNode n2 = new TreeNode(5);
    TreeNode n3 = new TreeNode(-10);
    TreeNode n4 = new TreeNode(-5);
    TreeNode n5 = new TreeNode(1);
    TreeNode n6 = new TreeNode(50);
    TreeNode n7 = new TreeNode(-70);

    root.left = n2;
    root.right = n3;

    n2.left = n4;
    n2.right = n5;

    n3.left = n6;
    n3.right = n7;

    Integer maxPathSum = computeSolution(root);
    System.out.println("The max path sum is: " + maxPathSum);
  }

  /**
   * time complexity: O(n), with n being the number of nodes in our tree
   * space complexity: O(h), with h being the height of the tree (number of recursive calls)
   *  ^-- might be O(log(n)) if the tree is correctly balanced, or O(n) if the tree is unbalanced (like a linked list)
   */
  public static Integer computeSolution(TreeNode node) {
    postOrderTraversal(node);

    return maxPathSum;
  }

  private static Integer postOrderTraversal(TreeNode node) {
    if (node == null) return 0;

    Integer leftSubTreeMaxPath = Math.max(postOrderTraversal(node.left), 0);
    Integer rightSubTreeMaxPath = Math.max(postOrderTraversal(node.right), 0);

    maxPathSum = Math.max(leftSubTreeMaxPath + rightSubTreeMaxPath + node.value, maxPathSum);

    return Math.max(leftSubTreeMaxPath, rightSubTreeMaxPath) + node.value;
  }
}
