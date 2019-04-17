package interview.Array;

import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 *
 * Example 1:
 * Input: [10,2]
 * Output: "210"
 *
 * Example 2:
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {

  public static String largestNumber(int[] nums) {

    String[] arr = new String[nums.length];

    for (int i = 0; i < nums.length; i++) {
      arr[i] = String.valueOf(nums[i]);
    }

    Arrays.sort(arr, (a, b) -> {
      String order1 = a + b;
      String order2 = b + a;
      return order2.compareTo(order1);
    });

    if (arr[0].equals("0")) {
      return "0";
    }

    String finalString = "";
    for (int i = 0; i < arr.length; i++) {
      finalString += arr[i];
    }
    return finalString;
  }

  public static void main(String[] args) {
    System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
  }
}
