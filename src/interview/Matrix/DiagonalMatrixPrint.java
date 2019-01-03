package matrix;

public class DiagonalMatrixPrint {

    public static void main(String[] args) {
        int[][] mat =
            { { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 16 } };
        //printDiagonal(mat, 4, 4);
        printZigZag(mat, 4, 4);
    }

    public static void printDiagonal(int[][] mat, int m, int n) {

        for (int k = 0; k < m; k++) {

            int i = k;
            int j = 0;
            while (i >= 0 && j < n) {
                System.out.println(mat[i][j]);
                i--;
                j++;
            }
        }

        for (int k = 1; k < n; k++) {

            int j = k;
            int i = m - 1;
            while (i >= 0 && j < n) {
                System.out.println(mat[i][j]);
                i--;
                j++;
            }
        }
    }

    private static void printZigZag(int[][] mat, int m, int n) {

        boolean zigzag = false;

        for (int k = 0; k < m; k++) {

            int i = k;
            int j = 0;
            while (i >= 0 && j < n) {
                if (!zigzag) {
                    System.out.println(mat[i][j]);
                    i--;
                    j++;
                }

                if (zigzag) {
                    System.out.println(mat[j][i]);
                    i--;
                    j++;
                }
            }
            zigzag = (zigzag == true) ? false : true;
        }

        for (int k = 1; k < n; k++) {

            int j = k;
            int i = m - 1;
            while (i >= 0 && j < n) {
                if (!zigzag) {
                    System.out.println(mat[i][j]);
                    i--;
                    j++;
                }
                if (zigzag) {
                    System.out.println(mat[j][i]);

                    i--;
                    j++;
                }
            }
            zigzag = (zigzag == true) ? false : true;
        }
    }
}

// Inorder pre ,post, dfs,bfs
// Given a tree find the level
//Print in reverse order
//