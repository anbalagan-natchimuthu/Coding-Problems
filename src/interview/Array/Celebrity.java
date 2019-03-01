package interview.Array;

/**
 * https://www.geeksforgeeks.org/the-celebrity-problem/
 *
 * In a party of N people, only one person is known to everyone. Such a person may be present in the party,
 *  if yes, (s)he doesn’t know anyone in the party. We can only ask questions like “does A know B? “.
 *  Find the stranger (celebrity) in minimum number of questions.
 */
public class Celebrity {

  public static void main(String[] args) {
    int matrix[][] = {  { 0, 0, 1, 1 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 1, 0 } };

    System.out.println(findCelebrity(matrix));

    int matrix1[][] = {   { 0, 0, 1, 0 },
                          { 0, 0, 1, 0 },
                          { 0, 1, 0, 0 },
                          { 0, 0, 1, 0 } };

    System.out.println(findCelebrity(matrix1));
  }

  private static int findCelebrity(int[][] matrix) {
    int a = 0;
    int b = matrix[0].length - 1;

    while (a < b) {
      if (knows(matrix, a, b)) {
        a++;
      } else {
        b--;
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      if (i != a && (knows(matrix, a, i) || !knows(matrix, i, a))) {
        return -1;
      }
    }
    return a;
  }

  private static boolean knows (int[][] matrix, int a, int b) {
    if (matrix[a][b] == 1) {
      return true;
    } else {
      return false;
    }
  }
}
