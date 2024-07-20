import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * video: https://www.youtube.com/watch?v=cupg2TGIkyM&ab_channel=AlgosWithMichael
 */
public class TopKFrequentWords {
  public static void main(String[] args) {
    // compile the regex a single time
    Pattern pattern = Pattern.compile(".");
    String test = "";
    // check a string against the compiled regex
    Matcher matcher = pattern.matcher(test);
    matcher.matches();

    // same as above (implicitly does the same)
    // but good for single matching checks
    // but if you repeat matching checks, the regex gets recompiled everytime
    test.matches("");
  }
}
