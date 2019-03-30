package interview.Array;

/**
 * https://www.geeksforgeeks.org/minimum-adjacent-swaps-to-move-maximum-and-minimum-to-corners/
 * Given N number of elements, find the minimum number of swaps required so that the maximum element is at the
 * beginning and the minimum element is at last with the condition that only swapping of adjacent elements is allowed.
 *
 * Examples:
 *
 * Input: a[] = {3, 1, 5, 3, 5, 5, 2}
 * Output: 6
 * Step 1: Swap 5 with 1 to make the array as {3, 5, 1, 3, 5, 5, 2}
 * Step 2: Swap 5 with 3 to make the array as {5, 3, 1, 3, 5, 5, 2}
 * Step 3: Swap 1 with 3 at its right to make the array as {5, 3, 3, 1, 5, 5, 2}
 * Step 4: Swap 1 with 5 at its right to make the array as {5, 3, 3, 5, 1, 5, 2}
 * Step 5: Swap 1 with 5 at its right to make the array as {5, 3, 3, 5, 5, 1, 2}
 * Step 6: Swap 1 with 2 at its right to make the array as {5, 3, 3, 5, 5, 2, 1}
 * After performing 6 swapping operations 5 is at the beginning and 1 at the end
 *
 * Input: a[] = {5, 6, 1, 3}
 * Output: 2
 */

public class MinimumAdjacentSwaps {

  // PROBLEM : 1
  public static void minimumSwaps(int a[], int n) {
    int maxx = Integer.MIN_VALUE, minn = Integer.MAX_VALUE, l = 0, r = 0;
    for (int i = 0; i < n; i++) {

      // Index of leftmost largest element
      if (a[i] > maxx) {
        maxx = a[i];
        l = i;
      }

      // Index of rightmost smallest element
      if (a[i] <= minn) {
        minn = a[i];
        r = i;
      }
    }
    if (r < l) {
      System.out.println(l + (n - r - 2));
    } else {
      System.out.println(l + (n - r - 1));
    }
  }

  /**
   * PROBLEM: 2
   * https://leetcode.com/problems/couples-holding-hands/
   *
   * N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of
   * swaps so
   * that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and
   * switch seats.
   *
   * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first
   * couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
   *
   * The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th
   * seat.
   */

  public static int minSwapsCouples(int[] row) {

    int swaps = 0;

    for (int i = 0; i < row.length; i += 2) {
      int x = row[i];
      if (row[i + 1] == (x ^ 1)) {
        continue;
      }
      swaps++;

      for (int j = i + 1; j < row.length; j++) {
        if (row[j] == (x ^ 1)) {
          row[j] = row[i + 1];
          row[i + 1] = (x ^ 1);
          break;
        }
      }
    }
    return swaps;
  }

  // Driver Code
  public static void main(String args[]) {
    int a[] = {5, 6, 1, 3};
    int n = a.length;
    minimumSwaps(a, n);

    int res = minSwapsCouples(new int[]{0, 2, 1, 3});
    System.out.println(res);
  }
}
