
/**
 * video: https://www.youtube.com/watch?v=wYZFTsb6ha0&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=47&ab_channel=AlgosWithMichael
 * input: head to a linked list & number of rotations to make
 * algo: rotate the linked list to the right by the number of rotations specified
 */
public class RotateLinkedList {
  static class ListNode {
    Integer value;
    ListNode next;

    ListNode(Integer value) {
      this.value = value;
    }

    ListNode(Integer value, ListNode next) {
      this.value = value;
      this.next = next;
    }

    @Override
    public String toString() {
      return "value: " + this.value + " --> " + this.next;
    }
  }

  public static void main(String[] args) {
    /** First example */
    ListNode example1 = buildLinkedList();
    // print list before rotating list
    System.out.println(example1);

    ListNode newHead = computeSolution(example1, 2);
    // print list after rotating list
    System.out.println(newHead);


    /** Second example */
    ListNode example2 = buildLinkedList();
    System.out.println(example2);
    newHead = computeSolution(example2, 12);
    System.out.println(newHead);
  }

  public static ListNode buildLinkedList() {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);
    ListNode n6 = new ListNode(6);
    ListNode n7 = new ListNode(7);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    n5.next = n6;
    n6.next = n7;

    return n1;
  }

  /**
   * time complexity: 2 * O(n) = O(n), with n being the length of the linked list
   * space complexity: O(1) <-- constant time, no new allocation
   */
  public static ListNode computeSolution(ListNode head, int rotations) {
    int length = 1;
    ListNode current = head;
    ListNode newTail = head;

    while (current.next != null) {
      current = current.next;
      length++;
      if (length > rotations + 1) {
        newTail = newTail.next;
      }
    }

    // if rotations < length, we already found the new tail above
    if (rotations >= length) {
      int newTailIndex = length - rotations % length;
      // if rotations is a multiple of the length, no rotations are needed
      if (newTailIndex == length)
        return head;

      int index = 1;
      while (index != newTailIndex) {
        newTail = newTail.next;
        index++;
      }
    }

    current.next = head;
    head = newTail.next;
    newTail.next = null;

    return head;
  }
}
