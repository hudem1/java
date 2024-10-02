
/**
 * link: https://neetcode.io/problems/maximum-product-subarray
 *
 * 
 */
public class MaximumProductSubarray {
  public static void main(String[] args) {
    MaximumProductSubarray mps = new MaximumProductSubarray();
    // int[] nums = {1, 2, -3, 4};
    int[] nums = {1, 0, 4};
    int result = mps.computeSolution(nums);
    System.out.println("The result is: " + result);
  }

  public int computeSolution(int[] nums) {
    int currentMax = 1;
    int currentMin = 1;

    int res =  Integer.MIN_VALUE;

    // even if num == 0, it still works
    for (int num: nums) {
      int tmp = currentMax * num;
      currentMax =  Math.max(Math.max(tmp, currentMin * num), num);

      currentMin = Math.min(Math.min(tmp, currentMin * num), num);

      res = Math.max(res, currentMax);
    }

    return res;
  }

}



// class Solution {
//   public int maxProduct(int[] nums) {
//       int maxProduct = Integer.MIN_VALUE;

//       List<Integer> previousPossibilities = new ArrayList<>();

//       for (int num: nums) {
//           if (num == 0) {
//               if (num > maxProduct) maxProduct = num;
//               previousPossibilities.clear();
//           } else if (previousPossibilities.isEmpty()) {
//               previousPossibilities.add(num);
//               if (num > maxProduct) maxProduct = num;
//           } else if (num > 0) {
//               if (previousPossibilities.size() == 1) {
//                   previousPossibilities.set(0, previousPossibilities.get(0) * num);
//                   if (previousPossibilities.get(0) < 0)
//                       previousPossibilities.add(num);
//               } else {
//                   previousPossibilities.set(0, previousPossibilities.get(0) * num);
//                   previousPossibilities.set(1, previousPossibilities.get(1) * num);
//               }
//           } else {
//               if (previousPossibilities.size() == 1) {
//                   previousPossibilities.set(0, previousPossibilities.get(0) * num);
//               } else {
//                   Integer minValue = Math.min(previousPossibilities.get(0), previousPossibilities.get(1));
//                   previousPossibilities.clear();
//                   // the minValue becomes the max between the 2
//                   previousPossibilities.add(minValue * num);
//               }
//           }
//           for (Integer poss: previousPossibilities) {
//               if (poss > maxProduct) maxProduct = poss;
//           }
//       }

//       return maxProduct;
//   }
// }
