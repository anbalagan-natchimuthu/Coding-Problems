package interview.DP;

/**
 * Given an array of integers, nums and a target value T, find the
 number of ways that you can add and subtract the values in
 nums to add up to T.
 eg.
 nums = {1, 1, 1, 1, 1}
 T = 3
 1 + 1 + 1 + 1 - 1
 1 + 1 + 1 - 1 + 1
 1 + 1 - 1 + 1 + 1
 1 - 1 + 1 + 1 + 1
 -1 + 1 + 1 + 1 + 1
 targetSum(nums, T) = 5
 */
public class TargetSum {

    // Naive brute force solution. Find every combo
    public int targetSum(int[] nums, int T) {
        return targetSum(nums, T, 0, 0);
    }

    // Overloaded recursive function
    private int targetSum(int[] nums, int T, int i, int sum) {
        // When we've gone through every item, see if we've reached our target sum
        if (i == nums.length) {
            return sum == T ? 1 : 0;
        }

        // Combine the possibilities by adding and subtracting the current value
        return targetSum(nums, T, i+1, sum + nums[i])
            + targetSum(nums, T, i+1, sum - nums[i]);
    }

    public int targetSumDP(int[] nums, int T) {
        int sum = 0;
        // Our cache has to range from -sum(nums) to
        // sum(nums), so we offset everything by sum
        for (int num : nums) sum += num;
        int[][] cache =
            new int[nums.length + 1][2*sum + 1];
        if (sum == 0) return 0;
        // Initialize i=0, T=0
        cache[0][sum] = 1;
        // Iterate over previous row and update the
        // current row
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                int prev = cache[i-1][j];
                if (prev != 0) {
                    cache[i][j - nums[i-1]] += prev;
                    cache[i][j + nums[i-1]] += prev;
                }
            }
        }
        return cache[nums.length][sum + T];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int targetSum =3;

        TargetSum ts = new TargetSum();
        System.out.println(ts.targetSum(nums, targetSum));
        System.out.println(ts.targetSumDP(nums, targetSum));
    }
}
