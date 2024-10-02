import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * goal: Find k closest points to origin
 */
public class KClosestPointsToOrigin {
  public static void main(String[] args) {
    List<int[]> points = new ArrayList<>();
    points.add(new int[] {2, 3});
    points.add(new int[] {2, 1});
    points.add(new int[] {4, 4});
    points.add(new int[] {8, 6});
    points.add(new int[] {0, 1});
    points.add(new int[] {3, 4});

    KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();
    int[][] res = kClosestPointsToOrigin.computeSolution(points, 3);

    for (int[] closestPoint: res) {
      System.out.println("x: " + closestPoint[0] + " y: " + closestPoint[1]);
    }
  }

  /**
   * time complexity: O(nlogk), n being the number of points and k the parameter
   * ^-- logk because of adding at most k points to priority queue
   * space complexity: O(2 * k) == O(k)
   */
  public int[][] computeSolution(List<int[]> points, int k) {
    if (k > points.size()) return new int[][] {};

    int[][] results = new int[k][];

    PriorityQueue<int[]> kClosestPoints = new PriorityQueue<>(new Comparator<>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        double result = distanceToOrigin(o1) - distanceToOrigin(o2);
        // put priority to point farther from origin so that we have only k elements in priority queue
        // ^-- we don't need to have at most k elements in priority queue but little opti
        //     compared to if we just inserted all elements in it with correct priority, and then removed the k top elements
        return result < 0 ? 1 : result > 0 ? -1 : 0;
      }

      private double distanceToOrigin(int[] point) {
        return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
      }
    });

    for(int[] point: points) {
      kClosestPoints.add(point);
      // first add one more and then poll
      if (kClosestPoints.size() == k + 1) kClosestPoints.poll();
    }

    // just to have the closest points in correct order
    int i = k - 1;
    while (!kClosestPoints.isEmpty()) {
      results[i--] = kClosestPoints.poll();
    }

    return results;
  }

  /**
   * time complexity: O(nlogn)
   * space complexity: O(k)
   */
  public int[][] computeSolution_v2(List<int[]> points, int k) {
    Collections.sort(points, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        double result = distanceToOrigin(o1) - distanceToOrigin(o2);
        // result has already the correct sign but needs to convert double to int
        return result < 0 ? -1 : result > 0 ? 1 : 0;
      }
    });

    int[][] results = new int[k][];
    for (int i = 0; i < k; i++) {
      results[i] = points.get(i);
    }

    return results;
  }

  private double distanceToOrigin(int[] point) {
    return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
  }
}
