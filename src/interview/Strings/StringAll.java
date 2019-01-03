package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringAll {

    public static void main(String[] args) {
        boolean value = isPalindrome("abc,.@cba!!!");
        System.out.println("isPalindrome " + value);
    }

    private static boolean isPalindrome(String input) {
        char[] inputArr = input.toCharArray();
        int i = 0;
        int j = inputArr.length - 1;
        while (i <= j) {
            if (!(inputArr[i] >= 'a' && inputArr[i] <= 'z')) {
                i++;
            }

            if (!(inputArr[j] >= 'a' && inputArr[j] <= 'z')) {
                j--;
            }

            if ((inputArr[i] >= 'a' && inputArr[i] <= 'z') && (inputArr[j] >= 'a' && inputArr[j] <= 'z')) {
                if (!(inputArr[i] == inputArr[j])) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    private static void groupTargetSum(int[] arr, int target) {
        int[] inpi = { 1, 2, 3, 5, 4, 9, 11, 1 };
        Map<Integer, List<Integer>> indexCounterMap = new HashMap<>();
        List<Integer> indexLst = new ArrayList<>();
        for (int i = 0; i < inpi.length; i++) {
            if (indexCounterMap.containsKey(inpi[i])) {
                indexLst = indexCounterMap.get(inpi[i]);
            }
            indexLst.add(i);
            indexCounterMap.put(inpi[i], indexLst);
        }
        //1,1,2,3,4,5,9,11
        Arrays.sort(inpi);
        int sum = 0;
        int j = 0;
        int k = inpi.length - 1;
        while (j <= k) {
            sum = inpi[j] + inpi[k];
            if (sum == target) {

            } else if (sum > target) {
                k--;
                sum = 0;
            } else if (sum < target) {
                j++;
                sum = 0;
            }
        }
    }
}