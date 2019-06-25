package interview.Array;

/**
 * Given an Array of integers, write a method that will return the integer with the maximum number of repetitions.
 * Your code is expected to run with O(n) time complexity and O(1) space complexity. The elements in the array are
 * between 0 to size(array) - 1 and the array will not be empty.
 *
 * f({3,1,2,2,3,4,4,4}) --> 4
 */
public class MaximumRepetitions {

  public static int getMaxRepetition(int[] a) {

    if (a == null || a.length == 0) {
      return 0;
    }

    int k = a.length;
    int max = 0;
    int index = -1;

    for (int i = 0; i < a.length; i++) {
      a[a[i] % k] += k;

      if (a[a[i] % k] > max) {
        max = a[a[i] % k];
        index = a[i] % k;
      }
    }

    return index;
  }

  public static void main(String[] args) {
    int max = getMaxRepetition(new int[]{3, 7, 2, 2, 3, 4, 4, 4});
    System.out.println(max);
  }
}
