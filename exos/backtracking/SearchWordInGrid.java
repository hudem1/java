
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
        if (board[i][j] == word.charAt(0)) {
          if (dfs(board, i, j, word, 0)) return true;
        }
      }
    }

    return false;
  }

  private boolean dfs(char[][] board, int row, int col, String word, int charIndex) {
    if (charIndex == word.length() - 1) return true;

    // Could have used a Set, or another [][] of ints with 1 or 0 stating whether the case has already been used
    // but overwriting the existing [][] is more efficient (O(1) space complexity)
    board[row][col] = '#';

    for (int[] dir: directions) {
      int newRow = row + dir[0];
      int newCol = col + dir[1];

      // if newly computed cell is in bounds and cell's char is expected one
      if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && board[newRow][newCol] == word.charAt(charIndex + 1)) {
        if (dfs(board, newRow, newCol, word, charIndex + 1)) return true;
      }
    }

    // if the current cell does not lead to a solution, reset it to its original character
    board[row][col] = word.charAt(charIndex);

    return false;
  }
}
