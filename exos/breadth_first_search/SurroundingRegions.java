import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * video: https://www.youtube.com/watch?v=EiK4i9yyd7A&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=43&ab_channel=AlgosWithMichael
 * input: 2D array of cells, either 'X' or 'O'
 * goal: to flip all O's into X's if those O's are completely surrounded by X's (in the 4 directions)
 * output: the resulting flipped 2D array
 */
public class SurroundingRegions {
  public static void main(String[] args) {
    char[][] cells = {
      {'X', 'X', 'X', 'X'},
      {'X', 'O', 'O', 'X'},
      {'X', 'X', 'O', 'X'},
      {'X', 'O', 'X', 'X'},
    };

    char[][] result = computeSolution(cells);

    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        System.out.print(result[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static void breadthFirstTraversal(char[][] cells, int row, int col) {
    if (cells[row][col] != 'O') return;

    int numberOfRows = cells.length;
    int numberOfCols = cells[0].length;

    int[] start = {row, col};
    Queue<int[]> oCells = new LinkedList<>(Arrays.asList(start));

    int[][] directions = {
      {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    while (!oCells.isEmpty()) {
      int[] currentOCell = oCells.poll();
      row = currentOCell[0];
      col = currentOCell[1];

      cells[row][col] = 'M';

      for (int[] dir: directions) {
        int newRow = dir[0] + row;
        int newCol = dir[1] + col;

        if (newRow >= 0 && newRow < numberOfRows && newCol >= 0 && newCol < numberOfCols && cells[newRow][newCol] == '0') {
          int[] neighbour = {newRow, newCol};
          oCells.add(neighbour);
        }
      }
    }
  }

  private static void depthFirstTraversalUsingStack(char[][] cells, int row, int col) {
    if (cells[row][col] != 'O') return;

    int numberOfRows = cells.length;
    int numberOfCols = cells[0].length;

    int[] start = {row, col};
    Stack<int[]> oCells = new Stack<>();
    oCells.push(start);

    int[][] directions = {
      {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    while (!oCells.isEmpty()) {
      int[] currentOCell = oCells.pop();
      row = currentOCell[0];
      col = currentOCell[1];

      cells[row][col] = 'M';

      for (int[] dir: directions) {
        int newRow = dir[0] + row;
        int newCol = dir[1] + col;

        if (newRow >= 0 && newRow < numberOfRows && newCol >= 0 && newCol < numberOfCols && cells[newRow][newCol] == '0') {
          int[] neighbour = {newRow, newCol};
          oCells.push(neighbour);
        }
      }
    }
  }

  private static void depthFirstTraversalUsingRecursion(char[][] cells, int row, int col) {
    // if only useful for the 1st call (coming from computeSolution function)
    if (cells[row][col] != 'O') return;
    // else is useful for every call
    else cells[row][col] = 'M';

    int numberOfRows = cells.length;
    int numberOfCols = cells[0].length;

    // could create it as an attribute to the class
    // that would avoid re-creating it at every call
    int[][] directions = {
      {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    for (int[] dir: directions) {
      int newRow = dir[0] + row;
      int newCol = dir[1] + col;

      if (newRow >= 0 && newRow < numberOfRows && newCol >= 0 && newCol < numberOfCols && cells[newRow][newCol] == '0') {
        depthFirstTraversalUsingRecursion(cells, newRow, newCol);
      }
    }
  }

  /**
   * time complexity: O(m) + O(n * m) + O(n) + O(n * m) = O(n * m) + O(n + m)
   * space complexity:
   *  - BFS: O(min(n, m))
   *     ^-- in the worst case, all cells are Os, and because even though we add to the queue, we also remove from it
   *     ^-- but not sure to understand why min(n, m)
   *  - DFS:
   *    1) if use stack: O(min(n , m)
   *      ^-- same reason as for BFS (we gradually remove from the stack)
   *    2) if use recursion: O(n * m)
   *      ^-- in the worst case, that is if all cells are O's, call stack grows to n * m
   */
  public static char[][] computeSolution(char[][] cells) {
    int numberOfRows = cells.length;
    int numberOfCols = cells[0].length;

    for (int i = 0; i < numberOfCols; i++) {
      breadthFirstTraversal(cells, 0, i);
      breadthFirstTraversal(cells, numberOfRows - 1, i);

      // depthFirstTraversalUsingStack(cells, 0, i);
      // depthFirstTraversalUsingStack(cells, numberOfRows - 1, i);

      // depthFirstTraversalUsingRecursion(cells, 0, i);
      // depthFirstTraversalUsingRecursion(cells, numberOfRows - 1, i);
    }

    for (int i = 0; i < numberOfRows; i++) {
      breadthFirstTraversal(cells, i, 0);
      breadthFirstTraversal(cells, i, numberOfCols - 1);

      // depthFirstTraversalUsingStack(cells, i, 0);
      // depthFirstTraversalUsingStack(cells, i, numberOfCols - 1);

      // depthFirstTraversalUsingRecursion(cells, i, 0);
      // depthFirstTraversalUsingRecursion(cells, i, numberOfCols - 1);
    }

    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfCols; j++) {
        if (cells[i][j] == 'M') cells[i][j] = 'O';
        else if (cells[i][j] == 'O') cells[i][j] = 'X';
      }
    }

    return cells;
  }
}
