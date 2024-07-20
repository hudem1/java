import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * video: https://www.youtube.com/watch?v=BBt9FB5Yt0M&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=35&ab_channel=AlgosWithMichael
 * input: List of lists of numbers
 * output: A single ordered list of all sublists
 */
public class MergeKLists {
  // The video does it with linked lists
  // static class ListNode {
  //   Integer value;
  //   ListNode left;
  //   ListNode right;

  //   ListNode(Integer value) {
  //     this.value = value;
  //   }
  // }

  public static void main(String[] args) {
    List<List<Integer>> input = new ArrayList<>();
    input.add(new ArrayList<>(Arrays.asList(1, 3, 4)));
    input.add(new ArrayList<>(Arrays.asList(5, 6, 7)));
    input.add(new ArrayList<>(Arrays.asList(3, 4, 5)));

    List<Integer> result = computeSolution(input);
    // Result should be: [1, 3, 3, 4, 4, 5, 5, 6, 7]
    System.out.println("The result is: ");
    for (Integer elem: result) {
      System.out.print(elem + " ");
    }
    System.out.println();
  }

  /**
   * time complexity: O(n * log(k))
   *  ^-- with n being the total length of the 2 lists being merged, and k being the number of RECURSIVE stack calls
   * space complexity: O(n + k) <-- just my guess
   *  ^-- with n being the total length of all sublists (because at the end, you will have a newly allocated single array of all ordered elements)
   *  ^-- and k being the number of recursive stack calls
   */
  public static List<Integer> computeSolution(List<List<Integer>> input) {
    if (input == null || input.isEmpty()) return null;

    return mergeKLists(input, 0, input.size() - 1);
  }

  private static List<Integer> mergeKLists(List<List<Integer>> input, int left, int right) {
    if (left == right) return input.get(left);

    int mid = (left + right) / 2;
    // this mid formula below is to avoid overlow from adding left and right (if they were huge numbers)
    // int mid = left + (right - left) / 2;
    List<Integer> leftList = mergeKLists(input, left, mid);
    List<Integer> rightList = mergeKLists(input, mid + 1, right);

    return merge2Lists(leftList, rightList);
  }

  private static List<Integer> merge2Lists(List<Integer> list1, List<Integer> list2) {
    int list1Pointer = 0;
    int list2Pointer = 0;

    List<Integer> result = new ArrayList<>();

    while (list1Pointer < list1.size() || list2Pointer < list2.size()) {
      if (list1Pointer >= list1.size()) {
        result.add(list2.get(list2Pointer));
        ++list2Pointer;
      } else if (list2Pointer >= list2.size()) {
        result.add(list1.get(list1Pointer));
        ++list1Pointer;
      } else {
        if (list1.get(list1Pointer) <= list2.get(list2Pointer)) {
          result.add(list1.get(list1Pointer));
          ++list1Pointer;
        } else {
          result.add(list2.get(list2Pointer));
          ++list2Pointer;
        }
      }
    }

    return result;
  }
}
