import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * video: https://www.youtube.com/watch?v=vsAeM9EGhFo&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=44&ab_channel=AlgosWithMichael
 * input: 2D Integer matrix, representing (fresh oranges | rotten oranges | no oranges)
 * ouput: minimum number of minutes before all fresh oranges become rotten (if impossible, return -1)
 * nomenclature: 2 means rotten orange, 1 means fresh orange and 0 means no orange (empty cell)
 */
public class RottingOranges {
  public static void main(String[] args) {
    int[][] cells = {
      {2, 1, 1},
      {1, 1, 0},
      {0, 1, 1},
    };

    Integer result = computeSolution(cells);
    // Should print 4
    System.out.println("The result is: " + result);



    int[][] cells2 = {
      {2, 2, 2},
      {2, 1, 0},
      {0, 2, 2},
    };

    result = computeSolution(cells2);
    // Should print 4
    System.out.println("The result is: " + result);
  }

  /**
   * time complexity: O(n), with n being the number of cells
   * ^-- actually 2 * O(n) == 0(n), because in the worst case, all cells are 1 except one rotten cell
   *    and therefore, we need to go through all cells again in the while loop
   * space complexity: O(n), with n being the number of cells
   * ^-- in the worst case, all cells are rotten tomatoes, and therefore our stack is filled with all cells
   */
  public static Integer computeSolution(int[][] cells) {
    int numberOfRows = cells.length;
    int numberOfCols = cells[0].length;

    int numberFreshTomatoes = 0;
    Queue<Integer> rottenTomatoes = new LinkedList<>();

    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfCols; j++) {
        if (cells[i][j] == 1) numberFreshTomatoes++;
        else if (cells[i][j] == 2) rottenTomatoes.add(i * numberOfCols + j);
      }
    }

    int numberOfMinutes = 0;

    // no fresh tomato cell
    if (numberFreshTomatoes == 0)
      return numberOfMinutes;

    while (!rottenTomatoes.isEmpty()) {
      int rottenTomatoesToRemove = rottenTomatoes.size();
      numberOfMinutes++;

      for (int i = 0; i < rottenTomatoesToRemove; i++) {
        Integer currentRottenTomato = rottenTomatoes.poll();
        int row = currentRottenTomato / numberOfCols;
        int col = currentRottenTomato % numberOfCols;

        for (int[] validNeighbour: neighbourFreshTomatoes(cells, row, col)) {
          int neighbourRow = validNeighbour[0];
          int neighbourCol = validNeighbour[1];
          cells[neighbourRow][neighbourCol] = 2; // fresh tomato becomes rotten

          if (--numberFreshTomatoes == 0)
            return numberOfMinutes; // no need to go through anymore of the cells

          rottenTomatoes.add(neighbourRow * numberOfCols + neighbourCol);
        }
      }
    }

    // if we reach here, it means there are no more rotten tomatoes
    // and there still are fresh tomatoes
    return -1;
  }

  private static ArrayList<int[]> neighbourFreshTomatoes(int[][] cells, int row, int col) {
    int numberOfRows = cells.length;
    int numberOfCols = cells[0].length;

    ArrayList<int[]> validNeighbours = new ArrayList<>();

    int[][] directions = {
      {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    for (int[] dir: directions) {
      int newRow = dir[0] + row;
      int newCol = dir[1] + col;
      // making sure the neighbour is in bounds and is a fresh tomato
      if (newRow >= 0 && newRow < numberOfRows && newCol >= 0 && newCol < numberOfCols && cells[newRow][newCol] == 1) {
        int[] neighbour = {newRow, newCol};
        validNeighbours.add(neighbour);
      }
    }

    return validNeighbours;
  }
}
