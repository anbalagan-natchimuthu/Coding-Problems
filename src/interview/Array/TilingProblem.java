package interview.Array;

/**
 * https://www.geeksforgeeks.org/tiling-problem/
 * https://www.geeksforgeeks.org/count-number-ways-tile-floor-size-n-x-m-using-1-x-m-size-tiles/
 *
 * Given a “2 x n” board and tiles of size “2 x 1”, count the number of ways to tile the given board using the 2 x 1
 * tiles. A tile can either be placed horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
 * Examples:
 *
 * Input n = 3
 * Output: 3
 * Explanation:
 * We need 3 tiles to tile the board of size  2 x 3.
 * We can tile the board using following ways
 * 1) Place all 3 tiles vertically.
 * 2) Place first tile vertically and remaining 2 tiles horizontally.
 * 3) Place first 2 tiles horizontally and remaining tiles vertically
 */
public class TilingProblem {

  public static void main(String[] args) {
    int n = 2;
    int m = 3;
    System.out.println("Number of ways = " + countWays(n, m));
  }

  public static int countWays(int n, int m) {

    int[] count = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      if (i < m) {
        count[i] = 1;
      } else if (i == m) {
        count[i] = 2;
      } //i > m
      else {
        count[i] = count[i - 1] + count[i - m];
      }
    }
    return count[n];
  }
}
