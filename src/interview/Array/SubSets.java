package interview.Array;

import java.util.Arrays;
import java.util.Objects;

/**
 * https://www.youtube.com/watch?v=bGC2fNALbNU
 * Find and print all subsets of a given set! (Given as an array.)
 * e.g. given array {1,2} print all subsets {}, {1}, {2}, {1,2}
 */
public class SubSets {

  public static void main(String[] args) {
    int[] input = {1, 2};
    Integer[] subSet = new Integer[input.length];
    printSubSet(input, subSet, 0);

    int[] nums1 = {2, 1, 3, 4};
    System.out.println(countSubsets(nums1, 8)); // 15

    int[] nums2 = {2, 4, 5, 7};
    System.out.println(countSubsets(nums2, 8)); // 5
  }

  private static void printSubSet(int[] inputArr, Integer[] subsetArr, int i) {
    if (i == inputArr.length) {
      subsetArr = Arrays.stream(subsetArr).filter(Objects::nonNull).toArray(Integer[]::new);
      System.out.println(Arrays.toString(subsetArr));
      //System.out.println(Arrays.toString(subsetArr));
    } else {

      // Not including current element in the subset
      subsetArr[i] = null;
      printSubSet(inputArr, subsetArr, i + 1);

      // Including current element in the subset
      subsetArr[i] = inputArr[i];
      printSubSet(inputArr, subsetArr, i + 1);
    }
  }

  /**
   * For a given list of integers and integer K, find the number of non-empty subsets S such that min(S) + max(S) <= K.
   *
   * Example 1:
   *
   * nums = [2, 4, 5, 7]
   * k = 8
   * Output: 5
   * Explanation: [2], [4], [2, 4], [2, 4, 5], [2, 5]
   *
   * Time complexity: O(nlogn).
   * Space complexity: O(1).
   *
   * Example: 2
   * nums = [2, 4, 2, 5, 7]
   * k = 10
   * Output: 27
   * Explanation: 31 (2^5 - 1) - 4 ([7], [5, 7], [4, 5, 7], [4, 7]) = 27
   */
  public static int countSubsets(int[] nums, int k) {
    Arrays.sort(nums);
    int count = 0;
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
      if (nums[lo] + nums[hi] > k) {
        hi--;
      } else {
        // 1 "<<" n -- signed left shift operator calculates the value of 2 ^ n.
        count += 1 << (hi - lo);
        lo++;
      }
    }
    return count;
  }
}
