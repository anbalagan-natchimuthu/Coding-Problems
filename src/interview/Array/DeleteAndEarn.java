package interview.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/delete-and-earn/
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element
 * equal to nums[i] - 1 or nums[i] + 1.
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
public class DeleteAndEarn {

  public static void main(String[] args) {
    System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
  }

  public static int deleteAndEarn(int[] nums) {
    final Map<Integer, Integer> values = new HashMap<>();
    for (final int num : nums) {
      values.put(num, values.getOrDefault(num, 0) + num);
    }
    int pre = 0, cur = 0;
    for (final int num : values.keySet()) {
      if (!values.containsKey(num - 1)) {
        pre = cur;
        cur += values.get(num);
      } else {
        final int temp = Math.max(pre + values.get(num), cur);
        pre = cur;
        cur = temp;
      }
    }
    return cur;
  }
}
