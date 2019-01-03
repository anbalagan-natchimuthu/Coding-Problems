package interview.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/
 *
 * Given two binary arrays arr1[] and arr2[] of same size n. Find length of the longest common span (i, j)
 * where j >= i such that arr1[i] + arr1[i+1] + …. + arr1[j] = arr2[i] + arr2[i+1] + …. + arr2[j].

 Expected time complexity is Θ(n).

 Examples :

 Input: arr1[] = {0, 1, 0, 0, 0, 0};
        arr2[] = {1, 0, 1, 0, 0, 1};
 Output: 4
 The longest span with same sum is from index 1 to 4.

 Input: arr1[] = {0, 1, 0, 1, 1, 1, 1};
        arr2[] = {1, 1, 1, 1, 1, 0, 1};
 Output: 6
 The longest span with same sum is from index 1 to 6.

 Input: arr1[] = {0, 0, 0};
        arr2[] = {1, 1, 1};
 Output: 0

 Input: arr1[] = {0, 0, 1, 0};
        arr2[] = {1, 1, 1, 1};
 Output: 1
 */
public class LongestSpanTwoBinaryArrays {
    static int arr1[] = new int[]{0, 1, 0, 1, 1, 1, 1};
    static int arr2[] = new int[]{1, 1, 1, 1, 1, 0, 1};

    // Returns length of the longest common sum in arr1[]
    // and arr2[]. Both are of same size n.
    static int longestCommonSum(int n)
    {
        // Initialize result
        int maxLen = 0;

        // Initialize prefix sums of two arrays
        int preSum1 = 0, preSum2 = 0;

        // Create an array to store staring and ending
        // indexes of all possible diff values. diff[i]
        // would store starting and ending points for
        // difference "i-n"
        int diff[] = new int[2*n+1];

        // Initialize all starting and ending values as -1.
        for (int i = 0; i < diff.length; i++) {
            diff[i] = -1;
        }

        // Traverse both arrays
        for (int i=0; i<n; i++)
        {
            // Update prefix sums
            preSum1 += arr1[i];
            preSum2 += arr2[i];

            // Comput current diff and index to be used
            // in diff array. Note that diff can be negative
            // and can have minimum value as -1.
            int curr_diff = preSum1 - preSum2;
            int diffIndex = n + curr_diff;

            // If current diff is 0, then there are same number
            // of 1's so far in both arrays, i.e., (i+1) is
            // maximum length.
            if (curr_diff == 0)
                maxLen = i+1;

                // If current diff is seen first time, then update
                // starting index of diff.
            else if ( diff[diffIndex] == -1)
                diff[diffIndex] = i;

                // Current diff is already seen
            else
            {
                // Find lenght of this same sum common span
                int len = i - diff[diffIndex];

                // Update max len if needed
                if (len > maxLen)
                    maxLen = len;
            }
        }
        return maxLen;
    }

    // Returns largest common subarray with equal
    // number of 0s and 1s in both of t
    static int longestCommonSum(int arr1[], int arr2[], int n)
    {
        // Find difference between the two
        int arr[] = new int[n];
        for (int i=0; i<n; i++)
            arr[i] = arr1[i] - arr2[i];

        // Creates an empty hashMap hM
        Map<Integer, Integer> integerMap = new HashMap<>();

        int sum = 0;     // Initialize sum of elements
        int max_len = 0; // Initialize result

        // Traverse through the given array
        for (int i = 0; i < n; i++)
        {
            // Add current element to sum
            sum += arr[i];

            // To handle sum=0 at last index
            if (sum == 0)
                max_len = i + 1;

            // If this sum is seen before,
            // then update max_len if required
            if (integerMap.containsKey(sum)) {
                max_len = Math.max(max_len, i - integerMap.get(sum));
            }  else {// Else put this sum in hash table
                integerMap.put(sum, i);
            }
        }

        return max_len;
    }

    // Driver method to test the above function
    public static void main(String[] args) {
        System.out.print("Length of the longest common span with same sum is ");
        System.out.println(longestCommonSum(arr1.length));
        System.out.println(longestCommonSum(arr1, arr2, arr1.length));
    }

}
