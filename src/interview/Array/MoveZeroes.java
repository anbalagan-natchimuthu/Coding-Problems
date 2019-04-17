package interview.Array;

/**
 * https://leetcode.com/problems/move-zeroes/
 *
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
 * non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {

  public static void main(String[] args) {
    int[] arr = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9};
    moveZeroesWithMinWrites(arr);
  }

  public static void moveZeroesWithMinWrites(int[] nums) {
    int left = 0;
    for (int num : nums) {
      if (num != 0) {
        nums[left++] = num;
      }
    }

    for (int j = left; j < nums.length; j++) {
      nums[j] = 0;
    }

    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + " ");
    }
  }
}
