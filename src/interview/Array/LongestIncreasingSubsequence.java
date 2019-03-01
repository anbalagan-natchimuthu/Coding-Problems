package interview.Array;

import java.util.Arrays;

/**
 * PROBLEM : 1 https://www.youtube.com/watch?v=CE2b_-XfVDk The Longest Increasing Subsequence (LIS) problem is to find
 * the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in
 * increasing order. For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33,
 * 50, 60, 80}. https://www.geeksforgeeks.org/?p=12832
 */
public class LongestIncreasingSubsequence {

  public static void main(String args[]) {
    int arr[] = {10, 22, 9, 33, 21, 50, 41, 60};
    System.out.println("Length of lis is " + subSequenceLength(arr));

    arr = new int[]{50, 3, 10, 7, 40, 80};
    System.out.println("Length of lis is " + subSequenceLength(arr));

    arr = new int[]{3, 2, 1};
    System.out.println("Length of lis is " + subSequenceLength(arr));

    arr = new int[]{1, 101, 2, 3, 100, 4, 5};
    System.out.println("Sum of maximum sum increasing " + " subsequence is " + sumOfIncreasingSubSequence(arr));
  }

  private static int subSequenceLength(int[] inputArray) {

    int[] res = new int[inputArray.length];
    // Every element is sub sequence with length 1.
    Arrays.fill(res, 1);
    int max = 1;

    /* Compute optimized LIS values in bottom up manner */
    for (int i = 1; i < inputArray.length; i++) {
      for (int j = 0; j < i; j++) {
        if (inputArray[i] > inputArray[j] && res[i] < res[j] + 1) {
          res[i] = res[j] + 1;
          if (res[i] > max) {
            max = res[i];
          }
        }
      }
    }

    return max;
  }

  /*
   PROBLEM : 2
      Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given
      array such that the integers in the subsequence are sorted in increasing order. For example,
      if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100),
      if the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 + 10)
      and if the input array is {10, 5, 4, 3}, then output should be 10
   */
  private static int sumOfIncreasingSubSequence(int[] inputArray) {

    int[] res = new int[inputArray.length];

    /* Initialize msis values for all indexes */
    for (int i = 0; i < inputArray.length; i++) {
      res[i] = inputArray[i];
    }
    int sum = inputArray[0];

    /* Compute optimized LIS values in bottom up manner */
    for (int i = 1; i < inputArray.length; i++) {
      for (int j = 0; j < i; j++) {
        if (inputArray[i] > inputArray[j] && res[i] < res[j] + inputArray[i]) {
          res[i] = res[j] + inputArray[i];
          if (sum < res[i]) {
            sum = res[i];
          }
        }
      }
    }

    return sum;
  }
}
