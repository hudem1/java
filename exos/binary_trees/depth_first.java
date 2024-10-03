
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class depth_first {
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

    // expected: a - b - d - e - c - f
    iterativeVersion(a);
  }

  public static void iterativeVersion(Node root) {
    Deque<Node> stack = new LinkedList<>(Arrays.asList(root));
    // can also use Stack object: Stack<Node> nodes = new Stack<>();

    ArrayList<String> visited = new ArrayList<>();
    while (stack.size() != 0) {
      Node elem = stack.removeFirst();

      visited.add(elem.value);

      if (elem.right != null) stack.addFirst(elem.right);
      if (elem.left != null) stack.addFirst(elem.left);
    }

    System.out.println(visited);
  }

  public static void recursionVersion(Node node, List<Node> visitedNodes) {
    if (node.left != null) recursionVersion(node.left, visitedNodes);
    visitedNodes.add(node);
    if (node.right != null) recursionVersion(node.right, visitedNodes);
  }
}