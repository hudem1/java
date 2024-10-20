import java.util.LinkedList;
import java.util.Queue;

/**
 * video: https://www.youtube.com/watch?v=HS7t2i9ldmE&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=32&ab_channel=AlgosWithMichael
 * input: a 2D map of 1s and 0s, representing land and water, respectively.
 * output: return the number of islands
 */
public class NumberOfIslands {
  public static void main(String[] args) {
    NumberOfIslands noi = new NumberOfIslands();

    int[][] map = {
      {1, 0, 1},
      {1, 1, 0},
      {0, 0, 1},
    };
    Integer result = noi.computeSolution(map);
    System.out.println("The result is: " + result);
  }

  static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  // can also write this way
  // static int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  /**
   * time complexity: O(n * m), with n being the number of rows and m the number of columns
   *  ^-- in the worst case, that is if map is all land, it's 2 * O(n * m) == O(n * m)
   * space complexity: O(min(n, m))
   *  ^-- in the worst case, the queue will grow to the minimum of the number of rows/columns
   *      We consistently poll from our queue, therefore, it cannot grow to n * m
   */
  public Integer computeSolution(int[][] map) {
    int numberOfRows = map.length;
    int numberOfCols = map[0].length;

    int numberOfIslands = 0;

    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfCols; j++) {
        if (map[i][j] != 1) continue;
        bfs(map, numberOfRows, numberOfCols, i, j);
        ++numberOfIslands;
      }
    }

    return numberOfIslands;
  }

  private void bfs(int[][] map, int numberOfRows, int numberOfCols, int row, int col) {
    Queue<int[]> lands = new LinkedList<>();
    int[] landCoords = {row, col};
    lands.add(landCoords);

    // transform land into water so that we don't take it into account several times
    map[row][col] = 0;

    while (!lands.isEmpty()) {
      int[] land = lands.poll();

      for (int[] dir: directions) {
        int newRow = land[0] + dir[0];
        int newCol = land[1] + dir[1];

        if (newRow >= 0 && newRow < numberOfRows && newCol >= 0 && newCol < numberOfCols && map[newRow][newCol] == 1) {
          // transform land into water so that we don't take it into account several times
          map[newRow][newCol] = 0;

          int[] neighbourLand = {newRow, newCol};
          lands.add(neighbourLand);
        }
      }
    }
  }
}
