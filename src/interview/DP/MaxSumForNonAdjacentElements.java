package interview.DP;

import java.util.TreeMap;

/**
 * Find maximum sum for non adjacent elements.
 * https://leetcode.com/problems/house-robber/
 * https://leetcode.com/problems/house-robber-ii/
 *
 * https://www.youtube.com/watch?v=UtGtF6nc35g
 */
public class MaxSumForNonAdjacentElements {

  public static void main(String[] args) {
    int arr[] = {2, 10, 13, 4, 2, 15, 10};
    System.out.println(maxSum(arr));

    System.out.println(maxSumInRound(new int[]{1, 2, 3, 1}));

    System.out.println(deleteAndEarn(new int[]{2, 2, 4, 3, 3, 3}));
    System.out.println(deleteAndEarn(new int[]{2, 5, 9, 4}));
  }

  /**
   * PROBLEM: 1
   */
  public static int maxSum(int[] arr) {
    int inclusive = 0;
    int exclusive = 0, temp;
    for (int i = 0; i < arr.length; i++) {
      temp = inclusive;
      inclusive = Math.max(inclusive, exclusive + arr[i]);
      exclusive = temp;
    }

    return inclusive;
  }

  /**
   * PROBLEM: 2
   * Find maximum sum from left to right ignoring first element.
   * Find maximum sum from right to left ignoring last element.
   * Maximum of two will be the answer. It guarantees that both first and last element
   * will be not selected together.
   */
  public static int maxSumInRound(int[] arr) {
    int inclusive = arr[1];
    int exclusive = 0, temp;
    for (int i = 2; i < arr.length; i++) {
      temp = inclusive;
      inclusive = Math.max(inclusive, exclusive + arr[i]);
      exclusive = temp;
    }

    int inclusive1 = arr[arr.length - 2];
    int exclusive1 = 0;
    for (int i = arr.length - 3; i >= 0; i--) {
      temp = inclusive1;
      inclusive1 = Math.max(inclusive1, exclusive1 + arr[i]);
      exclusive1 = temp;
    }

    return Math.max(inclusive, inclusive1);
  }

  /**
   * PROBLEM: 3
   * https://leetcode.com/problems/delete-and-earn/
   * Given an array nums of integers, you can perform operations on the array.
   *
   * In each operation, you pick any nums[i] and delete it to earn nums[i] points.
   * After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
   *
   * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
   *
   * Example 1:
   *
   * Input: nums = [3, 4, 2]
   * Output: 6
   * Explanation:
   * Delete 4 to earn 4 points, consequently 3 is also deleted.
   * Then, delete 2 to earn 2 points. 6 total points are earned.
   */
  public static int deleteAndEarn(int[] nums) {
    // sort it in increment order
    final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    for (final int num : nums) {
      treeMap.put(num, treeMap.getOrDefault(num, 0) + num);
    }
    int prevPrevious = 0, previous = 0;
    for (final int num : treeMap.keySet()) {
      if (!treeMap.containsKey(num - 1)) {
        prevPrevious = previous;
        previous += treeMap.get(num);
      } else {
        final int temp = Math.max(prevPrevious + treeMap.get(num), previous);
        prevPrevious = previous;
        previous = temp;
      }
    }
    return previous;
  }
}
