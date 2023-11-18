import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class find_pairs_to_target_in_array {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 5, 2, 3));
    int[] pair = findPairToTarget(list, 16);
    if (pair != null) System.out.println(pair[0] + " " + pair[1]);
    else System.out.println("no pair found");
  }

   public static int[] findPairToTarget(ArrayList<Integer> list, Integer target) {
    int[] res = new int[2];
    Set<Integer> set = new HashSet<Integer>();

    for (Integer elem : list) {
      if (set.contains(target - elem)) {
        res[0] = target - elem;
        res[1] = elem;
        return res;
      }
      set.add(elem);
    }

    return null;
  }
}