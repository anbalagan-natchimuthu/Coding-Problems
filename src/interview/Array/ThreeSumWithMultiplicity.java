package interview.Array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-with-multiplicity/
 *
 * Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i]
 * + A[j] + A[k] == target.
 *
 * As the answer can be very large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (A[i], A[j], A[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 */
public class ThreeSumWithMultiplicity {

  public static void main(String[] args) {
    System.out.println(threeSumMulti(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8));
  }

  public static int threeSumMulti(int[] A, int target) {

    int mod = 1_000_000_007;
    Arrays.sort(A);

    int j, k, tempTarget;

    int ans = 0;

    for (int i = 0; i < A.length - 2; i++) {
      j = i + 1;
      k = A.length - 1;
      tempTarget = target - A[i];

      while (j < k) {
        if (A[j] + A[k] < tempTarget) {
          j++;
        } else if (A[j] + A[k] > tempTarget) {
          k--;
        }
        // A[j] + A[k] == tempTarget. However, Need to check if j ==k or not
        else if (A[j] != A[k]) {
          /* Whenever A[j] + A[k] == T, we should count the multiplicity of A[j] and A[k].
          In this example [2,2,2,2,3,3,4,4,4,5,5,5,6,6], if A[j] == 2 and A[k] == 6, the multiplicities are 4 and 2,
          and the total number of pairs is 4 * 2 = 8
            */
          int lo = 1, hi = 1;
          while (j + 1 < k && A[j] == A[j + 1]) {
            lo++;
            j++;
          }

          while (k - 1 > j && A[k] == A[k - 1]) {
            hi++;
            k--;
          }

          // if j !=k then multiply frequencies of j and K
          ans += lo * hi;
          ans %= mod;
          j++;
          k--;
        } else {
          /*
          As a special case, if A[j] == A[k], then our manner of counting would be incorrect.
          If for example the remaining window is [4,4,4], there are only 3 such pairs.
          In general, when A[j] == A[k], we have M*(M-1)/2 pairs (j,k)
           */
          ans += ((k - j + 1) * (k - j) / 2);
          ans %= mod;
          break;
        }
      }
    }

    return ans;
  }
}
