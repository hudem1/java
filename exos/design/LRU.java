import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// public class LRU {
//   public static void main(String[] args) {
//     LRU lru = new LRU(3);
//     lru.put(1, 11);
//     lru.put(2, 12);
//     lru.put(3, 13);
//     lru.put(4, 14);
//     lru.put(2, 22);
//     lru.put(5, 15);

//     for (Map.Entry<Integer, Node> entry: lru.data.entrySet()) {
//       System.out.println(entry.getKey() + " " + entry.getValue().value);
//     }

//     Node current = lru.lruTail;
//     while (current != null) {
//       System.out.print(current.key + " -> ");
//       current = current.next;
//     }
//     System.out.println("null");
//   }

//   class Node {
//     Integer key;
//     Integer value;
//     Node previous;
//     Node next;

//     Node(Integer key, Integer value) {
//       this.key = key;
//       this.value = value;
//     }
//   }

//   private int capacity;
//   private Map<Integer, Node> data;
//   // the LEAST recently used elem is at the head <-- we remove from the head !
//   private Node lruHead;
//   // the MOST recently used elem is at the tail <-- we add to the tail !
//   private Node lruTail;
//   // tail_node -> node -> node -> node -> head_node

//   public LRU(int capacity) {
//     if (capacity == 0) throw new Error("Capacity must be superior to 0");

//     this.capacity = capacity;
//     this.data = new HashMap<>(capacity);
//     this.lruHead = null;
//     this.lruTail = null;
//   }

//   public int get(int key) {
//     if (!this.data.containsKey(key)) return -1;

//     Node node = this.data.get(key);

//     updateLRUOrder(node);

//     return node.value;
//   }

//   public void put(int key, int value) {
//     if (this.data.containsKey(key)) {
//       Node node = this.data.get(key);
//       node.value = value;
//       updateLRUOrder(node);
//       return;
//     }

//     // if the map has reached its capacity, remove the least recently used element
//     if (this.data.size() == this.capacity) removeLRUElem();

//     Node node = new Node(key, value);
//     this.data.put(key, node);

//     addNewLRUElem(node);
//   }

//   private void addNewLRUElem(Node node) {
//     if (this.data.size() == 1) {
//       this.lruTail = node;
//       this.lruHead = node;
//     } else {
//       node.next = this.lruTail;
//       this.lruTail.previous = node;
//       this.lruTail = node;
//     }
//   }

//   private void removeLRUElem() {
//     int lruKey = this.lruHead.key;

//     if (this.capacity == 1) {
//       this.lruHead = null;
//       this.lruTail = null;
//     } else {
//       this.lruHead = this.lruHead.previous;
//       this.lruHead.next = null;
//     }

//     this.data.remove(lruKey);
//   }

//   private void updateLRUOrder(Node valueNode) {
//     // if only one element OR if the element is the LRU tail, no need to update LRU elem priority
//     if (this.data.size() == 1 || valueNode == this.lruTail) return;

//     if (valueNode != this.lruHead && valueNode != this.lruTail) {
//       valueNode.previous.next = valueNode.next;
//       valueNode.next.previous = valueNode.previous;
//     } else if (valueNode == this.lruHead) {
//       this.lruHead = valueNode.previous;
//       this.lruHead.next = null;
//     }

//     valueNode.next = this.lruTail;
//     this.lruTail = valueNode;
//     valueNode.previous = null;
//   }
// }



public class LRU {
  public static void main(String[] args) {
    LRU lru = new LRU(3);
    lru.put(1, 11);
    lru.put(2, 12);
    lru.put(3, 13);
    lru.put(4, 14);
    lru.put(2, 22);
    lru.put(5, 15);

    // display in correct order
    for (Map.Entry<Integer, Integer> entry: lru.lruMap.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
  }

  private int capacity;
  private LinkedHashMap<Integer, Integer> lruMap;

  public LRU(int capacity) {
    this.capacity = capacity;

    this.lruMap = new LinkedHashMap<>(capacity, 1, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        // after each put() or putAll(), this fct will be called to see if we should remove lru entry
        return size() > capacity;
      };
    };
  }

  public Integer get(Integer key) {
    return this.lruMap.getOrDefault(key, -1);
  }

  public void put(Integer key, Integer value) {
    this.lruMap.put(key, value);
  }
}