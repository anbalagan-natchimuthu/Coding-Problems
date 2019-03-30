package interview.Array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 *
 * https://www.geeksforgeeks.org/count-pairs-difference-equal-k/
 *
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here
 * a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute
 * difference is k.
 *
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 */
public class countPairsWithDiffK {

  public static void main(String[] args) {
    int count = countPairsWithKDiff(new int[]{1, 2, 3, 4, 5}, 1);
    System.out.println("count::" + count);

    count = countPairsWithKDiff(new int[]{1, 3, 1, 3, 5, 4, 4}, 0);
    System.out.println("count::" + count);
  }

  private static int countPairsWithKDiff(int[] arr, int diff) {
    int count = 0;
    Arrays.sort(arr); // Sort array elements

    int l = 0;
    int r = 0;
    while (r < arr.length) {
      if (arr[r] - arr[l] == diff) {
        if (diff == 0 && l == r) {
          r++;
        } else {
          count++;
          l++;
          r++;
        }
      } else if (arr[r] - arr[l] > diff) {
        l++;
      } else // arr[r] - arr[l] < sum
      {
        r++;
      }
    }
    return count;
  }
}
