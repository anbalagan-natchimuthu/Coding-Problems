package interview.Matrix;

/**
 * Transpose Matrix for N * N... This will not work for M * N
 */
public class TransposeMatrix {

    public static void main(String[] args) {
        int matrix[][] = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };
        transposeMatrix(matrix);
    }

    /**
     * Transpose Matrix without using space
     * @param arr
     */
    public static void transposeMatrix(int[][] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr[0].length; j++) {
                int temp = arr[j][i];
                arr[j][i] = arr[i][j];
                arr[i][j] = temp;
            }
        }

        displayMatrix(arr);
    }

    public static void displayMatrix(int[][] a){
        for(int i=0 ; i<a.length ; i++)
        {
            for(int j=0 ; j<a[0].length ; j++)
            {
                System.out.print(a[i][j] + " ");
            }

            System.out.println();
        }
    }
}
