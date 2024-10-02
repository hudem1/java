import java.util.HashSet;
import java.util.Set;

/**
 * link: https://neetcode.io/problems/search-for-word
 *
 * Input: Given a 2-D grid of characters board and a string word
 * Ouput: return true if the word is present in the grid, otherwise return false
 *
 * Info: For the word to be present it must be possible
 * to form it with a path in the board with horizontally or vertically neighboring cells.
 * The same cell may not be used more than once in a word.
 */
public class SearchWordInGrid {
  public static void main(String[] args) {
    SearchWordInGrid swig = new SearchWordInGrid();
    char[][] board = {
      {'A', 'B', 'C', 'D'},
      {'S', 'A', 'A', 'T'},
      {'A', 'C', 'A', 'E'}
    };
    String word = "CAT";
    boolean result = swig.computeSolution(board, word);

    System.out.println("The result is: " + result);
  }

  static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public boolean computeSolution(char[][] board, String word) {
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        if (dfs(board, i, j, word, 0))
          return true;
      }
    }

    return false;
  }

  private boolean dfs(char[][] board, int row, int col, String word, int charIndex) {
    // here, charIndex will never be out of bounds
    // because if its the correct character return true on the next if
    // otherwise, then return false and go back to charIndex - 1
    if (board[row][col] != word.charAt(charIndex))
      return false;

    if (charIndex == word.length() - 1)
      return true;

    // At first, I used a set to keep track of used cells, but it adds space complexity
    // So, might as well better use existing memory --> change used cell to other character
    board[row][col] = '#';
    // usedCells.add(flattenedCell);

    for (int[] dir: directions) {
      int newRow = row + dir[0];
      int newCol = col + dir[1];

      // if new computed cell is in bounds
      if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
        if (dfs(board, newRow, newCol, word, charIndex + 1))
          return true;
      }
    }

    // if the current cell does not lead to a solution, reset it to its original character
    board[row][col] = word.charAt(charIndex);
    // usedCells.remove(flattenedCell);

    return false;
  }
}
