package interview.Array;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray
 * of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Time complexity: O(n). Single iteration of O(n).
 * Each element can be visited atmost twice, once by the right pointer(i) and (atmost)once by the left pointer.
 * Space complexity: O(1) extra space. Only constant space required for left, sum, ans and i.
 *
 */
public class MinimumSizeSubarraySum {

  public static int minSubArrayLen(int target, int[] nums) {

    int left = 0;
    int ans = Integer.MAX_VALUE;
    int sum = 0;

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      while (sum >= target) {
        ans = Math.min(ans, i+1 - left);
        sum -= nums[left++];
      }

    }

    return ans != Integer.MAX_VALUE ? ans : 0;

  }

  public static void main(String[] args) {
    System.out.println(minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
  }

}
