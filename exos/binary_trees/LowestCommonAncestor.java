import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * video: https://www.youtube.com/watch?v=xuvw11Ucqs8&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=17&ab_channel=AlgosWithMichael
 * input: root of binary tree & 2 nodes to find their lowest common ancestor
 * ouput: the lowest common ancestor node
 */
public class LowestCommonAncestor {
  static class Node {
    Integer value;
    Node left;
    Node right;

    public Node(Integer value) {
      this.value = value;
    }
  }

  public Node computeSolution(Node root, Node node1, Node node2) {
    Stack<Node> ancestorsToNode1 = recursiveUsingStack(root, node1);
    Stack<Node> ancestorsToNode2 = recursiveUsingStack(root, node2);

    // FYI: can iterate over a stack and print its elements like below (because use an array under the hood I think)
    // for (Node elem: ancestorsToNode1) {
    //   System.out.print(elem.value +  " ");
    // }
    // System.out.println();

    // for (Node elem: ancestorsToNode2) {
    //   System.out.print(elem.value +  " ");
    // }

    Node lastAncestor = null;
    while (!ancestorsToNode1.isEmpty() && !ancestorsToNode2.isEmpty() && ancestorsToNode1.peek() == ancestorsToNode2.peek()) {
      lastAncestor = ancestorsToNode1.pop();
      ancestorsToNode2.pop();
    }

    // If we arrive until here, one leaf is higher than the other and ancestors are all equal until the higher leaf
    // In any case, whether one stack is empty or both peeks are not equal, return last seen ancestor
    return lastAncestor;
  }

  /**
   * time complexity: O(log(n)), binary tree search
   * space complexity: O(log(n)), adding ancestors to stack on path to leaf & recursive call stack
   * We use a stack because we create it at the found leaf and add ancestors starting from the leaf (in reverse order)
   * Alternative: we could have created an array in computeSolution() and pass it to recursive adding ancestors in correct order until found leaf
   */
  private Stack<Node> recursiveUsingStack(Node current, Node nodeToSearchFor) {
    // a node is an ancestor to itself (hence, adding it)
    if (current == nodeToSearchFor) {
      Stack<Node> ancestors = new Stack<>();
      ancestors.push(current);
      return ancestors;
    }

    Stack<Node> ancestors = null;

    if (current.value > nodeToSearchFor.value) {
      ancestors = recursiveUsingStack(current.left, nodeToSearchFor);
    } else if (current.value < nodeToSearchFor.value) {
      ancestors = recursiveUsingStack(current.right, nodeToSearchFor);
    }

    ancestors.add(current);

    return ancestors;
  }

  public static void main(String[] args) {
    Node root = new Node(15);
    Node n2 = new Node(10);
    Node n3 = new Node(20);
    Node n4 = new Node(8);
    Node n5 = new Node(13);
    Node n6 = new Node(17);
    Node n7 = new Node(30);
    Node n8 = new Node(11);
    Node n9 = new Node(14);

    root.left = n2;
    root.right = n3;

    n2.left = n4;
    n2.right = n5;

    n3.left = n6;
    n3.right = n7;

    n5.left = n8;
    n5.right = n9;

    /**
     * Resulting tree:
     *          15
     *       /      \
     *     10        20
     *    /  \      /   \
     *  8     13   17    30
     *       /  \
     *     11    14
     */

     LowestCommonAncestor lca = new LowestCommonAncestor();
     Node result = lca.computeSolution(root, n2, n6);
     System.out.println("The result is: " + result.value);
  }
}