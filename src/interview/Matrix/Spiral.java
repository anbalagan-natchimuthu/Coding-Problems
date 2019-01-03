package interview.Matrix;

/**
 * https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
 * Print a given matrix in spiral form.
 * https://www.youtube.com/watch?v=qEZoUVOqOs8&feature=youtu.be.
 * Input:
 1    2   3   4
 5    6   7   8
 9   10  11  12
 13  14  15  16
 Output:
 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10


 Input:
 1   2   3   4  5   6
 7   8   9  10  11  12
 13  14  15 16  17  18
 Output:
 1 2 3 4 5 6 12 18 17 16 15 14 13 7 8 9 10 11
 */
public class Spiral {
    public static void main(String[] args) {
        int a[][] = { { 1, 2, 3, 4, 5, 6 },
            { 7, 8, 9, 10, 11, 12 },
            { 13, 14, 15, 16, 17, 18 }
        };
        printSpiral(a);
    }

    private static void printSpiral(int[][] inputArray) {
        int rowStarting = 0;
        int rowEnding = inputArray.length;
        int columnStarting = 0;
        int columnEnding = inputArray[0].length;
        int i;

        while (rowStarting < rowEnding && columnStarting <columnEnding) {
            // Print First row
            for (i = columnStarting; i < columnEnding; i++) {
                System.out.print(inputArray[rowStarting][i] + "  ");
            }
            rowStarting++;

            //Print last column
            for (i = rowStarting; i < rowEnding; ++i) {
                System.out.print(inputArray[i][columnEnding - 1] + "  ");
            }
            columnEnding--;

            if (rowStarting < rowEnding) {
                // Print last row
                for (i = columnEnding - 1; i >= columnStarting; i--) {
                    System.out.print(inputArray[rowEnding - 1][i] + "  ");
                }
                rowEnding--;
            }

            if (columnStarting < columnEnding) {
                //Print first column
                for (i = rowEnding - 1; i >= rowStarting; i--) {
                    System.out.print(inputArray[i][columnStarting] + "  ");
                }
                columnStarting++;
            }
        }
    }
}
