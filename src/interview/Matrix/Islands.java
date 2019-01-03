package interview.Matrix;
// A Java program to count the number of rectangular
// islands where every island is separated by a line

/**
 * https://www.geeksforgeeks.org/count-number-islands-every-island-separated-line/
 * Count number of islands where every island is row-wise and column-wise separated
 *
 * Given a rectangular matrix which has only two possible values ‘X’ and ‘O’.
 * The values ‘X’ always appear in form of rectangular islands and these islands are always row-wise and column-wise
 * separated by at least one line of ‘O’s. Note that islands can only be diagonally adjacent. Count the number of
 * islands in the given matrix.
 *
 */
public class Islands {

    // This function takes a matrix of 'X' and 'O'
    // and returns the number of rectangular islands
    // of 'X' where no two islands are row-wise or
    // column-wise adjacent, the islands may be diagonally
    // adjacent
    static int countIslands(int mat[][], int m, int n) {
        // Initialize result
        int count = 0;

        // Traverse the input matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If current cell is 'X', then check
                // whether this is top-leftmost of a
                // rectangle. If yes, then increment count
                if (mat[i][j] == 'X') {
                    if ((i == 0 || mat[i - 1][j] == 'O') && (j == 0 || mat[i][j - 1] == 'O')) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // Driver program
    public static void main(String[] args) {
        // Size of given matrix is m X n
        int m = 6;
        int n = 3;
        int mat[][] = { { 'O', 'O', 'O' },
                        { 'X', 'O', 'X' },
                        { 'X', 'X', 'O' },
                        { 'O', 'O', 'O' },
                        { 'O', 'X', 'X' },
                        { 'O', 'O', 'O' } };
        System.out.println("Number of rectangular islands is: " + countIslands(mat, m, n));
    }
}