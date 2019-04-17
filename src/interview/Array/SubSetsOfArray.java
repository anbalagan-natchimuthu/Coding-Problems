package interview.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Find Sets Of Numbers That Add Up To n.
 * e.g. {2, 4, 6, 10} and total is 16, then it should return 2 sets as {2, 4, 10} and {6, 10}.
 * Assumed that no negative numbers and all are unique.
 */

public class SubSetsOfArray {

  int returnVal = 0;

  public static void main(String[] args) {
    int[] array = new int[]{4, 2, 6};

    System.out.println("***** Possible subsets of Array using Recursion ************");
    SubSetsOfArray subSetsOfArray = new SubSetsOfArray();
    System.out.println(subSetsOfArray.subSetsNonDP(array, 6, array.length - 1));

    int arr1[] = {4, 1, 20, 3, 10, 5};
    System.out.println("subArrayEqualsToSum:: " + subSetsOfArray.subSetsNonDP(arr1, 33, arr1.length - 1));

    System.out.println("***** Sum in continuous subsets of Array with positive numbers ************");
    int arr[] = {4, 1, 20, 3, 10, 5};
    System.out.println("subArrayEqualsToSum:: " + subArrayEqualsToSum(arr, 33));

    arr = new int[]{31, 4, 20, 7, 10, 33};
    System.out.println(subArrayEqualsToSum(arr, 33));

    System.out.println("***** Sum in continuous subsets of Array including negative numbers ************");
    arr = new int[]{10, 2, -2, -20, 10};
    System.out.println(subArrayEqualsToSumWithNegativeNumbers(arr, -10));

    arr = new int[]{1, 10, 20, 3, 8, 5};
    System.out.println(subArrayEqualsToSumWithNegativeNumbers(arr, 16));
  }

  private int subSetsNonDP(int[] inputArray, int total, int i) {
    if (total == 0) {
      return 1;
    } else if (total < 0) {
      return 0;
    } else if (i < 0) {
      return 0;
    } else if (total < inputArray[i]) {
      return subSetsNonDP(inputArray, total, i - 1);
    } else {
      return subSetsNonDP(inputArray, total - inputArray[i], i - 1) + subSetsNonDP(inputArray, total, i - 1);
    }
  }

  /**
   * Find subarray with given sum (Nonnegative Numbers)
   * Given an unsorted array of non-negative integers, find a continuous subarray which adds to a given number.
   *
   * Examples :
   *
   * https://www.geeksforgeeks.org/find-subarray-with-given-sum/
   *
   * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
   * Ouptut: Sum found between indexes 2 and 4
   *
   * Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
   * Ouptut: Sum found between indexes 1 and 4
   *
   * Input: arr[] = {1, 4}, sum = 0
   * Output: No subarray found
   */
  private static int subArrayEqualsToSum(int[] arr, int target) {
    int curr_sum = 0, left = 0, n = arr.length, possibleCombinations = 0;

    // Pick a starting point
    for (int i = 0; i < n; i++) {
      curr_sum += arr[i];

      while (curr_sum >= target) {

        if (curr_sum == target) {
          System.out.println("Target value " + target + " exists between index " + left + " and " + i);
          possibleCombinations++;
        }
        curr_sum -= arr[left++];
      }
    }

    if (possibleCombinations == 0) {
      System.out.println("No subarray found");
    }

    return possibleCombinations;
  }

  /*

  Given an unsorted array of integers, find a subarray which adds to a given number.
  If there are more than one subarrays with sum as the given number, print any of them.

  Examples:

  Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
  Ouptut: Sum found between indexes 2 and 4

  Input: arr[] = {10, 2, -2, -20, 10}, sum = -10
  Ouptut: Sum found between indexes 0 to 3

  Input: arr[] = {-10, 0, 2, -2, -20, 10}, sum = 20
  Ouptut: No subarray with given sum exists

  https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
   */
  private static int subArrayEqualsToSumWithNegativeNumbers(int[] arr, int sum) {
    Map<Integer, Integer> mapValues = new HashMap<>();

    int curr_sum = 0;

    for (int i = 0; i < arr.length; i++) {
      curr_sum += arr[i];

      if (curr_sum == sum) {
        System.out.println("sum found between 0 to " + i);
        return 1;
      }

      if (mapValues.containsKey(curr_sum - sum)) {
        System.out.println("sum found between " + mapValues.get(curr_sum - sum) + " to " + i);
        return 1;
      }
      mapValues.put(curr_sum, i + 1);
    }
    return 0;
  }
}
