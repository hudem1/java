import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class breadth_first {
  public static class Node {
    // should make them private and use getters/setters
    // but public just for facilitation
    public String value;
    public Node left;
    public Node right;

    public Node(String value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    public Node(String value) {
      this(value, null, null);
    }
  }

  public static void main(String[] args) {
    Node a = new Node("a");
    Node b = new Node("b");
    Node c = new Node("c");
    Node d = new Node("d");
    Node e = new Node("e");
    Node f = new Node("f");

    a.left = b;
    a.right = c;

    b.left = d;
    b.right = e;

    c.left = f;

    // expected: a - b - c - d - e - f
    iterativeVersion(a);
  }

  public static void iterativeVersion(Node root) {
    if (root == null) return;

    Queue<Node> file = new LinkedList<>(Arrays.asList(root));

    ArrayList<String> visitedNodes = new ArrayList<>();
    while (file.size() != 0) {
      Node current = file.remove();
      visitedNodes.add(current.value);

      if (current.left != null) file.add(current.left);
      if (current.right != null) file.add(current.right);
    }

    System.out.println(visitedNodes);
  }
}