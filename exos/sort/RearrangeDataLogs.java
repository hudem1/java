import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

          boolean isLog1DigitLog = Character.isDigit(log1Logs.charAt(0));
          boolean isLog2DigitLog = Character.isDigit(log2Logs.charAt(0));

          // if both logs are strings
          if (!isLog1DigitLog && !isLog2DigitLog) {
            int logsAlphabeticOrder = log1Logs.compareTo(log2Logs);
            // sort by alphabetical order. If same, sort by identifier's alphabetical order
            return logsAlphabeticOrder != 0 ? logsAlphabeticOrder : log1Identifier.compareTo(log2Identifier);
          } else {
            // if first log is a string, the first log comes first (-1)
            // if second log is a string, the second log comes first (1)
            // otherwise, both logs are digit-logs, therefore keep natural order (0)
            return !isLog1DigitLog ? -1 : !isLog2DigitLog ? 1 : 0;
          }
        };
    });
  }
}
