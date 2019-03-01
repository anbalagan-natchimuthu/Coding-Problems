package interview.misc;

public class Sudoku {

  private static boolean solveSudoku(int[][] grid) {

    boolean isEmptyAvailable = false;
    int row = -1;
    int col = -1;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 0) {
          row = i;
          col = j;
          isEmptyAvailable = true;
          break;
        }
      }
      if (isEmptyAvailable) {
        break;
      }
    }

    if (!isEmptyAvailable) {
      return true;
    }

    for (int num = 1; num <= grid.length; num++) {
      if (isSafe(grid, row, col, num)) {
        grid[row][col] = num;

        if (solveSudoku(grid)) {
          return true;
        } else {
          grid[row][col] = 0;
        }
      }
    }

    return false;
  }

  static boolean isSafe(int[][] grid, int row, int col, int num) {
    for (int i = 0; i < grid.length; i++) {
      if (grid[row][i] == num) {
        return false;
      }
    }

    for (int j = 0; j < grid[0].length; j++) {
      if (grid[j][col] == num) {
        return false;
      }
    }

    // corresponding square has
    // unique number (box-clash)
    int sqrt = (int) Math.sqrt(grid.length);
    int boxRowStart = row - row % sqrt;
    int boxColStart = col - col % sqrt;

    for (int r = boxRowStart;
        r < boxRowStart + sqrt; r++)
    {
      for (int d = boxColStart;
          d < boxColStart + sqrt; d++)
      {
        if (grid[r][d] == num)
        {
          return false;
        }
      }
    }


    return true;
  }

  public static void main(String[] args) {
    int[][] board = new int[][]{{3, 0, 6, 5, 0, 8, 4, 0, 0}, {5, 2, 0, 0, 0, 0, 0, 0, 0}, {0, 8, 7, 0, 0, 0, 0, 3, 1},
        {0, 0, 3, 0, 1, 0, 0, 8, 0}, {9, 0, 0, 8, 6, 3, 0, 0, 5}, {0, 5, 0, 0, 9, 0, 6, 0, 0},
        {1, 3, 0, 0, 0, 0, 2, 5, 0}, {0, 0, 0, 0, 0, 0, 0, 7, 4}, {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    int N = board.length;

    if (solveSudoku(board)) {
      print(board, N); // print solution
    } else {
      System.out.println("No solution");
    }
  }

  public static void print(int[][] board, int N) {
    // we got the answer, just print it
    for (int r = 0; r < N; r++) {
      for (int d = 0; d < N; d++) {
        System.out.print(board[r][d]);
        System.out.print(" ");
      }
      System.out.print("\n");

      if ((r + 1) % (int) Math.sqrt(N) == 0) {
        System.out.print("");
      }
    }
  }
}
