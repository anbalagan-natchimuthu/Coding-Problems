package interview.Matrix;

/**
 * PROBLEM: 1
 * https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 * Count all possible paths from top left to bottom right of a mXn matrix. The problem is to count all the possible
 * paths from top left to bottom right of a mXn matrix with the constraints that from each cell you can either move
 * only to right or down
 */
public class PossiblePaths {

  // Returns count of possible paths to reach
  // cell at row number m and column number n
  // from the topmost leftmost cell (cell at 1, 1)
  static int numberOfPathsUsingRecursion(int m, int n) {
    // If either given row number is first or
    // given column number is first
    if (m == 1 || n == 1) {
      return 1;
    }

    // If diagonal movements are allowed then
    // the last addition is required.
    return numberOfPathsUsingRecursion(m - 1, n) + numberOfPathsUsingRecursion(m, n - 1);
    // + numberOfPaths(m-1,n-1);
  }

  // Returns count of possible paths to reach cell at row number m and column number n from
  //  the topmost leftmost cell (cell at 1, 1)
  static int numberOfPathsUsingDP(int m, int n) {
    // Create a 2D table to store results
    // of subproblems
    int count[][] = new int[m][n];

    // Count of paths to reach any cell in
    // first column is 1 since only down movement is allowed
    for (int i = 0; i < m; i++) {
      count[i][0] = 1;
    }

    // Count of paths to reach any cell in
    // first row is 1 since only right movement is allowed
    for (int j = 0; j < n; j++) {
      count[0][j] = 1;
    }

    // Calculate count of paths for other
    // cells in bottom-up manner using
    // the recursive solution
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++)

      // By uncommenting the last part the
      // code calculates the total possible paths
      // if the diagonal Movements are allowed
      {
        count[i][j] = count[i - 1][j] + count[i][j - 1]; //+ count[i-1][j-1];
      }
    }
    return count[m - 1][n - 1];
  }

  /**
   * PROBLEM: 2
   * https://leetcode.com/problems/minimum-path-sum/
   *
   * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
   * the sum of all numbers along its path.
   *
   * Note: You can only move either down or right at any point in time.
   *
   * Example:
   *
   * Input:
   * [
   *   [1,3,1],
   *   [1,5,1],
   *   [4,2,1]
   * ]
   * Output: 7
   * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
   */

  public static int minPathSum(int[][] grid) {

    // Fill first row (Can move from left to right direction only)
    for (int j = 1; j < grid[0].length; j++) {
      grid[0][j] = grid[0][j] + grid[0][j - 1];
    }

    // Fill first column (Can move from top to bottom direction only)
    for (int i = 1; i < grid.length; i++) {
      grid[i][0] = grid[i][0] + grid[i - 1][0];
    }

    // Fill remaining values
    for (int i = 1; i < grid.length; i++) {
      for (int j = 1; j < grid[0].length; j++) {
        grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
      }
    }
    return grid[grid.length - 1][grid[0].length - 1];
  }

  // Driver program to test above function
  public static void main(String args[]) {
    System.out.println("numberOfPaths:" + numberOfPathsUsingDP(3, 3));
    System.out.println("numberOfPathsUsingRecursion:" + numberOfPathsUsingRecursion(3, 3));
    System.out.println("minimum path sum::" + minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
  }
}
