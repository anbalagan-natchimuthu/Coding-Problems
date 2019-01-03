package interview.Array;

import java.util.PriorityQueue;

// Sort Transformed Array
//     // https://leetcode.com/problems/sort-transformed-array/
//Given a sorted array of integers nums and integer values a, b and c.
// Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.
//The returned array must be in sorted order.
//Expected time complexity: O(n)
//     nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
//     Result: [3, 9, 15, 33]
//     nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
//     Result: [-23, -5, 1, 7]
public class SortSquaredSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[] {-4, -2, 2, 4};
        int[] res = sortTransformedArray(nums, 1, 3, 5);
        for (int val : res) {
            System.out.print(val + " ");
        }
        System.out.println();
        nums = new int[] {-4, -2, 2, 4};
        res = sortTransformedArray(nums, -1, 3, 5);
        for (int val : res) {
            System.out.print(val + " ");
        }
    }

   private static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            PriorityQueue<Integer> priorityQueue =  new PriorityQueue<>();
            for (int d : nums) {
                priorityQueue.add(a * d * d + b * d + c);
            }
            int i = 0;
            while (priorityQueue.peek() != null) {
                nums[i++] = priorityQueue.remove();
            }
            return nums;
        }
    }