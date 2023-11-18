import java.util.ArrayList;
import java.util.Arrays;

public class merge_sorted_arrays {
  public static void main(String[] args) {
    ArrayList<Integer> nums1 = new ArrayList<>(Arrays.asList(1, 2, 3, 0, 0, 0));
    ArrayList<Integer> nums2 = new ArrayList<>(Arrays.asList(2, 5, 6));
    mergeSortedArrays(nums1, 3, nums2, 3);
    // expected result: 1, 2, 2, 3, 5, 6
    System.out.println(nums1);
  }

  public static void mergeSortedArrays(ArrayList<Integer> nums1, int m, ArrayList<Integer> nums2, int n) {
    ArrayList<Integer> nums1Copy = (ArrayList<Integer>) nums1.clone();

    int nums1_ptr = 0;
    int nums2_ptr = 0;

    for (int i = 0; i < m + n; i++) {
      if ((nums1_ptr < m && nums2_ptr < n && nums1Copy.get(nums1_ptr) < nums2.get(nums2_ptr)) || nums2_ptr >= n) {
        nums1.set(i, nums1Copy.get(nums1_ptr++));
      } else {
        nums1.set(i, nums2.get(nums2_ptr++));
      }
    }
  }
}