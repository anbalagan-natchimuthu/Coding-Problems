package interview.DP;

/**
 * Given a 2D boolean array, find the largest square sub array of true values.
 * The return value should be the side length of the largest square sub array subarray.
 */
public class SquareSubMatrix {
    public static void main(String[] args) {
        boolean[][] inputArray = new boolean[][] {
            {true, true, true, true, true},
            {true, true, true, true, false},
            {true, true, true, true, false},
            {true, true, true, true, false},
            {true, false, false, false, false}
    };
        System.out.println(squareSubmatrix(inputArray));
    }

    public static int squareSubmatrix(boolean[][] arr) {
        int max = 0;
        // Initialize cache
        int[][] cache =
            new int[arr.length][arr[0].length];
        int row = 0, column = 0;
        // Iterate over matrix to compute each value
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                // If we’re in the first row/column then
                // the value is just 1 if that cell is
                // true and 0 otherwise. In other rows and
                // columns need to look up and to the left
                if (i == 0 || j == 0) {
                    cache[i][j] = arr[i][j] ? 1 : 0;
                } else if (arr[i][j]) {
                    cache[i][j] =
                        Math.min(Math.min(cache[i][j - 1],
                            cache[i - 1][j]),
                            cache[i - 1][j - 1]) + 1;
                }
                if (cache[i][j] > max) {
                    max = cache[i][j];
                    row = i;
                    column = j;
                }
            }
        }

        for (int i = max - row - 1; i < max; i++) {
            for (int j = max - column - 1; j < max; j++) {
                System.out.print("[" + i + "][" + j + "]  ");
            }
            System.out.println();
        }
        return max;
    }
}
