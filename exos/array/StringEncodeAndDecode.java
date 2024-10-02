import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * link: https://neetcode.io/problems/string-encode-and-decode
 *
 * Goal: Design an algorithm to encode a list of strings to a single string.
 *       The encoded string is then decoded back to the original list of strings.
 *
 * Constraints:
 *  0 <= strs.length < 100
 *  0 <= strs[i].length < 200
 *  strs[i] contains only UTF-8 characters.
 */
public class StringEncodeAndDecode {
  public static void main(String[] args) {
    StringEncodeAndDecode sead = new StringEncodeAndDecode();

    List<String> strs = new ArrayList<>(
      Arrays.asList("3neet","a2code","7alove","you")
    );

    String encodedString = sead.encode(strs);
    System.out.println("Encoded string: " + encodedString);

    List<String> decodedStrings = sead.decode(encodedString);
    for (String str: decodedStrings) {
      System.out.print(str + " ");
    }
    System.out.println();
  }

  /**
   * time complexity: O(n), with n being the total length of all characters in strs
   *  ^-- because for each string, you append (therefore create by iterating over the string)
   * space complexity: O(n), same as for time complexity
   */
  public String encode(List<String> strs) {
    StringBuilder sb = new StringBuilder();

    for (String str: strs) {
      // append the length of the string + a delimeter char
      // because the string could start with a number
      // as the string can contain any char
      sb.append(str.length()).append("a").append(str);
    }

    return sb.toString();
  }

  /**
   * time complexity: O(n), with n being the size of the encoded string
   * space complexity: O(n), same as time complexity
   */
  public List<String> decode(String str) {
    List<String> result = new ArrayList<>();

    int i = 0;
    while (i < str.length()) {
      int currentStrLen = 0;
      while (str.charAt(i) != 'a') {
        currentStrLen = currentStrLen * 10 + str.charAt(i) - '0';
        ++i;
      }
      // here i is at the delimiter character (in our case 'a')
      // hence the + 1 below
      result.add(str.substring(i + 1, i + 1 + currentStrLen));
      i += 1 + currentStrLen;
    }

    return result;
  }
}
