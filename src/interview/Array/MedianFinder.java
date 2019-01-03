package interview.Array;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://practice.geeksforgeeks.org/problems/find-median-in-a-stream/0
 *
 * Given an input stream of n integers the task is to insert integers to stream and print the median of the new
 * stream formed by each insertion of x to the stream.
 *
 * Example
 *
 * Flow in stream : 5, 15, 1, 3
 * 5 goes to stream --> median 5 (5)
 * 15 goes to stream --> median 10 (5, 15)
 * 1 goes to stream --> median 5 (5, 15, 1)
 * 3 goes to stream --> median 4 (5, 15, 1, 3)
 *
 * https://leetcode.com/problems/find-median-from-data-stream/discuss/201815/My-Two-PriorityQueue-Java-Solution-O(logN)
 */
class MedianFinder {
    //A max-heap to store the smaller half of the input numbers
    //A min-heap to store the larger half of the input numbers
    PriorityQueue<Integer> maxHeap;//lower half
    PriorityQueue<Integer> minHeap;//higher half

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() + (minHeap.peek())) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(15);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}