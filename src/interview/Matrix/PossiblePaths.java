package interview.Matrix;

/**
 * https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 * Count all possible paths from top left to bottom right of a mXn matrix.
 * The problem is to count all the possible paths from top left to bottom right of a mXn matrix with the constraints
 * that from each cell you can either move only to right or down
 */
public class PossiblePaths {


    // Returns count of possible paths to reach
    // cell at row number m and column number n
    // from the topmost leftmost cell (cell at 1, 1)
    static int  numberOfPathsUsingRecursion(int m, int n)
    {
        // If either given row number is first or
        // given column number is first
        if (m == 1 || n == 1)
            return 1;

        // If diagonal movements are allowed then
        // the last addition is required.
        return  numberOfPathsUsingRecursion(m-1, n) + numberOfPathsUsingRecursion(m, n-1);
        // + numberOfPaths(m-1,n-1);
    }

    // Returns count of possible paths to reach cell at row number m and column number n from
    //  the topmost leftmost cell (cell at 1, 1)
    static int numberOfPathsUsingDP(int m, int n) {
        // Create a 2D table to store results
        // of subproblems
        int count[][] = new int[m][n];

        // Count of paths to reach any cell in
        // first column is 1
        for (int i = 0; i < m; i++)
            count[i][0] = 1;

        // Count of paths to reach any cell in
        // first row is 1
        for (int j = 0; j < n; j++)
            count[0][j] = 1;

        // Calculate count of paths for other
        // cells in bottom-up manner using
        // the recursive solution
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)

                // By uncommenting the last part the
                // code calculates the total possible paths
                // if the diagonal Movements are allowed
                count[i][j] = count[i - 1][j] + count[i][j - 1]; //+ count[i-1][j-1];
        }
        return count[m - 1][n - 1];
    }

    // Driver program to test above function
    public static void main(String args[])
    {
        System.out.println("numberOfPaths:" + numberOfPathsUsingDP(3, 3));
        System.out.println("numberOfPathsUsingRecursion:" + numberOfPathsUsingRecursion(3, 3));
    }
}
