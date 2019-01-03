package interview.DP;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 212438472 on 3/22/18.
 */
public class Memoization {

    public static void main(String[] args) {
        Memoization memoization = new Memoization();
        //0, 1, 1, 2, 3, 5, 8, 13
        System.out.println(memoization.calculateFibonacci(8, new HashMap<>()));
        System.out.println(memoization.getFibWithMem(8));
    }

    private int calculateFibonacci(int inputValue, Map<Integer, Integer> map) {
        if (inputValue <= 0) {
            return 0;
        } else if (inputValue == 1) {
            return 1;
        } else if (map.containsKey(inputValue)) {
            return map.get(inputValue);
        }
        Integer fibForN = calculateFibonacci(inputValue - 1, map) + calculateFibonacci(inputValue - 2, map);
        map.put(inputValue, fibForN);
        return fibForN;
    }

    public int getFibWithMem(int n) {
        int[] memo = new int[n + 1];
        return calculateFibWithMem(n, memo);
    }

    private int calculateFibWithMem(int n, int[] memo) {
        // number wasn't calculated yet.
        if (memo[n] > 0) {
            return memo[n];
        }
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return memo[n] = calculateFibWithMem(n - 1, memo) + calculateFibWithMem(n - 2, memo);
        }
    }
}
