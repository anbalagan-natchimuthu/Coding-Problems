package interview.DP;

/**
 Given a list of items with values and weights, as well as a max
 weight, find the maximum value you can generate from items,
 where the sum of the weights is less than or equal to the max.
 eg.
 items = {(w:2, v:6), (w:2, v:10), (w:3, v:12)}
 max weight = 5
 knapsack(items, max weight) = 22
 */
public class KnapsackProblem {

    public static class Item {
        int weight;
        int value;
        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    // Naive brute force solution. Recursively include or exclude each item to try every possible combination
    public static int knapsackBruteForce(Item[] items, int W) {
        return knapsack(items, W, 0);
    }

    // Overloaded recursive function
    private static int knapsack(Item[] items, int W, int i) {
        // If we've gone through all the items,
        // return
        if (i == items.length) {
            return 0;
        }
        // If the item is too big to fill the
        // remaining space, skip it
        if (W - items[i].weight < 0) {
            return knapsack(items, W, i + 1);
        }

        // Find the maximum of including and not
        // including the current item
        return Math.max(
            knapsack(items, W - items[i].weight, i + 1)
                + items[i].value,
            knapsack(items, W, i + 1));
    }

    /**
     * https://www.youtube.com/watch?v=8LusJS5-AGo
     */
    public static int knapsackDPBottomUp(Item[] items, int W) {
        int K[][] = new int[items.length + 1][W + 1];

        for (int i = 0; i <= items.length; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    K[i][j] = 0;
                    continue;
                }
                if (j - items[i - 1].weight >= 0) {
                    K[i][j] = Math.max(K[i - 1][j], K[i - 1][j - items[i - 1].weight] + items[i - 1].value);
                } else {
                    K[i][j] = K[i - 1][j];
                }
            }
        }
        return K[items.length][W];
    }

    public static void main(String[] args) {
        Item[] items = new Item[3];
        items[0] = new Item(2, 6);
        items[1] = new Item(2, 10);
        items[2] = new Item(3, 12);
        System.out.println("knapsackBruteForce: " + knapsackBruteForce(items, 5));
        System.out.println("knapsackDPBottomUp: " + knapsackDPBottomUp(items, 5));
    }
}
