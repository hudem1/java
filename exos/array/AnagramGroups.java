import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * link: https://neetcode.io/problems/anagram-groups
 *
 * Input: Given an array of strings strs
 * Output: group all anagrams together into sublists. You may return the output in any order.
 * Info: An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
 *
 * Constraints:
 *  1 <= strs.length <= 1000.
 *  0 <= strs[i].length <= 100
 *  strs[i] is made up of lowercase English letters.
 */
public class AnagramGroups {
  public static void main(String[] args) {
    AnagramGroups ag = new AnagramGroups();
    String[] strs = {"act", "pots", "tops", "cat", "stop", "hat"};
    List<List<String>> result = ag.computeSolution(strs);

    for (List<String> anagGroup: result) {
      for (String str: anagGroup) {
        System.out.print(str + " ");
      }
      System.out.println();
    }
  }

  /**
   * time complexity: O(n * m), with n and m the length of strs and the max length of the strings in strs
   * space complexity: O(n * m), with n the length of strs because we end up storing all strs in the map
   *                   whether as the same key or different ones
   *
   * Alternative:
   *    Thought about sorting each string in strs.
   *    Then it becomes easy to compare strings (anagrams have same length && chars in same order).
   *      ^-- can put the string hashCode() as key in a map <-- because hashCode() takes the order of chars into account
   *    But this solution has a higher time complexity because of the sorting of each str: O(n * mlogm)
   */
  public List<List<String>> computeSolution(String[] strs) {
    // tried with int[] as key but when map calls containsKey(), it calls equals() on arrays
    // which only compare pointers and not array content.
    // Therefore it created a different entry in the map for each string
    Map<String, List<String>> groups = new HashMap<>();

    // O(n)
    for (String str: strs) {
      // as strings can only be 26 lowercase characters
      int[] count = new int[26];

      // O(m) (creating the charArray is also O(m) therefore 2 * O(m) == O(m))
      //  ^-- doing a for loop over string length is more efficient in terms of time & space complexity !
      for (char c: str.toCharArray()) count[c - 'a']++;

      // O(1) because O(26) <-- constant
      String countStr = Arrays.toString(count);
      if (!groups.containsKey(countStr)) groups.put(countStr, new ArrayList<>());
      groups.get(countStr).add(str);
    }

    return new ArrayList<>(groups.values());
  }
}
