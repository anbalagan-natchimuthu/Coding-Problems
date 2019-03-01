package interview.Array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array and an integer k, find the maximum for each and every contiguous subarray of size k. Examples :
 *
 * Input : arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6} k = 3
 * Output : 3 3 4 5 5 5 6
 *
 * Input : arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13} k = 4
 * Output : 10 10 10 15 15 90 90
 *
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * https://www.youtube.com/watch?v=J6o_Wz-UGvc
 *
 * https://www.programcreek.com/2014/05/leetcode-sliding-window-maximum-java/
 *
 * https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 */
public class MaxSlidingWindow {

  // A Dequeue (Double ended queue) based method for printing maixmum element of
  // all subarrays of size k
  static void printMax(int arr[], int n, int k) {
    // Create a Double Ended Queue, Qi that will store indexes of array elements
    // The queue will store indexes of useful elements in every window and it will
    // maintain decreasing order of values from front to rear in Qi, i.e.,
    // arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
    Deque<Integer> Qi = new LinkedList<>();

    // Process rest of the elements, i.e., from arr[k] to arr[n-1]
    for (int i = 0; i < n; ++i) {

      // Remove the elements which are out of this window
      while ((!Qi.isEmpty()) && Qi.peek() <= i - k) {
        Qi.removeFirst();
      }

      // Remove all elements smaller than the currently
      // being added element (remove useless elements)
      while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()]) {
        Qi.removeLast();
      }

      // Add current element at the rear of Qi
      Qi.addLast(i);

      // The element at the front of the queue is the largest element of
      // previous window, so print it
      if (i + 1 >= k) {
        System.out.print(arr[Qi.peek()] + " ");
      }
    }
  }

  // Driver program to test above functions
  public static void main(String[] args) {
    int arr[] = {12, 1, 78, 90, 57, 89, 56};
    int k = 3;
    printMax(arr, arr.length, k);
  }
}
