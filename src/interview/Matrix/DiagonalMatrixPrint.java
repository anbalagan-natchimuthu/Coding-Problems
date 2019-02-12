package interview.Matrix;

/**
 * https://www.youtube.com/watch?v=T8ErAYobcbc
 */
public class DiagonalMatrixPrint {

    public static void main(String[] args) {
        int[][] mat = {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 }
                    };
        printDiagonal(mat);
        System.out.println("\n\n");
        printDiagonal1(mat);
    }

    public static void printDiagonal(int[][] mat) {

        for (int k = 0; k < mat.length; k++) {
            int i = k;
            int j = 0;
            while (i >= 0) {
                System.out.print(mat[i][j] + " ");
                i--;
                j++;
            }
            System.out.println();
        }

        for (int k = 1; k < mat[0].length; k++) {
            int i = mat.length - 1;
            int j = k;
            while (j < mat[0].length) {
                System.out.print(mat[i][j] + " ");
                i--;
                j++;
            }
            System.out.println();
        }
    }

    private static void printDiagonal1(int[][] mat) {

        for (int k = 0; k < mat[0].length; k++) {

            int i = 0;
            int j = k;
            while (j >= 0) {
                System.out.print(mat[i][j] + " ");
                i++;
                j--;
            }
            System.out.println();
        }

        for (int k = 1; k < mat.length; k++) {

            int i = k;
            int j = mat[0].length - 1;
            while (i < mat.length) {
                System.out.print(mat[i][j] + " ");
                i++;
                j--;
            }
            System.out.println();
        }
    }
}