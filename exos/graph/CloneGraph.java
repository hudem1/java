import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * video: https://www.youtube.com/watch?v=e5tNvT1iUXs&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=11&ab_channel=AlgosWithMichael
 * input: a undirected graph of nodes (actually a single node containing a list of adjacent nodes)
 * ouput: a clone of the graph
 */
public class CloneGraph {
  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);

    n1.addNeighbour(n2);
    n1.addNeighbour(n3);

    n2.addNeighbour(n1);
    n2.addNeighbour(n4);

    n3.addNeighbour(n1);
    n3.addNeighbour(n4);

    n4.addNeighbour(n2);
    n4.addNeighbour(n3);

    /**
     * Here is the resulting graph:
     * 1 --- 2
     * |     |
     * 3 --- 4
     */

    CloneGraph cg = new CloneGraph();
    // results are printed in used functions
    Node result = cg.computeSolution(n1);
  }

  public static class Node {
    private Integer value;
    private List<Node> neighbours;

    public Node(Integer value) {
      this.value = value;
      this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(Node neighbour) {
      this.neighbours.add(neighbour);
    }
  }

  public Node computeSolution(Node initialNode) {
    if (initialNode == null) return null;

    Map<Integer, Node> indicesToNodes = new HashMap<>();
    Node firstNode = dfsUsingRecursion(initialNode, indicesToNodes);
    // only to display results
    displayNodes(indicesToNodes);

    // Node firstNode = dfsUsingStack(initialNode);

    return firstNode;
  }

  /**
   * time complexity: O(n), with n being the number of nodes
   * space complexity: O(n), with n being the number of nodes
   *  ^-- actually 2 * O(n) because of the nodes creation + call stack growing to n in worst case (if we have a sort of a linked list of nodes)
   */
  private Node dfsUsingRecursion(Node currentNode, Map<Integer, Node> indicesToNodes) {
    if (indicesToNodes.containsKey(currentNode.value))
      return indicesToNodes.get(currentNode.value);

    Node clonedNode = new Node(currentNode.value);
    indicesToNodes.put(clonedNode.value, clonedNode);

    for (Node node: currentNode.neighbours) {
      Node clonedNeighbour = dfsUsingRecursion(node, indicesToNodes);
      clonedNode.addNeighbour(clonedNeighbour);
    }

    return clonedNode;
  }

  /**
   * In the below algo, we go through neighbours in reverse order (as we use a stack) in comparison to the original graph
   *  ^-- example: instead of Node 1 having neighbours [2, 3], the Node 1 clone has neighbours [3, 2]
   *  ^-- It still gives an equivalent graph
   * For BFS, i think it's the same algo as the following, only using a queue instead of a stack
   *  ^-- in BFS, we go through neighbours in correct order in comparison to the original graph
   */
  private Node dfsUsingStack(Node initialNode) {
    Map<Integer, Node> indicesToNodes = new HashMap<>();

    Stack<Node[]> nodesToClone = new Stack<>();
    // for BFS
    // Queue<Node[]> nodesToClone = new LinkedList<>();
    nodesToClone.push(new Node[] {initialNode, null});

    while (!nodesToClone.isEmpty()) {
      Node[] toCloneToParent = nodesToClone.pop();
      Node toClone = toCloneToParent[0]; // node to clone
      Node toCloneParent = toCloneToParent[1]; // already-cloned parent

      Node clone = new Node(toClone.value);
      indicesToNodes.put(clone.value, clone);

      if (toCloneParent != null) {
        toCloneParent.addNeighbour(clone);
      }

      for (Node neighbour: toClone.neighbours) {
          if (!indicesToNodes.containsKey(neighbour.value))
            nodesToClone.push(new Node[] {neighbour, clone});
          else {
            Node alreadyClonedNeighbour = indicesToNodes.get(neighbour.value);
            clone.addNeighbour(alreadyClonedNeighbour);
          }
      }
    }

    // only to display results
    displayNodes(indicesToNodes);

    return indicesToNodes.get(initialNode.value);
  }


  private void displayNodes(Map<Integer, Node> indicesToNodes) {
    for (Map.Entry<Integer, Node> entry: indicesToNodes.entrySet()) {
      System.out.print("Node index: " + entry.getKey() + " ");
      System.out.print("Node value: " + entry.getValue().value + " ");
      System.out.print("Node neighbours: ");
      for (Node node: entry.getValue().neighbours) {
        System.out.print(node.value + " ");
      }
      System.out.println();
    }
  }
}
