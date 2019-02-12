package interview.Array;

import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/merge-k-sorted-arrays/
 *
 * https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
 * <p>
 * Given k sorted arrays of size n each, merge them and print the sorted output. Example:
 * <p>
 * Input: k = 3, n =  4 arr[][] = { {1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11}} ;
 * <p>
 * Output: 0 1 2 3 4 5 6 7 8 9 10 11
 */
public class MergeKsortedArrays {

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

        int[][] inputArray = { { 1, 3, 5, 7 }, { 2, 4, 6, 8 }, { 0, 9, 10, 11 } };

        int[] outputArray = new int[inputArray.length * inputArray[0].length];
        PriorityQueue<NodeElement> minHeap = new PriorityQueue<>(inputArray.length, (q1, q2) -> {
            return q1.element - q2.element;
        });

        for (int i = 0; i < inputArray.length; i++) {
            NodeElement node = new NodeElement(inputArray[i][0], i, 1);
            minHeap.add(node);
        }

        int k = 0;
        while(!minHeap.isEmpty()) {
            NodeElement temp = minHeap.poll();
            outputArray[k++] = temp.element;
            addElement(minHeap, inputArray, temp.arrayLocation, temp.nextElement);
        }

        for (int i = 0; i < outputArray.length; i++) {
            System.out.print(outputArray[i] + " ");
        }
    }
}
