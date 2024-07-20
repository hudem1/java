import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class vertical_order_traversal {
  public static class Node {
    private int value;
    private Node left;
    private Node right;

    Node(int _value) {
      this.value = _value;
    }

    public void setLeft(Node left) {
      this.left = left;
    }

    public void setRight(Node right) {
      this.right = right;
    }

    @Override
    public String toString() {
      return String.valueOf(this.value);
    }
  }

  public static class Pair<K, V> {
    private K left;
    private V right;

    public Pair(K left, V right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Pair) {
        @SuppressWarnings("unchecked")
        Pair<K, V> pair = (Pair<K, V>) obj;

        return this.left.equals(pair.left) && this.right.equals(pair.right);
      }

      return false;
    }

    @Override
    public int hashCode() {
      return this.left.hashCode() + this.right.hashCode();
    }

    @Override
    public String toString() {
      return this.left + "-" + this.right;
    }
  }


  public static void main(String[] args) {
    Node root = new Node(3);
    Node n9 = new Node(9);
    Node n20 = new Node(20);
    Node n15 = new Node(15);
    Node n7 = new Node(7);

    root.setLeft(n9);
    root.setRight(n20);
    n20.setLeft(n15);
    n20.setRight(n7);

    /**
     * binary tree resultant:
     *        3
     *      /   \
     *     9     20
     *          /  \
     *        15    7
     */

    traversal(root);
  }

  public static void traversal(Node root) {
    Queue<Pair<Node, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<Node, Integer>(root, 0)); // add at the end (tail)

    Map<Integer, List<Integer>> map = new HashMap<>();

    int minColValue = 0;
    int maxColValue = 0;

    while (queue.size() > 0) {
      Pair<Node, Integer> current = queue.poll(); // remove from the beginning (head)

      if (current.left.left != null)
        queue.add(new Pair<Node,Integer>(current.left.left, current.right - 1));

      if (current.left.right != null)
        queue.add(new Pair<Node, Integer>(current.left.right, current.right + 1));

      List<Integer> list = map.putIfAbsent(current.right, new ArrayList<>(Arrays.asList(current.left.value)));
      if (list != null) list.add(current.left.value);

      if (current.right < minColValue) minColValue = current.right;
      else if (current.right > maxColValue) maxColValue = current.right;
    }

    // expected result is: 9 3 15 20 7
    for (int i = minColValue; i <= maxColValue; i++) {
      List<Integer> colValues = map.get(i);
      colValues.forEach(value -> System.out.print(value + " "));
    }
  }
}