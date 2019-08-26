package interview.Array;

/**
 * Given an array arr[] of n integers, find the maximum that maximizes sum of value of i*arr[i] where i varies from 0
 * to n-1.
 *
 * Examples :
 *
 * Input : arr[] = {8, 3, 1, 2}
 * Output : 29
 * Explanation : Let us see all rotations
 * {8, 3, 1, 2} = 8*0 + 3*1 + 1*2 + 2*3 = 11
 * {3, 1, 2, 8} = 3*0 + 1*1 + 2*2 + 8*3 = 29
 * {1, 2, 8, 3} = 1*0 + 2*1 + 8*2 + 3*3 = 27
 * {2, 8, 3, 1} = 2*0 + 8*1 + 3*2 + 1*1 = 17
 *
 * Input : arr[] = {3, 2, 1}
 * Output : 8
 *
 * https://www.geeksforgeeks.org/maximum-sum-iarri-among-rotations-given-array/
 */
public class Maxsum {

  static int maxSum(int arr[], int n) {
    // Compute sum of all array elements
    int cum_sum = 0;
    int productCurrVal = 0;

    for (int i = 0; i < n; i++) {
      cum_sum += arr[i];

      // Compute sum of i*arr[i] for initial configuration.
      productCurrVal += i * arr[i];
    }

    // Initialize result
    int res = productCurrVal;

    // Compute values for other iterations
    for (int i = 1; i < n; i++) {
      // Compute next value using previous value in O(1) time
      // Subtract cum_sum from productCurrVal and add previous arr[i] value to answer
      // since it was not considered in previous iteration.
      productCurrVal = productCurrVal - (cum_sum - arr[i - 1]) + arr[i - 1] * (n - 1);

      // Update result if required
      res = Math.max(res, productCurrVal);
    }

    return res;
  }

  // Driver code
  public static void main(String[] args) {
    int arr[] = {8, 3, 9, 2};
    int n = arr.length;
    System.out.println(maxSum(arr, n));
  }
}
