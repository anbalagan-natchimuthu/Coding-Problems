package interview.DP;

/**
 * https://www.youtube.com/watch?v=lBRtnuxg-gU&index=21&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
 *https://www.geeksforgeeks.org/min-cost-path-dp-6/
 *
 * Given a cost matrix cost[][] and a position (m, n) in cost[][],
 * write a function that returns cost of minimum cost path to reach (m, n) from (0, 0).
 * Each cell of the matrix represents a cost to traverse through that cell. Total cost of a path to reach (m, n) is
 * sum of all the costs on that path (including both source and destination). You can only traverse down, right and diagonally
 * lower cells from a given cell, i.e., from a given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed.
 * You may assume that all costs are positive integers.
 */
public class MinCostPath {

    public static void main(String[] args) {
        int cost[][]= {{1, 2, 3},
            {4, 8, 2},
            {1, 5, 3}};
        System.out.println(minCost(cost,2,2));
    }

    private static int minCost(int[][] cost, int m, int n) {
        int rowLength = cost.length;
        int columnLength = cost[0].length;
        int[][] table = new int[rowLength][columnLength];

        table[0][0] = cost[0][0];

        /* Initialize first column of total cost(table) array */
        for (int i = 1; i < columnLength; i++) {
            table[0][i] = cost[0][i] + table[0][i-1];
        }

        /* Initialize first row of table array */
        for (int i = 1; i < rowLength; i++) {
            table[i][0] = cost[i][0] + table[i-1][0];
        }

        /* Construct rest of the table array */
        for (int i =1; i <rowLength; i++) {
            for (int j=1; j<columnLength; j++) {
                table[i][j] = cost[i][j] + Math.min(Math.min(table[i-1][j-1], table[i-1][j]), table[i][j-1]);
            }
        }

        return table[m][n];
    }
}
