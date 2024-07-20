import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.youtube.com/watch?v=h8X2IYNvVnE&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=53&ab_channel=AlgosWithMichael
 * Find longest VALID substring in string
 * a valid substring is a substring, whose own substrings are valid
 * meaning that all inner substrings are not present in forbidden list
 */
public class longest_valid_substring {
  public static void main(String[] args) {
    String input = "leetcode";
    // String[] forbidden = {"de", "le", "e"};
    ArrayList<String> forbidden = new ArrayList<>(Arrays.asList("de", "le", "e"));
    int res = lengthLongestSubstring(input, forbidden);

    System.out.println("Longest substring is of size: " + res);
  }

  /**
   * Time complexity:
   *  O(n * m^2), with n being the length of input and m the length of longest word in forbidden
   *    technically, here m = 10, therefore, 0(n * 100) = 100 * 0(n) = 0(n)
   * Space complexity:
   *  O(n), because of the hashset
   */
  public static int lengthLongestSubstring(String input, ArrayList<String> forbidden) {
    // creating a set for checking if substring is included in forbidden
    Set<String> forbiddenSet = new HashSet<>(forbidden);

    int right = input.length() - 1;
    int left = right;

    String maxSubstring = new String();

    while (left >= 0) {
      // We could just do: <= right but if the input word is very long, we could get a time limit exception.
      // The left + 9 comes from the fact that in the exercise instructions, the longest word in forbidden is not more than 10 letters
      // therefore, there is no need to check if substrings bigger than 10 letters exist in forbidden list
      for (int i = left; i <= Math.min(right, left + 9); i++) {
        String subString = input.substring(left, i + 1);
        if (forbiddenSet.contains(subString)) {
          right = i - 1; // before the invalid substring
          // should not reset left pointer (with left = right):
          // 1) if first letter of current substring is invalid, it could cause a bug:
          //     --> with left getting one letter before right, and right's letter alone not being checked
          // 2) in other cases where it's a subsequent substring (more than 1 letter) that is invalid,
          //    its actually even more optimal to NOT do that:
          //     --> if invalid substring is very long and as we reset the left pointer to the right pointer,
          //         we would have to compute again the validity of shorter substrings
          //         that we've already checked before we bumped on the long invalid substring
          // left = right;
          break;
        }
      }

      String currentSubstring = input.substring(left, right + 1);
      // remark: if condition is true, the length can only increase by 1 at a time
      // as we move the left pointer by 1 at each iteration
      if (currentSubstring.length() > maxSubstring.length())
        maxSubstring = currentSubstring;

      left--;
    }

    // should print "tcod"
    System.out.println("Longest substring is: " + maxSubstring);
    return maxSubstring.length();
  }
}
