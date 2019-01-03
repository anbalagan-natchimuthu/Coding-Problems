package interview.DP;

import java.util.Arrays;
import java.util.HashMap;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * Refer https://www.youtube.com/watch?v=jaNZ83Q3QGc
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = { 9, 6, 5, 1 };
        System.out.println("Number of Combinations: " + change(coins, 11));

        System.out.println("changeAllPermutations:" + changeAllPermutations(new int[] { 9, 6, 5, 1 }, 11));

        HashMap<String, Integer> memo = new HashMap<>();
        System.out
            .println("changeUniquePermutations:" + changeUniquePermutations(new double[] { 9, 6, 5, 1 }, 11, 0, memo));

        System.out
            .println("changeUniquePermutations:" + changeUniquePermutations(new double[] { 1.00, 0.25 }, 2.00, 0, memo));

        System.out.println(" Minimum coins DP Bottom-Up Approach: " + minCoinsBottmUpApproach(new int[]{9, 6, 5, 1}, 11));

        System.out.println(" Minimum coins DP Top-Down Approach: " + minCoinsTopDownApproach(new int[]{9, 6, 5, 1}, 11));
    }

    private static int change(int[] coins, int totalAmount) {
        int[] combinations = new int[totalAmount + 1];
        combinations[0] = 1;

        for (int coin : coins) {
            for (int amount = 1; amount < combinations.length; amount++) {
                if (amount >= coin) {
                    combinations[amount] += combinations[amount - coin];
                }
            }
        }
        System.out.println(Arrays.toString(combinations));
        return combinations[totalAmount];
    }

    /**
     * This function will return all possible permutations and combinations for the given amount
     * e.g. for coins {1, 2} and amount 4, this will return redundant combinations like  { {1,2,1}, {1, 1, 2}, {2, 1, 1}
     * @param coins
     * @param totalAmount
     * @return
     * https://www.youtube.com/watch?v=k4y5Pr0YVhg.
     */
    private static int changeAllPermutations(int[] coins, int totalAmount) {
        if (totalAmount == 0) {
            return 1;
        } else if (totalAmount < 0) {
            return 0;
        }

        int nCombos = 0;
        for (int coin : coins) {
            nCombos += changeAllPermutations(coins, totalAmount - coin);
        }
        return nCombos;
    }

    /**
     * This function will return only unique permutations and combinations for the given amount
     * e.g. for coins {1, 2} and amount 4, this will not return redundant combinations of  { {1,2,1}, {1, 1, 2}, {2,
     * 1, 1}
     * @param coins
     * @param totalAmount
     * @return
     */
    private static int changeUniquePermutations(double[] coins, double totalAmount,  int currentCoin, HashMap<String, Integer> memo) {

        if (totalAmount == 0) {
            return 1;
        } else if (totalAmount < 0) {
            return 0;
        }

        String key = totalAmount + "-" + currentCoin;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int nCombos = 0;
        for (int coin = currentCoin; coin < coins.length; coin++) {
            nCombos += changeUniquePermutations(coins, totalAmount - coins[coin], coin, memo);
        }
        memo.put(key, nCombos);
        return nCombos;
    }

    /**
     * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
     * Given a value V, if we want to make change for V Rs, and we have infinite supply of each of the denominations
     * in Indian currency, i.e., we have infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes,
     * what is the minimum number of coins and/or notes needed to make the change?

     Examples:

     Input: V = 70
     Output: 2
     We need a 50 Rs note and a 20 Rs note.

     Input: V = 121
     Output: 3
     We need a 100 Rs note, a 20 Rs note and a
     1 Rs coin.
     */

    // m is size of coins array (number of different coins)
    static int minCoinsBottmUpApproach(int coins[], int totalAmount) {
        // table[i] will be storing the minimum number of coins
        // required for i value. So table[totalAmount] will have result
        int cache[] = new int[totalAmount + 1];

        // Base case (If given value totalAmount is 0)
        cache[0] = 0;

        // Initialize all table values as Infinite
        for (int i = 1; i <= totalAmount; i++) {
            cache[i] = Integer.MAX_VALUE;
        }

        // Go through all coins
        for (int coin : coins) {
            // Compute minimum coins required for all values from 1 to V
            for (int amount = 1; amount <= totalAmount; amount++) {
                if (amount >= coin) {
                    int sub_res = cache[amount - coin];
                    if (sub_res != Integer.MAX_VALUE
                        && sub_res + 1 < cache[amount]) {
                        cache[amount] = sub_res + 1;
                    }
                }
            }
        }
        return cache[totalAmount];
    }

    public static int minCoinsTopDownApproach(int coins[], int c) {
        // Initialize cache with values as -1
        int[] cache = new int[c + 1];
        for (int i = 1; i < c + 1; i++)
            cache[i] = -1;
        return makeChange(coins, c, cache);
    }
    // Overloaded recursive function
    private static int makeChange(int coins[], int c, int[] cache) {
        // Return the value if it’s in the cache
        if (cache[c] >= 0) return cache[c];

        int minCoins = Integer.MAX_VALUE;

        // Find the best coin
        for (int coin : coins) {
            if (c - coin >= 0) {
                int currMinCoins =
                    makeChange(coins, c - coin, cache);
                if (currMinCoins < minCoins)
                    minCoins = currMinCoins;
            }
        }

        // Save the value into the cache
        cache[c] = minCoins + 1;
        return cache[c];
}
}