import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * video: https://www.youtube.com/watch?v=Nj2Bpw1KKds&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=29
 *
 * goal: Sort an array of logs according to the rules below
 *
 * 2 types of log:
 *  - letter log: "let1 qbc efd efji"
 *  - digit log: "dig3 2 442 12"
 *
 * rules:
 *  1. Letter logs always come before digit logs
 *  2. Letter logs are sorted lexicographically, not including the ID
 *  3. When letter logs are equal, sort by the ID lexicographically
 *  4. Digit logs should maintain their order2
 */
public class RearrangeDataLogs {
  public static void main(String[] args) {
    RearrangeDataLogs rLogs = new RearrangeDataLogs();
    List<String> logs = new ArrayList<>() {{
      add("dig2 8 1 5 1");
      add("let3 art can");
      add("dig1 3 6");
      add("let2 own kit dig");
      add("let1 art can");
    }};

    rLogs.computeSolution(logs);

    System.out.println("The result is: ");
    // the order should be: [let1... let3... let2... dig2... dig1...]
    for (String log: logs) {
      System.out.println(log);
    }
  }

  public void computeSolution(List<String> logs) {
    Collections.sort(logs, new Comparator<>() {
        public int compare(String log1, String log2) {
          int log1FirstSpace = log1.indexOf(' ');
          String log1Identifier = log1.substring(0, log1FirstSpace);
          String log1Logs = log1.substring(log1FirstSpace + 1);

          int log2FirstSpace = log2.indexOf(' ');
          String log2Identifier = log2.substring(0, log2FirstSpace);
          String log2Logs = log2.substring(log2FirstSpace + 1);

          boolean isLog1LetterLog = Character.isLetter(log1Logs.charAt(0));
          boolean isLog2LetterLog = Character.isLetter(log2Logs.charAt(0));

          // if both logs are strings
          if (isLog1LetterLog && isLog2LetterLog) {
            int logsAlphabeticOrder = log1Logs.compareTo(log2Logs);
            // sort by alphabetical order. If same, sort by identifier's alphabetical order
            return logsAlphabeticOrder != 0 ? logsAlphabeticOrder : log1Identifier.compareTo(log2Identifier);
          } else {
            // if first log is a string, the first log comes first (-1)
            // if second log is a string, the second log comes first (1)
            // otherwise, both logs are digit-logs, therefore keep natural order (0)
            return isLog1LetterLog ? -1 : isLog2LetterLog ? 1 : 0;
          }
        };
    });
  }
}
