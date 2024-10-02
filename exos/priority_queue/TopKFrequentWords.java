import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * video: https://www.youtube.com/watch?v=cupg2TGIkyM&ab_channel=AlgosWithMichael
 * input: a list of words
 * ouput: the k most frequent elements
 * info: if 2 elements have equal frequency, return the element coming first alphabetically
 */
public class TopKFrequentWords {

  /**
   * time complexity: O(n * log(k)), with n being the number of input words and k the passed parameter
   *  ^-- due to iterating over map entries and adding into the PriorityQueue of size k
   * space complexity: O(n), with n being the number of input words because of the allocated map
   *
   * Alternative:
   * 1)
   *  - insert all elements in priority queue with final/expected priority (instead of opposite priority)
   *  - and then poll only k elements into a list
   *  ^-- code easier to understand/maintain, but space complexity grows to O(n * log(n)) instead of O(n * log(k)) because of priority queue
   * 2)
   *  - put frequencies into a map (as already done)
   *  - inserts all map entries into an arrayList --> additional operation we didn't really have in other solutions
   *  - sort the arrayList with Collections.sort()
   *  - reduce size of arrayList to only k
   */
  public String[] computeSolution(String[] words, int k) {
    Map<String, Integer> frequencies = new HashMap<>();

    for (String word: words) {
      frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
    }
    Integer int1;
    int1.compareTo(int1)

    // we put high priority elements to the bottom of our heap, so that low priority elements get popped before
    // and only the highest priority elements remain (we inverse priority in priority queue Comparator)
    Queue<String> topKFrequentWords = new PriorityQueue<>(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        // we give max priority to smaller-frequency elements (if o1.freq < o2.freq, return o1)
        int freqComparison = frequencies.get(o1).compareTo(frequencies.get(o2));

        // we give max priority to higher-alphabetical elements (if o2.alpha < o1.alpha, return o1)
        return freqComparison == 0 ? o2.compareTo(o1) : freqComparison;
      }
    });

    for (Map.Entry<String, Integer> wordToFreq: frequencies.entrySet()) {
      topKFrequentWords.add(wordToFreq.getKey());

      // if size is too big (k + 1), remove the max priority element (which is least priority element actually)
      if (topKFrequentWords.size() > k) topKFrequentWords.poll();
    }

    String[] topKElements = new String[k];
    int i = k - 1;
    // can also do: for (String str: topKFrequentWords) topKElements[i--] = str;
    // but the problem with this is that the for loop might traverse the priority queue in whatever order (not from the most to less priority)
    // probably traverse in a BFS (if its as the impl ive done in c++)
    while (!topKFrequentWords.isEmpty()) {
      topKElements[i--] = topKFrequentWords.poll();
    }

    return topKElements;
  }

  public static void main(String[] args) {
    String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
    int k = 2;

    TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
    String[] topKElements = topKFrequentWords.computeSolution(words, k);

    System.out.println("The result is:");
    for (String elem: topKElements) {
      System.out.print(elem + " ");
    }
    System.out.println();
  }
}




// // compile the regex a single time
// Pattern pattern = Pattern.compile(".");
// String test = "";
// // check a string against the compiled regex
// Matcher matcher = pattern.matcher(test);
// matcher.matches();

// // same as above (implicitly does the same)
// // but good for single matching checks
// // but if you repeat matching checks, the regex gets recompiled everytime
// test.matches("");