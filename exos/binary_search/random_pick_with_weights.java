import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * https://www.youtube.com/watch?v=3xCqA-IJ7gU&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=51&ab_channel=AlgosWithMichael
 * Objective: return a random index based on weights distribution
 * For example:
 *   input: [1, 3, 2, 4]
 *   --> compute probabilities: [0.1, 0.4, 0.6, 1]
 *   --> from a random generated number, return the index based on the weights
 */
public class random_pick_with_weights {
  public static void main(String[] args) {
    List<Integer> weights = new ArrayList<>(Arrays.asList(1, 3, 2, 4));

    int index = randomPick(weights);
    System.out.println("The resultant index is: " + index);
  }

  public static int randomPick(List<Integer> weights) {
    Integer totalWeight = 0;
    for (Integer i: weights) totalWeight += i;

    List<Float> probabilities = new ArrayList<>();
    float cumulativeVal = 0;

    for (Integer i: weights) {
      cumulativeVal += i.floatValue() / totalWeight;
      probabilities.add(cumulativeVal);
    }
    System.out.println("Probabilities array is: " + probabilities);

    RandomGenerator gen = new Random();
    float randomTarget = gen.nextFloat(1);
    System.out.println("The random target probability is: " + randomTarget);

    int left = 0;
    int right = probabilities.size() - 1;

    // can now use binary search because probabilities array is sorted (cumulative array)
    // we exit the while loop when left == right only, which means we found the index
    // corresponding to the target probability
    while (left < right) {
      int mid = (left + right) / 2;

      if (probabilities.get(mid) < randomTarget) {
        left = mid + 1;
      } else {
        // not - 1 because we want to include mid in our solution
        right = mid;
      }
    }

    return left;
  }
}
