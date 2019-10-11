package interview.Graph;

/**
 * Check the image "Uber qn.png".
 *
 * Validate given cell is surrounded with opponent symbol.
 * If it's surrounded with same symbol, then check it's neighbours symbol.
 * If it's neighbour cell has empty cell, then return false.
 */
public class SurroundedWithGoodValue {

  public static void main(String[] args) {

    char[][] input = {
        {' ', '1', '0','0', '1'},
        {'1', '1', '0','0', '1'},
        {'1', '0', '1','0', '1'},
        {'0', '1', '0','1', '1'},
        {'0', '0', '0','1', '1'},
        {'0', '1', '1','1', '0'},
        {'0', '0', '1',' ', '0'}
    };

    System.out.println(isGoodVal(input, 2, 1)); // true
    System.out.println(isGoodVal(input, 3, 2)); // true
    System.out.println(isGoodVal(input, 5, 1)); // false
    System.out.println(isGoodVal(input, 0, 2)); // true
  }

  public static boolean isGoodVal(char[][] arr, int x, int y) {
    if (x < arr.length && y < arr[0].length) {
      return isGood(arr, x, y, arr[x][y], new boolean[arr.length][arr[0].length]);
    }
    return false;
  }

  public static boolean isGood(char[][] arr, int x, int y, char posVal, boolean[][] visited) {

    if ((x - 1 >= 0 && arr[x - 1][y] == ' ') || (x + 1 < arr.length && arr[x + 1][y] == ' ') || (y - 1 >= 0
        && arr[x][y - 1] == ' ') || (y + 1 < arr[0].length && arr[x][y + 1] == ' ')) {
      return false;
    }
    visited[x][y] = true;

    boolean returnVal = true;
    if (x - 1 >= 0 && !visited[x - 1][y] && arr[x - 1][y] == posVal) {
      returnVal = isGood(arr, x - 1, y, posVal, visited);
    }

    if (x + 1 < arr.length && !visited[x + 1][y] && arr[x + 1][y] == posVal) {
      returnVal = returnVal && isGood(arr, x + 1, y, posVal, visited);
    }

    if (y - 1 >= 0 && !visited[x][y - 1] && arr[x][y - 1] == posVal) {
      returnVal = returnVal && isGood(arr, x, y - 1, posVal, visited);
    }

    if (y + 1 < arr[0].length && !visited[x][y + 1] && arr[x][y + 1] == posVal) {
      returnVal = returnVal && isGood(arr, x, y + 1, posVal, visited);
    }

    visited[x][y] = false;
    return returnVal;
  }
}
