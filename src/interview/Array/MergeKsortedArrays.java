package interview.Array;

import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/merge-k-sorted-arrays/
 *
 * https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
 *
 * Given k sorted arrays of size n each, merge them and print the sorted output. Example:
 *
 * Input: k = 3, n =  4
 * arr[][] = {
 *              {1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11}
 *           } ;
 *
 * Output: 0 1 2 3 4 5 6 7 8 9 10 11
 */
public class MergeKsortedArrays {

  /**
   *   SOLUTION : 1
   * Merge K Sorted Arrays using Priority Queue
   *
   * Time complexity : O(Nlogk) where k is the number of Arrays.
   *
   * The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue. But
   * finding the element with the smallest value just costs O(1) time.
   * There are N values in the final Array.
   *
   * Space complexity :
   *
   * O(n) Creating a new Array costs O(n) space.
   * O(k) The code above present applies in-place method which cost O(1) space. And the priority queue (often
   * implemented with heaps) costs O(k) space (it's far less than N in most situations).
   */
  static class NodeElement {

    int element;
    int arrayLocation;
    int nextElement;

    NodeElement(int element, int arrayLocation, int nextElement) {
      this.element = element;
      this.arrayLocation = arrayLocation;
      this.nextElement = nextElement;
    }
  }

  private static void addElement(PriorityQueue<NodeElement> minHeap, int[][] inputArray, int arrayLocation,
      int nextElement) {

    if (inputArray[arrayLocation].length != nextElement) {
      minHeap.add(new NodeElement(inputArray[arrayLocation][nextElement], arrayLocation, nextElement + 1));
    }
  }

  public static void main(String[] args) {

    int[][] inputArray = {{1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11}};

    int[] outputArray = new int[inputArray.length * inputArray[0].length];

    PriorityQueue<NodeElement> minHeap = new PriorityQueue<>(inputArray.length, (q1, q2) -> {
      return q1.element - q2.element;
    });

    for (int i = 0; i < inputArray.length; i++) {
      NodeElement node = new NodeElement(inputArray[i][0], i, 1);
      minHeap.add(node);
    }

    int k = 0;
    while (!minHeap.isEmpty()) {
      NodeElement temp = minHeap.poll();
      outputArray[k++] = temp.element;
      addElement(minHeap, inputArray, temp.arrayLocation, temp.nextElement);
    }

    for (int i = 0; i < outputArray.length; i++) {
      System.out.print(outputArray[i] + " ");
    }

    System.out.println("\n************************************\n");

    int[] result = mergeArrays(inputArray);

    for (int i = 0; i < result.length; i++) {
      System.out.print(result[i] + " ");
    }

    System.out.println("\n************************************\n");

    int[] resultArray = mergeArraysUsingDivideAndConquer(inputArray);

    for (int i = 0; i < resultArray.length; i++) {
      System.out.print(resultArray[i] + " ");
    }
  }

  /**
   * Solution : 2
   *
   * Time complexity : O(kN) where k is the number of Arrays.
   *
   * We can merge two sorted arrays in O(n) time where n is the total number of elements in two arrays.
   *
   * Space complexity : O(1)
   *
   * We can merge two sorted arrays in O(1) space.
   */
  public static int[] mergeArrays(int[][] inputArray) {
    int i = 1;
    int[] result = inputArray[0];
    while (i < inputArray.length) {
      result = mergeTwoSubArrays (result, inputArray[i++]);
    }
    return  result;
  }

  /**
   * Solution : 3
   *
   * Time complexity : O(Nlogk) where k is the number of Arrays
   * We can merge two sorted Arrays in O(n) time where n is the total number of elements in two Arrays.
   *
   * Space complexity : O(1). We can merge two sorted arrays in O(1) space.
   */
  public static int[] mergeArraysUsingDivideAndConquer(int[][] inputArray) {
    int interval = 1;
    while (interval < inputArray.length) {
      for (int i = 0; i + interval < inputArray.length; i = i + interval * 2) {
        inputArray[i] = mergeTwoSubArrays(inputArray[i], inputArray[i + interval]);
      }
      interval *= 2;
    }
    return inputArray[0];
  }

   private static int[] mergeTwoSubArrays(int[] array1, int[] array2) {
    int ptr1 = 0;
    int ptr2 = 0;
    int length1 = array1.length;
    int length2 = array2.length;
    int[] result = new int[array1.length + array2.length];
    int k = 0;

    while (ptr1 < length1 && ptr2 < length2) {
      if (array1[ptr1] < array2[ptr2]) {
        result[k] = array1[ptr1++];
      } else {
        result[k] = array2[ptr2++];
      }
      k++;
    }

    while (ptr1 < length1) {
      result[k++] = array1[ptr1++];
    }

     while (ptr2 < length2) {
       result[k++] = array2[ptr2++];
     }
     return result;
   }
}
