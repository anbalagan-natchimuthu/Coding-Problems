package interview.DP;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        System.out.println("All possible unique Paths: " + up.uniquePaths(5, 5));

        int[][] obstacleGrid = new int[][] {
            { 0, 0, 0, 1 },
            { 0, 0, 1, 0 },
            { 0, 0, 0, 1 },
            { 0, 1, 0, 0 }
        };
        int[][] visited = new int[obstacleGrid.length][obstacleGrid[0].length];

        System.out
            .println("All possible Obstacle unique Paths: " + findUniqueObstaclePaths(obstacleGrid, visited, 0, 0, 0));
    }

    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];

        // Fill all columns of first row with 1 since robot can only reach these cells through right move
        for (int i = 0; i < n; i++) {
            paths[0][i] = 1;
        }

        // Fill all rows of first column with 1 since robot can only reach these cells through down move
        for (int j = 0; j < m; j++) {
            paths[j][0] = 1;
        }

        // fill the remaining cells
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                paths[row][col] = paths[row - 1][col] + paths[row][col - 1];
            }
        }

        return paths[m - 1][n - 1];
    }

    /**
     * PROBLEM 2:
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time.
     * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.

     Note: m and n will be at most 100.

     Example 1:

     Input:
     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]
     Output: 2
     Explanation:
     There is one obstacle in the middle of the 3x3 grid above.
     There are two ways to reach the bottom-right corner:
     1. Right -> Right -> Down -> Down
     2. Down -> Down -> Right -> Right
     */

    public static int findUniqueObstaclePaths(int[][] obstacleGrid, int[][] visited, int x, int y, int dist) {
        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
            return dist + 1;
        }

        visited[x][y] = 1;

        // Down move
        if (isValid(x + 1, y, obstacleGrid) && isSafe(x + 1, y, obstacleGrid, visited)) {
            dist = findUniqueObstaclePaths(obstacleGrid, visited, x + 1, y, dist);
        }

        // right move
        if (isValid(x, y + 1, obstacleGrid) && isSafe(x, y + 1, obstacleGrid, visited)) {
            dist = findUniqueObstaclePaths(obstacleGrid, visited, x, y + 1, dist);
        }

        visited[x][y] = 0;

        return dist;
    }

    private static boolean isValid(int x, int y, int[][] obstacleGrid) {
        return x >= 0 && y >= 0 && x < obstacleGrid.length && y < obstacleGrid[0].length;
    }

    private static boolean isSafe(int x, int y, int[][] obstacleGrid, int[][] visited) {
        return !(obstacleGrid[x][y] == 1 || visited[x][y] != 0);
    }
}
