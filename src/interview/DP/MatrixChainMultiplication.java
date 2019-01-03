package interview.DP;

/**
 * Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of dimension p[i-1] x p[i].
 * We need to write a function MatrixChainOrder() that should return the minimum number of multiplications needed to multiply the chain.
 *
 * Input: p[] = {40, 20, 30, 10, 30}
 *   Output: 26000
 *   There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
 *   Let the input 4 matrices be A, B, C and D.  The minimum number of
 *   multiplications are obtained by putting parenthesis in following way
 *   (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
 *
 * https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
 * https://www.youtube.com/watch?v=vgLJZMUfnsU
 */
public class MatrixChainMultiplication {

    // With One dimensional Array
    public int findCost(int arr[]) {
        int temp[][] = new int[arr.length][arr.length];
        int q = 0;
        // cost is zero when multiplying one matrix. So, calculate length from 2
        for (int length = 2; length < arr.length; length++) {
            for (int i = 0; i < arr.length - length; i++) {
                int j = i + length;
                temp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    q = temp[i][k] + temp[k][j] + arr[i] * arr[k] * arr[j];
                    if (q < temp[i][j]) {
                        temp[i][j] = q;
                    }
                }
            }
        }
        return temp[0][arr.length - 1];
    }

    public static void main(String args[]) {
        MatrixChainMultiplication mmc = new MatrixChainMultiplication();
        int arr[] = { 4, 2, 3, 5, 3 };
        int cost = mmc.findCost(arr);
        System.out.println(cost);
    }
}
