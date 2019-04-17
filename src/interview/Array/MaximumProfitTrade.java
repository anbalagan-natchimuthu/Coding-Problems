package interview.Array;

/**
 * In a daily share trading, a buyer buys shares in the morning and sells it on same day. If the trader is allowed to
 * make at most 2 transactions in a day, where as second transaction can only start after first one is complete
 * (Sell->buy->sell->buy). Given stock prices throughout day, find out maximum profit that a share trader could have
 * made.
 *
 * Examples:
 * Input:   price[] = {10, 22, 5, 75, 65, 80}
 * Output:  87 Trader earns 87 as sum of 12 and 75 Buy at price 10, sell at 22, buy at 5 and sell at 80
 *
 * Input:   price[] = {2, 30, 15, 10, 8, 25, 80}
 * Output:  100 Trader earns 100 as sum of 28 and 72 Buy at price 2, sell at 30, buy at 8 and sell at 80
 *
 * https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-twice/
 */
public class MaximumProfitTrade {

  // Returns maximum profit with two transactions on a given
  // list of stock prices, price[0..n-1]
  static int maxProfitWithTwoTransaction(int price[], int n) {
    // Create profit array and initialize it as 0
    int profit[] = new int[n];

        /* Get the maximum profit with only one transaction
           allowed. After this loop, profit[i] contains maximum
           profit from price[i..n-1] using at most one trans. */
    int max_price = price[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      // max_price has maximum of price[i..n-1]
      if (price[i] > max_price) {
        max_price = price[i];
      }

      // we can get profit[i] by taking maximum of:
      // a) previous maximum, i.e., profit[i+1]
      // b) profit by buying at price[i] and selling at
      //    max_price
      profit[i] = Math.max(profit[i + 1], max_price - price[i]);
    }

        /* Get the maximum profit with two transactions allowed
           After this loop, profit[n-1] contains the result */
    int min_price = price[0];
    for (int i = 1; i < n; i++) {
      // min_price is minimum price in price[0..i]
      if (price[i] < min_price) {
        min_price = price[i];
      }

      // Maximum profit is maximum of:
      // a) previous maximum, i.e., profit[i-1]
      // b) (Buy, Sell) at (min_price, price[i]) and add
      //    profit of other trans. stored in profit[i]
      profit[i] = Math.max(profit[i - 1], profit[i] + (price[i] - min_price));
    }
    return profit[n - 1];
  }

  /**
   * In share trading, a buyer buys shares and sells on future date. Given stock price of n days,
   * the trader is allowed to make at most k transactions, where new transaction can only start after previous
   * transaction is complete, find out maximum profit that a share trader could have made.

   Input:
   Price = [12, 14, 17, 10, 14, 13, 12, 15]
   K = 3
   Output:  12
   Trader earns 12 as sum of 5, 4 and 3
   Buy at price 12, sell at 17, buy at 10 and sell at 14 and buy at 12 and sell at 15
   */

  /**
   * Function to find out maximum profit by buying & selling/ a share atmost k times given stock price of n days
   * <p>
   * Time complexity of above solution is O(kn) and space complexity is O(nk). Space complexity can further be reduced
   * to O(n) as we uses the result from last transaction. But to make the article easily readable, we have used O(kn)
   * space.
   * <p>
   * https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/
   * https://www.youtube.com/watch?v=oDhu5uGq_ic
   */

  static int maxProfitWithKTransaction(int price[], int k) {

    // table to store results of subproblems
    // profit[t][i] stores maximum profit
    // using atmost t transactions up to day
    // i (including day i)
    int profit[][] = new int[k + 1][price.length];

    // fill the table in bottom-up fashion
    for (int i = 1; i < profit.length; i++) {
      int maxDiff = -price[0];
      for (int j = 1; j < profit[0].length; j++) {
        profit[i][j] = Math.max(profit[i][j - 1], price[j] + maxDiff);
        maxDiff = Math.max(maxDiff, profit[i - 1][j] - price[j]);
      }
    }

    return profit[k][price.length - 1];
  }

  static int maxProfitWithSlowSolution(int[] price, int k) {

    // table to store results of subproblems
    // profit[t][i] stores maximum profit using atmost t transactions up to day i (including day i)
    int[][] profit = new int[k + 1][price.length];

    // fill the table in bottom-up fashion
    for (int i = 1; i < profit.length; i++) {
      for (int j = 1; j < profit[0].length; j++) {
        int max_so_far = 0;

        // Maximum you earn by completing transaction on jth day
        for (int m = 0; m < j; m++) {
          max_so_far = Math.max(max_so_far, price[j] - price[m] + profit[i - 1][m]);
        }

        // Not doing transaction on jth day
        profit[i][j] = Math.max(profit[i][j - 1], max_so_far);
      }
    }

    return profit[k][price.length - 1];
  }

  public static void main(String args[]) {
    int price[] = {2, 30, 15, 90, 8, 25, 80};
    int n = price.length;
    System.out.println("Maximum Profit = " + maxProfitWithTwoTransaction(price, n));

    int k = 3;
    int price1[] = {12, 14, 17, 10, 14, 13, 12, 15};

    System.out.println("Maximum profit is: " + maxProfitWithKTransaction(price1, k));

    int[] price2 = {12, 14, 17, 10, 14, 13, 12, 15};
    System.out.println("Maximum profit is: " + maxProfitWithSlowSolution(price2, k));
  }
}
