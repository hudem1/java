import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * video: https://www.youtube.com/watch?v=D4T2N0yAr20
 *
 * goal: given an array of strings (products) and a search word, retrieve the 3 first lexicographically minimum matching words
 *
 */
public class SearchSuggestionSystem {
  public static void main(String[] args) {
    SearchSuggestionSystem sss = new SearchSuggestionSystem();

    String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
    List<List<String>> results = sss.suggestedProducts(products, "mouse");

    System.out.println("Results are: ");
    for (List<String> suggestions: results) {
      for (String suggestion: suggestions) {
        System.out.print(suggestion + " ");
      }
      System.out.println();
    }
  }

  /**
   * time complexity: O(nlogn) because of sorting products
   * space complexity: O(n), with n being the length of searchWord
   * ^-- we would have as many List<String> as there are characters in searchWord
   */
  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    // time complexity: O(nlogn)
    Arrays.sort(products);

    int lowerBound = 0;
    int upperBound = products.length - 1;

    List<List<String>> results = new ArrayList<>();

    // time complexity: O(n)
    for (int i = 0; i < searchWord.length(); i++) {
      int[] bounds = findProductsRange(products, lowerBound, upperBound, i, searchWord.charAt(i));
      lowerBound = bounds[0];
      upperBound = bounds[1];

      List<String> currentResults = new ArrayList<>();
      int productIndex = lowerBound;
      // time complexity: O(1) <-- at max, 3 iterations === constant time complexity
      while (productIndex <= upperBound && currentResults.size() < 3) {
        currentResults.add(products[productIndex++]);
      }

      results.add(currentResults);
    }

    return results;
  }

  /**
   * time complexity: O(right-left)
   * space complexity: O(1)
   */
  private int[] findProductsRange(String[] sortedProducts, int left, int right, int searchWordIndex, char searchWordChar) {
    while (left <= right && (searchWordIndex >= sortedProducts[left].length() || sortedProducts[left].charAt(searchWordIndex) < searchWordChar)) {
      ++left;
    }

    while (left <= right && (searchWordIndex >= sortedProducts[right].length() || sortedProducts[right].charAt(searchWordIndex) > searchWordChar)) {
      --right;
    }

    return new int[] {left, right};
  }


  /** OTHER VERSION BUT NOT AS EFFICIENT AS FIRST VERSION BECAUSE LOTS OF "STARTSWITH" */

  public List<List<String>> suggestedProducts_v2(String[] products, String searchWord) {
    Arrays.sort(products);

    int lowerBound = 0;
    int upperBound = products.length - 1;

    List<List<String>> results = new ArrayList<>();

    for (int i = 0; i < searchWord.length(); i++) {
      int[] bounds = findProductsRange_v2(products, lowerBound, upperBound, searchWord.substring(0, i + 1));
      lowerBound = bounds[0];
      upperBound = bounds[1];

      List<String> currentResults = new ArrayList<>();
      int productIndex = lowerBound;
      while (productIndex <= upperBound && currentResults.size() <= 3) {
        currentResults.add(products[productIndex++]);
      }
    }

    return results;
  }

  private int[] findProductsRange_v2(String[] sortedProducts, int left, int right, String searchWord) {
    int mid = (left + right) / 2;

    while (left <= right && !sortedProducts[mid].startsWith(searchWord) || (mid > 0 && sortedProducts[mid - 1].startsWith(searchWord))) {
      if (sortedProducts[mid].compareTo(searchWord) < 0) {
        left = mid + 1;
      } else {
        // mid is a potential answer (because mid could be "mouse" and searchWord "mou")
        right = mid;
      }

      mid = (left + right) / 2;
    }

    // lower bound
    left = mid;

    // cannot do binary search again to find upper bound because lower bound is already > searchWord
    // therefore, all searchProducts[mid] will be > searchWord

    for (int i = left; i < sortedProducts.length - 1; i++) {
      if (!sortedProducts[i + 1].startsWith(searchWord)) {
        right = i;
        break;
      }
    }

    // account for last element we didn't check in our for loop
    if (sortedProducts[sortedProducts.length - 1].startsWith(searchWord))
      right = sortedProducts.length - 1;

    return new int[] {left, right};
  }
}