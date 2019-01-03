package interview.Matrix;

/**
 * Created by 212438472 on 1/10/18.
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0
 */
public class MandNMatrix {

    private static int[][] performMatrix (int[][] inputMatrix) {

        boolean[] row = new boolean[inputMatrix.length];
        boolean[] cols = new boolean[inputMatrix[0].length];

        for (int i=0; i< inputMatrix.length; i++) {
            for (int j=0; j < inputMatrix[i].length; j++) {
                if (inputMatrix[i][j] == 0) {
                    row[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i=0; i< inputMatrix.length; i++) {
            for (int j=0; j < inputMatrix[i].length; j++) {
                if (row[i] || cols[j]) {
                    inputMatrix[i][j] = 0;
                }
            }
        }
        return inputMatrix;
    }
    public static void main (String args[]) {
        int matrix[ ][ ] = { {1, 5, 6, 0},
                             {7, 8, 0, 9},
                             {3, 2, 5, 6}
                        };
        int result[][] = performMatrix(matrix);

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                System.out.print(result[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
