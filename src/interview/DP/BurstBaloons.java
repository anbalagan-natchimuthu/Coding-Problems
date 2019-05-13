package interview.DP;

/**
 * https://www.youtube.com/watch?v=IFNibRVgFBo
 *
 * https://leetcode.com/problems/burst-balloons/
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] *
 * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes
 * adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 *  Time Complexity : O(n^3)
 *  Space Complexity : O (n^2)
 */
public class BurstBaloons {

  public static void main(String[] args) {
    BurstBaloons burstBaloons = new BurstBaloons();
    System.out.println(burstBaloons.maxCoins(new int[] {3,1,5,8}));
  }
  public int maxCoins(int[] nums) {
   int[][] T = new int[nums.length][nums.length];

   for (int len = 1;  len <= nums.length; len++) {
     for (int i = 0; i <= nums.length-len; i++) {
      int j = i + len - 1;

      for (int k = i; k <=j; k++) {
        int before = 0;
        int after= 0;

        if (i != k) {
          before = T[i][k-1];
        }

        if (j != k) {
          after = T[k+1][j];
        }

        int leftValue =1;
        int rightValue =1;

        if (i != 0) {
          leftValue = nums[i-1];
        }

        if (j != nums.length -1) {
          rightValue = nums[j+1];
        }

        T[i][j] = Math.max(T[i][j], before + after + leftValue * nums[k] * rightValue);
      }
     }
   }
   return T[0][nums.length-1];
  }
}
