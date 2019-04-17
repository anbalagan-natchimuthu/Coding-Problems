package interview.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM : 1
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0.
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * https://www.geeksforgeeks.org/unique-triplets-sum-given-value/
 */
public class Triplet {

  public static void main(String[] args) {
    List<List<Integer>> result = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    for (int i = 0; i < result.size(); i++) {
      System.out.println(result.get(i).toString());
    }

    System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    if (nums == null || nums.length < 3) {
      return result;
    }

    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
      if (i == 0 || nums[i] > nums[i - 1]) {
        int j = i + 1;
        int k = nums.length - 1;

        while (j < k) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            List<Integer> l = new ArrayList<Integer>();
            l.add(nums[i]);
            l.add(nums[j]);
            l.add(nums[k]);
            result.add(l);

            j++;
            k--;

            //handle duplicate here
            while (j < k && nums[j] == nums[j - 1]) {
              j++;
            }
            while (j < k && nums[k] == nums[k + 1]) {
              k--;
            }
          } else if (nums[i] + nums[j] + nums[k] < 0) {
            j++;
          } else {
            k--;
          }
        }
      }
    }

    return result;
  }

  /**
   * PROBLEM : 2
   * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
   * Return the sum of the three integers. You may assume that each input would have exactly one solution.
   *
   * For example, given array S = {-1 2 1 -4}, and target = 1.
   * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
   */

  public static int threeSumClosest(int[] nums, int target) {
    int min = Integer.MAX_VALUE;
    int result = 0;

    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        int diff = Math.abs(sum - target);

        if (diff == 0) {
          return sum;
        }

        if (diff < min) {
          min = diff;
          result = sum;
        }
        if (sum <= target) {
          j++;
        } else {
          k--;
        }
      }
    }

    return result;
  }
}
