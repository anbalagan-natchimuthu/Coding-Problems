package interview.Strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] input = { 2, 7, 11, 15 };
        two(input, 17);
        // findPair(input, 18);
        // int[] input1 = { -9, 12, 3, 11, 15 };
        // findThreeSum(input1);
        // twoSum(input, 9);
        // getTwoSumIndexes(input, 9);
        // int[] inputs = { -1, 2, 1, -4 };
        // int result = threeSumClosest(inputs, 1);
        // System.out.println(result);
    }

    private static int findPair(int[] inputArr, int target) {
        Arrays.sort(inputArr);
        int j = inputArr.length - 1;
        int i = 0;
        int sum = 0;

        while (i <= j) {
            sum = inputArr[i] + inputArr[j];
            if (sum == target) {
                System.out.println("Number1 " + inputArr[i] + " Number2 " + inputArr[j]);
                return sum;
            } else if (sum > target) {
                j--;
                sum = 0;
            } else if (sum < target) {
                i++;
                sum = 0;
            }
        }
        return 0;
    }

    // a + b + c= 0
    // a+ b = -c

    //{ }
    private static void findThreeSum(int[] inputArr) {
        int j = 1;

        for (int i = 0; i < inputArr.length; i++) {
            int size = inputArr.length - j;
            if (Math.abs(inputArr[i]) == findPair((partArray(inputArr, size, j)), Math.abs(inputArr[i]))) {
                System.out.println("3rd elemt " + inputArr[i]);
                return;
            }
            j++;
        }
    }

    private static int[] partArray(int[] array, int size, int j) {
        int[] part = new int[size];
        System.arraycopy(array, j, part, 0, size);
        return part;
    }

    // a+ b + c = target
    private static void threeSum(int[] inputArr, int target) {

    }

    private static void twoSum(int[] inputArr, int target) {
        int j = inputArr.length - 1;
        int sum = 0;
        int i = 0;
        while (i <= j) {
            sum = inputArr[i] + inputArr[j];
            if (sum > target) {
                j--;
                sum = 0;
            } else if (sum < target) {
                i++;
                sum = 0;
            } else if (sum == target) {
                System.out.println("Target found at index" + i + ", " + j);
                break;
            }
        }
    }

    private static void getTwoSumIndexes(int[] inputArr, int target) {

        // check for not found
        // size 0

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inputArr.length; i++) {
            if (map.containsKey(inputArr[i])) {
                int index1 = (map.get(inputArr[i]));
                int index2 = i;
                System.out.println("index1 " + index1 + "index2 " + index2);
            } else {
                map.put(target - inputArr[i], i);
            }
        }
    }

    public static int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(sum - target);

                if (diff == 0) {
                    return sum;
                }

                if (diff < min) {
                    min = diff;
                    result = sum;
                }
                if (sum <= target) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return result;
    }

    private static void two(int[] nums, int target) {

        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (!targetMap.containsKey(nums[i])) {
                targetMap.put(target - nums[i], nums[i]);
            } else {
                Integer value = targetMap.get(nums[i]);
                System.out.println("Target " + value + " " + nums[i]);
            }
        }
    }
}


