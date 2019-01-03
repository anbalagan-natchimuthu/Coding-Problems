package interview.Matrix;

public class SubMatrixWith1s {

    public static void main(String[] args) {
        int M[][] = { { 0, 1, 1, 0, 1 }, { 1, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 },
            { 0, 0, 0, 0, 0 } };
        printMatrixWithAllOnes(M, 6, 5);
    }

    private static void printMatrixWithAllOnes(int[][] mat, int row, int column) {

        int[][] newMat = new int[row][column];

        for (int i = 0; i < row; i++) {
            newMat[i][0] = mat[i][0];
        }

        for (int i = 0; i < column; i++) {
            newMat[0][i] = mat[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (mat[i][j] == 1) {
                    newMat[i][j] = (Math.min(newMat[i][j - 1], Math.min(newMat[i - 1][j], newMat[i - 1][j - 1]))) + 1;
                } else {
                    newMat[i][j] = 0;
                }
            }
        }
        int max_of_s, max_i, max_j;
       /* Find the maximum entry, and indexes of maximum entry
             in S[][] */
        max_of_s = newMat[0][0];
        max_i = 0;
        max_j = 0;
        for (int k = 0; k < row; k++) {
            for (int p = 0; p < column; p++) {
                if (max_of_s < newMat[k][p]) {
                    max_of_s = newMat[k][p];
                    max_i = k;
                    max_j = p;
                }
            }
        }

        System.out.println("Maximum size sub-matrix is: ");
        for (int k = max_i; k > max_i - max_of_s; k--) {
            for (int p = max_j; p > max_j - max_of_s; p--) {
                System.out.print(mat[k][p] + " ");
            }
            System.out.println();
        }
    }
}
