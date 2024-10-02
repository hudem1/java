import java.util.HashMap;
import java.util.Map;

/**
 * video: https://www.youtube.com/watch?v=uLjO2LUlLN4&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=35&ab_channel=AlgosWithMichael
 * input: 2D integers matrix
 * output: the length of the longest path of values increasing
 */
public class LongestIncreasingPath {
  static int[][] directions = {
    {0, 1}, {1, 0}, {0, -1}, {-1, 0}
  };

  public static void main(String[] args) {
    int[][] matrix = {
      {3, 4, 5},
      {3, 2, 6},
    };

    Integer result = computeSolution(matrix);
    // should be 4: 3->4->5->6 or 2->4->5->6
    System.out.println("The result is: " + result);
  }

  /**
   * time complexity: O(n * m), with n and m being the number of rows and columns respectively
   *  ^-- in the worst case, lets say we have numbers that keep increasing, 2 * O(n * m)
   * space complexity: O(n, m), with n and m being the number of rows and columns respectively for the cache
   */
  public static Integer computeSolution(int[][] matrix) {
    if (matrix == null || matrix.length == 0) return 0;

    int numberOfRows = matrix.length;
    int numberOfCols = matrix[0].length;

    int maxPathLength = 0;

    // init all cells to default int value --> 0
    int[][] cache = new int[numberOfRows][numberOfCols];

    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfCols; j++) {
        int pathLength = dfs(matrix, numberOfRows, numberOfCols, cache, i, j);
        if (pathLength > maxPathLength) maxPathLength = pathLength;
      }
    }

    return maxPathLength;
  }

  private static Integer dfs(int[][] matrix, int numberOfRows, int numberOfCols, int[][] cache, int row, int col) {
    // memoization (2/2), remark: every cell should at least be 1 (by the end of the algo)
    if (cache[row][col] > 0) return cache[row][col];

    int maxCurrentPath = 0;

    for (int[] dir: directions) {
      int newRow = row + dir[0];
      int newCol = col + dir[1];

      if (newRow >= 0 && newRow < numberOfRows && newCol >= 0 && newCol < numberOfCols && matrix[newRow][newCol] > matrix[row][col]) {
        int maxPath = dfs(matrix, numberOfRows, numberOfCols, cache, newRow, newCol);
        if (maxPath > maxCurrentPath) maxCurrentPath = maxPath;
      }
    }

    // memoization (1/2)
    cache[row][col] = maxCurrentPath + 1;

    return cache[row][col];
  }
}
