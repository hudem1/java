
public class merge_sorted_linked_lists {
  public static class ListNode implements Comparable<ListNode> {
    public int value;
    public ListNode next;

    public ListNode(int value, ListNode next) {
      this.value = value;
      this.next = next;
    }

    public ListNode(int value) {
      this(value, null);
    }

    @Override
    public String toString() {
      return this.value + " -> " + this.next;
    }

    @Override
    public int compareTo(ListNode other) {
      return this.value < other.value ?
        -1 : this.value > other.value ?
        1 : 0;
    }
  }

  public static void main(String[] args) {
    // 1 -> 2 -> 2 -> 4 -> 5
    ListNode l5 = new ListNode(5);
    ListNode l4 = new ListNode(4, l5);
    ListNode l2_1 = new ListNode(2, l4);
    ListNode l2 = new ListNode(2, l2_1);
    ListNode l1 = new ListNode(1, l2);

    // 2 -> 3 -> 4 -> 6
    ListNode l6 = new ListNode(6);
    ListNode l4_bis = new ListNode(4, l6);
    ListNode l3 = new ListNode(3, l4_bis);
    ListNode l2_2 = new ListNode(2, l3);

    // expected result: 1 -> 2 -> 2 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    ListNode res = merge2SortedlinkedLists(l1, l2_2);
    System.out.println(res);
  }

  public static ListNode merge2SortedlinkedLists(ListNode list1, ListNode list2) {
    ListNode current_l1 = list1;
    ListNode current_l2 = list2;

    // remove head at the end (just to have a 1st ListNode != null)
    ListNode res_head = new ListNode(0);

    ListNode res_current = res_head;
    while (current_l1 != null || current_l2 != null) {
      if (current_l1 == null) {
        // not a big deal if it does a shallow copy (prevent going through the rest of list2)
        res_current.next = current_l2;
        res_current = res_current.next;
        break;
      }
      else if (current_l2 == null) {
        // not a big deal if it does a shallow copy (prevent going through the rest of list1)
        res_current.next = current_l1;
        res_current = res_current.next;
        break;
      }
      else {
        // didn't really have to implement the Comparable interface (just for practice)
        // could have just done current_l1.value < current_l2.value
        if (current_l1.compareTo(current_l2) == -1) {
          res_current.next = new ListNode(current_l1.value);
          current_l1 = current_l1.next;
          // remark: if both list's current value are equal, then it will take the second's list value first
        } else {
          res_current.next = new ListNode(current_l2.value);
          current_l2 = current_l2.next;
        }
        res_current = res_current.next;
      }
    }

    // removing the head as explained before
    return res_head.next;
  }
}