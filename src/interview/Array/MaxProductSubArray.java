package interview.Array;

// Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which
// has the largest product.
//
//     Example 1:
//
//     Input: [2,3,-2,4]
//     Output: 6
//     Explanation: [2,3] has the largest product 6.
//     Example 2:
//
//     Input: [-2,0,-1]
//     Output: 0
//     Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

public class MaxProductSubArray {

    public static void main(String[] args) {

        int[] arr = { 2,3,-2,-6, 2 };
        int result = maxProduct(arr);
        System.out.println("Result " + result);
    }

    /**
     * Loop through the array, each time remember the max and min value for the previous product,
     * the most important thing is to update the max and min value: we have to compare among max * A[i], min * A[i] as well as A[i],
     * since this is product, a negative * negative could be positive.
     *
     */
    public static int maxProduct(int A[]) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }
}
