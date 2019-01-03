package interview.Strings;

import java.util.HashMap;
import java.util.Map;

public class WindowOfMaxSumInArray {

    public static void main(String[] args) {

        int[] arr = new int[] { 1, 2, 3, 14, -60, 7, 10, -20, 8, -2 };

        findMaxWindow(arr);
    }

    private static void findMaxWindow(int[] arr) {

        int maxSum = 0;
        int startIndex = 0;
        int endIndex = 0;
        int sum = 0;
        Map<Integer, String> map = new HashMap<>();
        StringBuilder sbr;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum > maxSum) {
                maxSum = sum;
                endIndex = i;
                sbr = new StringBuilder();
                map.put(maxSum, (sbr.append(startIndex).append(":").append(endIndex)).toString());
            }

            if (sum < 0) {
                sum = 0;
                startIndex = i + 1;
                endIndex = Integer.MAX_VALUE;
            }
        }

        int mapMaxKey = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if (key >= mapMaxKey) {
                mapMaxKey = key;
            }
        }
        System.out.println(mapMaxKey);
        System.out.println(map.get(mapMaxKey));
        // for (int i = startIndex; i <= endIndex; i++) {
        //     System.out.println(arr[i]);
        // }
    }
}
