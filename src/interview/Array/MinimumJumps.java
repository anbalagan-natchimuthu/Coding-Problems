package interview.Array;

import java.util.Arrays;

/**
 * Given an array of integers where each element represents the max number of steps that can be made forward from that
 * element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the
 * first element). If an element is 0, then cannot move through that element.
 *
 * Example:
 *
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 8 ->9)
 * First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg to 5 or 8 or 9.
 * https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/#comment-3321908697
 * https://www.youtube.com/watch?v=cETfFsSTGJI
 */
public class MinimumJumps {

  public static void main(String[] args) {
    int arr[] = {1, 3, 6, 1, 0, 9};
    System.out.println("Minimum number of jumps to reach end is : " + minJumps(arr));
    System.out.println("Can Reach End::" + canJumpToEnd(arr));
    System.out.println("Can Jump to End::" + canJump(arr));
    System.out.println("\n\n\n");

    int arr1[] = {1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
    System.out.println("Minimum number of jumps to reach end is : " + minJumps(arr1));
    System.out.println("Can Reach End::" + canJumpToEnd(arr1));
    System.out.println("Can Jump to End::" + canJump(arr1));
    System.out.println("\n\n\n");

    int arr2[] = {2, 3, 1, 1, 2, 4, 2, 0, 1, 1};
    System.out.println("Minimum number of jumps to reach end is : " + minJumps(arr2));
    System.out.println("Can Reach End::" + canJumpToEnd(arr2));
    System.out.println("Can Jump to End::" + canJump(arr2));
    System.out.println("\n\n\n");

    int arr3[] = {1, 0, 1, 1};
    System.out.println("Minimum number of jumps to reach end is : " + minJumps(arr3));
    System.out.println("Can Reach End::" + canJumpToEnd(arr3));
    System.out.println("Can Jump to End::" + canJump(arr3));
  }

  public static int minJumps(int[] arr) {

    // if first element is 0, end cannot be reached
    if (arr.length == 0 || arr[0] == 0) {
      return Integer.MAX_VALUE;
    }

    // jumps[n-1] will hold the result
    int[] jumps = new int[arr.length];
    int[] actualJump = new int[arr.length];

    Arrays.fill(jumps, Integer.MAX_VALUE);

    jumps[0] = 0;

    // Find the minimum number of jumps to reach arr[i]
    // from arr[0], and assign this value to jumps[i]
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        // Check if it's possible to reach i from current location j by adding j + arr[j]
        if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {

          if (jumps[j] + 1 < jumps[i]) {
            actualJump[i] = j;
          }
          jumps[i] = Math.min(jumps[i], jumps[j] + 1);
        }
      }
    }

    System.out.println("....Actual Jump Steps....");
    int location = actualJump.length - 1;
    System.out.print(location + " ");
    while (true) {
      System.out.print(" " + actualJump[location] + " ");
      location = actualJump[location];
      if (location == 0) {
        break;
      }
    }
    System.out.println();
    return jumps[arr.length - 1];
  }

  /**
   * PROBLEM 2: https://leetcode.com/problems/jump-game/
   *
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   *
   * Each element in the array represents your maximum jump length at that position.
   *
   * Determine if you are able to reach the last index.
   *
   * Example 1:
   *
   * Input: [2,3,1,1,4]
   * Output: true
   * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
   *
   * Time complexity : O(n^2)  Space complexity : O(n)
   */
  public static boolean canJumpToEnd(int[] inputArr) {
    boolean[] jumpsArr = new boolean[inputArr.length];

    if (inputArr[0] == 0) {
      return false;
    }

    jumpsArr[0] = true;

    for (int i = 1; i < inputArr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (inputArr[j] + j >= i && jumpsArr[j] == true) {
          jumpsArr[i] = true;
          break;
        }
      }
    }
    return jumpsArr[jumpsArr.length - 1];
  }

  /**
   * https://leetcode.com/problems/jump-game/solution/
   *
   * Time complexity : O(n). We are doing a single pass through the nums array, hence n steps, where n is the
   * length of array nums.
   *
   * Space complexity : O(1). We are not using any extra memory.
   */
  public static boolean canJump(int[] nums) {
    int lastPos = nums.length - 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      if (i + nums[i] >= lastPos) {
        lastPos = i;
      }
    }
    return lastPos == 0;
  }
}
