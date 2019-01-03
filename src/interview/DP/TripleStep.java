package interview.DP;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs.
 */
public class TripleStep {
    public static void main(String[] args) {
        int n = 3;
        int[] memo = new int[n + 1];
        System.out.println(findWays(n, memo));
    }

    private static int findWays(int number, int[] memo) {
        if (number == 0) {
            return 1;
        } else if (number < 0) {
            return 0;
        } else if (memo[number] > 0) {
            return memo[number];
        } else {
            memo[number] = findWays(number - 1, memo) + findWays(number - 2, memo) + findWays(number - 3, memo);
            return memo[number];
        }
    }
}
