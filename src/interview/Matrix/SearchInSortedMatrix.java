package interview.Matrix;


public class SearchInSortedMatrix {

  /**
   * PROBLEM: 1
   * https://www.youtube.com/watch?v=dsPdwhRR_84
   * Search in a row wise and column wise sorted matrix
   *
   * Input : mat[4][4] = { {10, 20, 30, 40},
   *                       {15, 25, 35, 45},
   *                       {27, 29, 37, 48},
   *                       {32, 33, 39, 50}};
   *               x = 29
   * Output : Found at (2, 1)
   */
  public static boolean findElement(int[][] matrix, int target){
    int i = 0;
    int j = matrix[0].length-1;

    while (i < matrix.length && j >= 0) {
      if (matrix[i][j] == target) {
        return true;
      } else if (matrix[i][j] < target) {
        i++;
      } else {
        j--;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[][] matrix = {
                      {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}
                     };
    System.out.println(findElement(matrix, 37));
    System.out.println(findElement(matrix, 42));
  }
}
