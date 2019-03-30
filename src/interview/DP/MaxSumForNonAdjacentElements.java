package interview.DP;

/**
 * Find maximum sum for non adjacent elements.
 * https://leetcode.com/problems/house-robber/
 * https://leetcode.com/problems/house-robber-ii/
 *
 * https://www.youtube.com/watch?v=UtGtF6nc35g
 */
public class MaxSumForNonAdjacentElements {

  public static void main(String[] args) {
    int arr[] = {2, 10, 13, 4, 2, 15, 10};
    System.out.println(maxSum(arr));

    System.out.println(maxSumInRound(new int[]{1, 2, 3, 1}));
  }

  /**
   * PROBLEM: 1
   */
  public static int maxSum(int[] arr) {
    int inclusive = arr[0];
    int exclusive = 0, temp;
    for (int i = 1; i < arr.length; i++) {
      temp = inclusive;
      inclusive = Math.max(inclusive, exclusive + arr[i]);
      exclusive = temp;
    }

    return inclusive;
  }

  /**
   * PROBLEM: 2
   * Find maximum sum from left to right ignoring first element.
   * Find maximum sum from right to left ignoring last element.
   * Maximum of two will be the answer. It guarantees that both first and last element
   * will be not selected together.
   */
  public static int maxSumInRound(int[] arr) {
    int inclusive = arr[1];
    int exclusive = 0, temp;
    for (int i = 2; i < arr.length; i++) {
      temp = inclusive;
      inclusive = Math.max(inclusive, exclusive + arr[i]);
      exclusive = temp;
    }

    int inclusive1 = arr[arr.length - 2];
    int exclusive1 = 0;
    for (int i = arr.length - 3; i >= 0; i--) {
      temp = inclusive1;
      inclusive1 = Math.max(inclusive1, exclusive1 + arr[i]);
      exclusive1 = temp;
    }

    return Math.max(inclusive, inclusive1);
  }
}
