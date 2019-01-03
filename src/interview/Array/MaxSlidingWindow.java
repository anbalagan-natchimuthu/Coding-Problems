package interview.Array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.
 Examples :

 Input :
 arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
 k = 3
 Output :
 3 3 4 5 5 5 6

 Input :
 arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
 k = 4
 Output :
 10 10 10 15 15 90 90
 https://leetcode.com/problems/sliding-window-maximum/
 * https://www.youtube.com/watch?v=J6o_Wz-UGvc
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        int intput[] = { 9, 7, 6, 5, 4, 10, 8, 11 };
        for (int i : maxSlidingWindow(intput, 3)) {
            System.out.println(i);
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }

            // Index of the element
            deque.offer(i);

            if (i + 1 >= k) {
                result[i + 1 - k] = nums[deque.peek()];
            }
        }

        return result;
    }
}
