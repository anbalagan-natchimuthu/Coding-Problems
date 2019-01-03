package interview.Matrix;

public class RowColumnSortedMatrix {

    public static void main(String[] args) {
        int[][] mat = { { 10, 20, 31, 40 },
                        { 15, 25, 35, 45 },
                        { 27, 29, 37, 48 },
                        { 32, 33, 39, 50 } };
        findNumber(mat, 4, 37);
    }

    public static int findNumber(int[][] mat, int n, int number) {

        int i = 0, j = n - 1;  //set indexes for top right element
        while (i < n && j >= 0) {
            if (number == mat[i][j]) {
                System.out.println("Number found i " + i + "j " + j);
                return 1;
            } else if (number > mat[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        System.out.println("Element not found");
        return 0;
    }
}
