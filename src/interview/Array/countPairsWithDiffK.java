package interview.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    System.out.println("count::" + findPairs(new int[]{1, 2, 3, 4, 5}, 1));

    count = countPairsWithKDiff(new int[]{1, 3, 1, 3, 5, 4, 4}, 0);
    System.out.println("count::" + count);
    System.out.println("count::" + findPairs(new int[]{1, 3, 1, 3, 5, 4, 4}, 0));

    count = countPairsWithKDiff(new int[]{1, 1, 3, 4, 5, 3}, 2);
    System.out.println("count::" + count);
    System.out.println("count::" + findPairs(new int[]{1, 1, 3, 4, 5, 3}, 2));
  }

  private static int countPairsWithKDiff(int[] arr, int diff) {
    int count = 0;
    Arrays.sort(arr); // Sort array elements

    int l = 0;
    int r = 0;
    while (r < arr.length) {
      if (l != r && arr[r] - arr[l] == diff) {
        int tempCount = 0;
        int tempLeft = arr[l];
        l++;
        while (tempLeft == arr[l]) {
          tempCount++;
          l++;
        }

        int tempRight = arr[r];
        r++;
        while (r < arr.length && tempRight == arr[r]) {
          tempCount++;
          r++;
        }
        count += (Math.pow(2, tempCount));

      } else if (arr[r] - arr[l] > diff) {
        l++;
      } else // arr[r] - arr[l] < sum
      {
        r++;
      }
    }
    return count;
  }


  public static int findPairs(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k < 0)   return 0;

    Map<Integer, Integer> map = new HashMap<>();
    int count = 0;
    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (k == 0) {
        //count how many elements in the array that appear more than twice.
        if (entry.getValue() >= 2) {
          count++;
        }
      } else {
        if (map.containsKey(entry.getKey() + k)) {
          count++;
        }
      }
    }

    return count;
  }
}
