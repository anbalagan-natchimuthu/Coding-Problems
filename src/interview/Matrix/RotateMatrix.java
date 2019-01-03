package interview.Matrix;

import java.util.Arrays;

/**
 * Rotate Matrix Clockwise and Anti-clockwise With / without additional space.
 * https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 */
public class RotateMatrix {
    private static int[][] rotateMatrixClockwise90Degree(int[][] inputMatrix) {
        int rows = inputMatrix.length;
        int cols = inputMatrix[0].length;
        int[][] outputMatrix = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            int k = 0;
            for (int j = rows - 1; j >= 0; j--) {
                outputMatrix[i][k++] = inputMatrix[j][i];
            }
        }
        return outputMatrix;
    }

    private static int[][] rotateMatrixAntiClockwise90Degree(int[][] inputMatrix) {
        int rows = inputMatrix.length;
        int cols = inputMatrix[0].length;
        int[][] outputMatrix = new int[cols][rows];
        int k = rows - 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                outputMatrix[i][j] = inputMatrix[j][k];
            }
            k--;
        }
        return outputMatrix;
    }

    // An Inplace function (without Additional space)to rotate a N x N matrix by 90 degrees in anti-clockwise direction
    // https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
    static int[][] rotateMatrixInplace(int mat[][]) {
        int N = mat.length;
        // Consider all squares one by one
        for (int x = 0; x < N / 2; x++) {
            // Consider elements in group of 4 in
            // current square
            for (int y = x; y < N - 1 - x; y++) {
                // store current cell in temp variable
                int temp = mat[x][y];

                // move values from right to top
                mat[x][y] = mat[y][N - 1 - x];

                // move values from bottom to right
                mat[y][N - 1 - x] = mat[N - 1 - x][N - 1 - y];

                // move values from left to bottom
                mat[N - 1 - x][N - 1 - y] = mat[N - 1 - y][x];

                // assign temp to left
                mat[N - 1 - y][x] = temp;
            }
        }
        return mat;
    }

    // https://www.geeksforgeeks.org/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/
    // An Inplace function (without Additional space)to rotate a N x N matrix by 90 degrees in clockwise direction
    static int[][] rotateMatrixInPlaceClockWise(int mat[][]) {
        int N = mat.length;
        // Consider all squares one by one
        for (int x = 0; x < N / 2; x++) {
            // Consider elements in group of 4 in
            // current square
            for (int y = x; y < N - 1 - x; y++) {
                // store current cell in temp variable
                int temp = mat[x][y];

                // move values from right to top
                mat[x][y] = mat[N - 1 - y][x];

                // move values from bottom to right
                mat[N - 1 - y][x] = mat[N - 1 - x][N - 1 - y];

                // move values from left to bottom
                mat[N - 1 - x][N - 1 - y] = mat[y][N - 1 - x];

                // assign temp to left
                mat[y][N - 1 - x] = temp;
            }
        }
        return mat;
    }

    //https://www.geeksforgeeks.org/rotate-matrix-90-degree-without-using-extra-space-set-2/
    // Function to anticlockwise rotate matrix by 90 degree
    static int[][] rotate90(int arr[][])
    {
        //There are two steps :

        //1.Find transpose of matrix.
        //2. Reverse columns of the transpose.

        transpose(arr);

        reverseColumns(arr);

        return arr;
    }

    // After transpose we swap elements of
    // column one by one for finding left
    // rotation of matrix by 90 degree
    static void reverseColumns(int arr[][])
    {
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0,  k = arr[0].length - 1;
                j < k; j++, k--)
            {
                int temp = arr[j][i];
                arr[j][i] = arr[k][i];
                arr[k][i] = temp;
            }
    }


    // Function for do transpose of matrix
    static void transpose(int arr[][])
    {
        for (int i = 0; i < arr.length; i++)
            for (int j = i; j < arr[0].length; j++)
            {
                int temp = arr[j][i];
                arr[j][i] = arr[i][j];
                arr[i][j] = temp;
            }
    }

    // Function for print matrix
    static void printMatrix(int arr[][])
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println("");

        }
    }

    /**
     * Given a matrix, clockwise rotate elements in it.
     * https://www.geeksforgeeks.org/rotate-matrix-elements/
     */
    private static int[][] rotateMatrix(int[][] arr) {
        int rowStarting = 0, rowEnding = arr.length, columnStarting = 0, columnEnding = arr[0].length;
        int prevElement, currentElement;

        while (rowStarting < rowEnding && columnStarting < columnEnding) {

            if (rowStarting + 1 == arr.length || columnStarting + 1 == arr[0].length) {
                break;
            }

            // Store the first element of next row, this element will replace first element of current row
            prevElement = arr[rowStarting + 1][columnStarting];

            // Move elements of first row from the remaining rows
            for (int i = columnStarting; i <columnEnding; i++) {
                currentElement = arr[rowStarting][i];
                arr[rowStarting][i] = prevElement;
                prevElement = currentElement;
            }

            rowStarting ++;

            // Move elements of last column from the remaining columns
            for (int i = rowStarting; i <rowEnding; i++) {
                currentElement = arr[i][columnEnding -1];
                arr[i][columnEnding -1] = prevElement;
                prevElement = currentElement;
            }

            columnEnding --;

            if (rowStarting < rowEnding) {
                // Move elements of last row from the remaining rows
                for (int i = columnEnding - 1; i >= columnStarting; i--) {
                    currentElement = arr[rowEnding - 1][i];
                    arr[rowEnding - 1][i] = prevElement;
                    prevElement = currentElement;
                }
            }

            rowEnding --;

            if (columnStarting < columnEnding) {
                // Move elements of last row from the remaining rows
                for (int i = rowEnding - 1; i >= rowStarting; i--) {
                    currentElement = arr[i][columnStarting];
                    arr[i][columnStarting] = prevElement;
                    prevElement = currentElement;
                }
            }

            columnStarting ++;

        }
        return arr;
    }

    public static void main(String args[]) {

        final int matrix[][] = {
            { 1,  2,  3,  4,   5,  6 },
            { 7,  8,  9,  10, 11, 12 },
            {13, 14, 15,  16, 17, 18 },
            {19, 20, 21,  22, 23, 24 },
            {25, 26, 27,  28, 29, 30 },
            {31, 32, 33,  34, 35, 36 }
        };

        System.out.println("******************************************");
        System.out.println("** ROTATE MATRIX CLOCK-WISE 90 DEGREE ****");
        System.out.println("******************************************");

        int tempMatrix[][] = deepCopy(matrix);
        int result[][] = rotateMatrixClockwise90Degree(tempMatrix);
        displayMatrix(result);

        System.out.println("******************************************");
        System.out.println("** ROTATE MATRIX CLOCK-WISE 90 DEGREE USING INPLACE FUNCTION ****");
        System.out.println("******************************************");

        tempMatrix = deepCopy(matrix);
        result = rotateMatrixInPlaceClockWise(tempMatrix);
        displayMatrix(result);

        System.out.println("************************************************");
        System.out.println("** ROTATE MATRIX ANTI CLOCK-WISE 90 DEGREE ****");
        System.out.println("*************************************************");

        tempMatrix = deepCopy(matrix);
        int result1[][] = rotateMatrixAntiClockwise90Degree(tempMatrix);
        displayMatrix(result1);

        System.out.println("******************************************");
        System.out.println("** ROTATE MATRIX ANTI CLOCK-WISE 90 DEGREE USING INPLACE FUNCTION ****");
        System.out.println("******************************************");

        tempMatrix = deepCopy(matrix);
        result1 = rotateMatrixInplace(tempMatrix);
        displayMatrix(result1);

        System.out.println("******************************************");
        System.out.println("** ROTATE MATRIX ANTI CLOCK-WISE 90 DEGREE USING TRANSPOSE METHOD ****");
        System.out.println("******************************************");

        tempMatrix = deepCopy(matrix);
        result1 = rotate90(tempMatrix);
        displayMatrix(result1);


        System.out.println("************************************************");
        System.out.println("** ROTATE MATRIX CLOCK-WISE ****");
        System.out.println("*************************************************");

        tempMatrix = deepCopy(matrix);
        result1 = rotateMatrix(tempMatrix);
        displayMatrix(result1);
    }

    // Function to print the matrix
    static void displayMatrix(int mat[][]) {
        int N = mat.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + mat[i][j]);
            }

            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
