package interview.misc;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * PROBLEM: 1
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 *
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageDataStream {

  private int[] window;
  private int n, insert;
  private long sum;

  /**
   * Initialize your data structure here.
   */
  public MovingAverageDataStream(int size) {
    window = new int[size];
    insert = 0;
    sum = 0;
  }

  public double next(int val) {
    if (n < window.length) {
      n++;
    }
    sum -= window[insert];
    sum += val;
    window[insert] = val;
    insert = (insert + 1) % window.length;

    return (double) sum / n;
  }

  /**
   * PROBLEM: 2
   *
   * https://leetcode.com/problems/sliding-window-median/
   *
   * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
   * very right. You can only see the k numbers in the window. Each time the sliding window moves right by one
   * position. Your job is to output the median array for each window in the original array.
   *
   * For example,
   * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
   *
   * Window position                Median
   * ---------------               -----
   * [1  3  -1] -3  5  3  6  7       1
   *  1 [3  -1  -3] 5  3  6  7       -1
   *  1  3 [-1  -3  5] 3  6  7       -1
   *  1  3  -1 [-3  5  3] 6  7       3
   *  1  3  -1  -3 [5  3  6] 7       5
   *  1  3  -1  -3  5 [3  6  7]      6
   * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
   */

  public static double[] medianSlidingWindow(int[] nums, int k) {
    double[] result = new double[nums.length - k + 1];
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for(int i = 0; i < nums.length; i++) {
      if(maxHeap.size() <= minHeap.size()) {
        minHeap.add(nums[i]);
        maxHeap.add(minHeap.remove());
      } else {
        maxHeap.add(nums[i]);
        minHeap.add(maxHeap.remove());
      }


      if(maxHeap.size() + minHeap.size() == k) {
        double median;
        if(maxHeap.size() == minHeap.size()) {
          median = (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
          median = maxHeap.peek();
        }

        int start = i - k + 1;
        result[start] = median;
        if(!maxHeap.remove(nums[start])) {
          minHeap.remove(nums[start]);
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(medianSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));
    System.out.println(Arrays.toString(medianSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 4)));
  }
}
