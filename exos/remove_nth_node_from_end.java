
// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

// Given the head of a linked list, remove the nth node from the end of the list and return its head.

public class remove_nth_node_from_end {
  public static class ListNode {
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
  }

  public static void main(String[] args) {
    // 1 -> 2 -> 3 -> 4 -> 5
    ListNode l5 = new ListNode(5);
    ListNode l4 = new ListNode(4, l5);
    ListNode l3 = new ListNode(3, l4);
    ListNode l2 = new ListNode(2, l3);
    ListNode l1 = new ListNode(1, l2);

    ListNode res = removeNthFromEnd(l1, 2);
    System.out.println(res);

    // 1
    ListNode l6 = new ListNode(1);

    ListNode res2 = removeNthFromEnd(l6, 1);
    System.out.println(res2);

    // 1
    ListNode l7 = new ListNode(2);
    ListNode l8 = new ListNode(1, l7);

    ListNode res3 = removeNthFromEnd(l8, 1);
    System.out.println(res3);
  }

  public static ListNode removeNthFromEnd(ListNode head, int nthElemToRemove) {
    ListNode slow = head;
    ListNode fast = head;

    // the linked list size must at least be equal (or greater than) nthElemToRemove
    for (int i = 0; i < nthElemToRemove; i++) fast = fast.next;

    // if fast == null, it means the list size was equal to nthElemToRemove.
    // Therefore, we want to remove the head element.
    if (fast == null) return head.next;

    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }

    slow.next = slow.next.next;

    return head;
  }
}