import java.util.Stack;

/**
 * video: https://www.youtube.com/watch?v=SF2W6VDs7bc&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=42&ab_channel=AlgosWithMichael
 * input: a string to decode
 * output: the decoded string
 * info: the string only contains letters, digits, '[' and ']'
 */
public class DecodeString {
  public static void main(String[] args) {
    String input = "2[a2[bc2[d]]]";
    String decodedString = computeSolution(input);
    // The result should be: abcddbcddabcddbcdd
    System.out.println("The result is: " + decodedString);
  }

  /**
   * time complexity: O(n * m)
   *   ^-- with n being the length of the input string and m being the current number of times we need to repeat a substring
   * space complexity: O(n + m)
   *   ^-- the video says: with n and m being the number of letters and the number of digits in the input string, respectively
   *   ^-- i would have said: with n and m being the same as for the time complexity <-- but actually, not so sure because the word sb gets deleted for every '[' encountered
   */
  public static String computeSolution(String input) {
    Stack<String> words = new Stack<>();
    Stack<Integer> numbers = new Stack<>();

    int number = 0;
    StringBuilder word = new StringBuilder();

    for (char elem: input.toCharArray()) {
      if (elem == '[') {
        words.push(word.toString());
        // more efficient to set length to 0 and reuse existing buffer than creating a new buffer
        // because a new object with a new buffer needs to be allocated, the old one becomes elligible for GC, etc.
        word.setLength(0);

        numbers.push(number);
        number = 0;
      } else if (elem == ']') {
        String topWord = words.pop();
        Integer topNumber = numbers.pop();

        StringBuilder repeatedString = new StringBuilder(topWord);
        for (int i = 0; i < topNumber; ++i) {
          repeatedString.append(word); // can append another sb to a sb
        }
        // set word sb to the newly computed one
        word = repeatedString;

      } else if (Character.isDigit(elem)) {
        // as there are consecutive numbers, we shift the current number to the left and add the new number
        number = 10 * number + Character.getNumericValue(elem);
        // Also works because we compare with '0' (fyi, '0' == 48)
        // number = 10 * number + elem - '0';
      } else {
        word.append(elem);
      }
    }

    return word.toString();
  }

  /**
   * Works but not the most optimal solution because I need to reverse the popped elements (from the stack)
   * in the second step with the `toReverse`
   */
  public static String computeSolution_v2(String input) {
    Stack<String> toProcess = new Stack<>();

    for (char elem: input.toCharArray()) {
      if (elem == ']') {
        /** First, get the elements inside [ ] */
        Stack<String> toReverse = new Stack<>();

        String topElem = toProcess.pop();
        while (!topElem.equals("[")) {
          toReverse.push(topElem);
          topElem = toProcess.pop();
        }

        /** Second, reverse the elements that were inside [ ] */
        StringBuilder stringToRepeat = new StringBuilder();
        while (!toReverse.isEmpty()) {
          stringToRepeat.append(toReverse.pop());
        }

        /** Third, get the number of times the string inside [ ] needs to be repeated */
        int repeatTimes = 0;
        int integerIndex = 0;
        while (!toProcess.isEmpty() && isSingleDigit(toProcess.peek())) {
          Integer topDigit = Integer.parseInt(toProcess.pop());
          repeatTimes += topDigit * Math.pow(10, integerIndex++);
        }

        /** Fourth, repeat as many times as required the string inside [ ] */
        StringBuilder repeatedString = new StringBuilder();
        for (int i = 0; i < repeatTimes; i++) {
          repeatedString.append(stringToRepeat.toString());
        }

        /** Fifth, push onto the stack the repeated string */
        toProcess.push(repeatedString.toString());
      } else {
        toProcess.push(String.valueOf(elem));
      }
    }

    return toProcess.pop();
  }

  // check if string is exactly one single digit
  private static boolean isSingleDigit(String input) {
    return input.matches("^\\d+$");
  }
}
