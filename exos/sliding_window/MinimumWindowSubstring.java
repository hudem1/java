import java.util.HashMap;
import java.util.Map;

/**
 * video: https://www.youtube.com/watch?v=U1q16AFcjKs&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=38&ab_channel=AlgosWithMichael
 * input: 2 strings, 1 string whose characters to search for and another string where to search the other string's characters
 * ouput: the minimum substring where you can find all of the characters that we searched for (NOT NECESSARILY CONSECUTIVE)
 */
public class MinimumWindowSubstring {
  public static void main(String[] args) {
    String input = "ADOBECODEBANC";
    String charsToSearch = "ABC";

    String result = computeSolution(input, charsToSearch);
    // should display "BANC"
    System.out.println("The result is: " + result);
  }

  /**
   * time complexity: O(2 * n + m), with n and m being the length of the input and charsToSearch strings respectively
   * space complexity: O(m), with m being the length of charsToSearch string
   */
  public static String computeSolution(String input, String charsToSearch) {
    if (input == null || charsToSearch == null || input.isEmpty() || charsToSearch.isEmpty())
      return "";

    Map<Character, Integer> map = new HashMap<>();

    for (char c: charsToSearch.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int numberUniqueChars = map.size();

    // if there is a substring in input, it will be at most input.length() size
    int minLength = input.length();
    int finalLeft = 0;
    int finalRight = minLength;
    // we might go through the entire input without finding a substring solution
    boolean found = false;

    int i = 0;

    for (int j = 0; j < input.length(); j++) {
      char elemAtJ = input.charAt(j);

      if (!map.containsKey(elemAtJ)) continue;

      map.put(elemAtJ, map.get(elemAtJ) - 1);

      if (map.get(elemAtJ) == 0) --numberUniqueChars;

      if (numberUniqueChars == 0) {
        // here we already found a substring but we might find an even smaller one
        // so, let's look for it before setting the minLength and found variables

        while (numberUniqueChars == 0) {
          char elemAtI = input.charAt(i++);

          if (!map.containsKey(elemAtI)) continue;

          map.put(elemAtI, map.get(elemAtI) + 1);

          // if the condition is true, it's because the value at elemAtI has changed from 0 to 1
          if (map.get(elemAtI) > 0) ++numberUniqueChars;
        }

        if (j - i + 2 < minLength) {
          minLength = j - i + 2;
          finalLeft = i - 1;
          finalRight = j;
          found = true;
        }
      }
    }

    return found ? input.substring(finalLeft, finalRight + 1) : "";
  }
}
