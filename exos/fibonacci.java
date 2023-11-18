import java.util.HashMap;
import java.util.Map;

public class fibonacci {
  public static void main(String[] args) {
    int res = fibMemoized(7, new HashMap<Integer, Integer>());
    System.out.println(res);
  }

   public static int fibNaive(int target) {
    if (target == 0) return 0;
    if (target == 1) return 1;

    return fibNaive(target - 1) + fibNaive(target - 2);
  }

  public static int fibMemoized(int target, Map<Integer, Integer> memo) {
    if (target == 0) return 0;
    if (target == 1) return 1;
    if (memo.containsKey(target)) return memo.get(target);

    int res = fibMemoized(target - 1, memo) + fibMemoized(target - 2, memo);
    memo.put(target, res);

    return res;
  }

}