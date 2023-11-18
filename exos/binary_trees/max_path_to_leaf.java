import java.util.ArrayList;

public class max_path_to_leaf {
  public static class Node {
    // should make them private and use getters/setters
    // but public just for facilitation
    public Integer value;
    public Node left;
    public Node right;

    public Node(Integer value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    public Node(Integer value) {
      this(value, null, null);
    }
  }

  public static void main(String[] args) {
    Node n1 = new Node(5);
    Node n2 = new Node(11);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(2);
    Node n6 = new Node(1);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    n3.left = n6;

    // Result: following tree
    //     5
    //    / \
    //   11  3
    //  / \   \
    // 4   2   1

    ArrayList<Integer> path = new ArrayList<>();
    // expected: 20
    Integer res = maxPathToLeaf(n1, path);
    System.out.println(res);
    System.out.println(path);
  }

  public static Integer maxPathToLeaf(Node root, ArrayList<Integer> path) {
    if (root == null) return Integer.MIN_VALUE;
    if (root.left == null && root.right == null) return root.value;

    Integer subLeftTreeMax = maxPathToLeaf(root.left, path);
    Integer subRightTreeMax = maxPathToLeaf(root.right, path);

    return root.value + Math.max(subLeftTreeMax, subRightTreeMax);
  }
}