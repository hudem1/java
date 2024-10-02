
/**
 * link: https://neetcode.io/problems/products-of-array-discluding-self
 *
 * Input: Given an integer array nums
 * Output: return an array output where output[i] is the product of all the elements of nums except nums[i]
 * Info: Each product is guaranteed to fit in a 32-bit integer.
 * Requirement: Could you solve it in O(n) time without using the division operation?
 */
public class ProductOfArrayDiscludingSelf {
  public static void main(String[] args) {
    ProductOfArrayDiscludingSelf cls = new ProductOfArrayDiscludingSelf();
    int[] nums = {1, 2, 4, 6};
    int[] result = cls.computeSolution(nums);
    // Should print: 48 24 12 8
    for (int res: result) {
      System.out.print(res + " ");
    }
    System.out.println();
  }

  /**
   * time complexity: 0(n) <-- 3 * O(n)
   * space complexity: 0(n) <-- 3 * O(n)
   *
   * Alternative algo:
   *  - I thought about computing the product of the whole array
   *  - Then going through each value, compute the whole product - quotient (that we calculate from substracting the whole product by the current value)
   *    ^-- but calcuating the quotient by substraction adds to the time complexity (maybe O(n^2))
   *        because in the worst case, we might have an array of a thousand 1s
   *        for each value, we have to compute 1000 iterations <-- 1000 - 1 = 999, then 999 - 1 = 998, etc. until 0 <-- quotient = 1000
   *        and repeat doing this for each 1 in the array (not efficient!)
   *
   * I thought about bit shifting but not really applicable here (because division is not just by 2)
   */
  public int[] computeSolution(int[] nums) {
    int[] prefix = new int[nums.length];
    int[] postfix = new int[nums.length];
    int[] result = new int[nums.length];

    prefix[0] = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      prefix[i] = nums[i] * prefix[i - 1];
    }

    postfix[nums.length - 1] = nums[nums.length - 1];
    for (int i = nums.length - 2; i >= 0; --i) {
      postfix[i] = nums[i] * postfix[i + 1];
    }

    for (int i = 0; i < nums.length; ++i) {
      int currentPrefix = i == 0 ? 1 : prefix[i - 1];
      int currentPostfix = i == nums.length - 1 ? 1 : postfix[i + 1];
      result[i] = currentPrefix * currentPostfix;
    }

    return result;
  }
}
