package interview.Array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/smallest-range-ii/
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i]
 * (only once).
 *
 * After this process, we have some array B.
 *
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 * Example 2:
 *
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 * Example 3:
 *
 * Input: A = [1,3,6], K = 3
 * Output: 3
 * Explanation: B = [4,6,3]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 */
public class smallestRangeII {

    public static void main(String[] args) {
        smallestRangeII sr = new smallestRangeII();
        System.out.println("smallestRange :" + sr.getSmallRange(new int[] { 1,3,6 }, 3));
        System.out.println("smallestRangeI :" + sr.smallestRangeI(new int[] { 1,3,6 }, 3));
    }

    public int getSmallRange(int[] inputArray, int k) {
        int len = inputArray.length;
        Arrays.sort(inputArray);
        int result = inputArray[len - 1] - inputArray[0];

        for (int i = 0; i < len - 1; i++) {
            int max = Math.max(inputArray[i] + k, inputArray[len - 1] - k);
            int min = Math.min(inputArray[i + 1] - k, inputArray[0] + k);
            result = Math.min(max - min, result);
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/smallest-range-i/solution/
     * Intuition and Algorithm
     *
     * Let A be the original array, and B be the array after all our modifications. Towards trying to minimize max(B)
     * - min(B), let's try to minimize max(B) and maximize min(B) separately.
     *
     * The smallest possible value of max(B) is max(A) - K, as the value max(A) cannot go lower. Similarly, the
     * largest possible value of min(B) is min(A) + K. So the quantity max(B) - min(B) is at least ans = (max(A) - K)
     * - (min(A) + K).
     *
     * We can attain this value (if ans >= 0), by the following modifications:
     *
     * If A[i] \leq \min(A) + KA[i]≤min(A)+K, then B[i] = \min(A) + KB[i]=min(A)+K
     * Else, if A[i] \geq \max(A) - KA[i]≥max(A)−K, then B[i] = \max(A) - KB[i]=max(A)−K
     * Else, B[i] = A[i]B[i]=A[i].
     * If ans < 0, the best answer we could have is ans = 0, also using the same modification.
     */
    public int smallestRangeI(int[] A, int K) {
        int min = A[0], max = A[0];
        for (int x: A) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return Math.max(0, max - min - 2*K);
    }
}
