package interview.Matrix;

public class BooleanMatrix {

    //
    // Example 3
    // The matrix
    // 1 0 0 1
    // 0 0 1 0
    // 0 0 0 0
    // should be changed to following
    // 1 1 1 1
    // 1 1 1 1
    // 1 0 1 1

    public static void main(String[] args) {
        int mat[][] = { { 1, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, };

        System.out.println("Matrix Intially");

        printMatrix(mat, 3, 4);
        //
        processMatrix(mat, 3, 4);
        System.out.println("Matrix after modification n");
        printMatrix(mat, 3, 4);
    }

    public static void processMatrix(int[][] mat, int m, int n) {
        int[] rowMatrix = new int[m];
        int[] columnMatrix = new int[n];

        for (int i = 0; i < m; i++) {
            rowMatrix[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            columnMatrix[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowMatrix[i] = 1;
                    columnMatrix[j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowMatrix[i] == 1 || columnMatrix[j] == 1) {
                    mat[i][j] = 1;
                }
            }
        }
    }

    private static void printMatrix(int[][] mat, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.println(mat[i][j]);
            }
        }
    }
}
