package interview.Array;

import java.util.NoSuchElementException;

/**
 * https://codepumpkin.com/heap/
 * https://www.youtube.com/watch?v=t0Cq6tVNRBA
 * Created by 212438472 on 8/11/18.
 */
public class MaxHeap {

    private static final int d = 2;
    private int[] heap;
    private int heapSize;

    /**
     * This will initialize our heap with default size.
     */
    public MaxHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity];
       // Arrays.fill(heap, -1);
    }

    /**
     *  This will check if the heap is empty or not
     *  Complexity: O(1)
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     *  This will check if the heap is full or not
     *  Complexity: O(1)
     */
    public boolean isFull() {
        return heapSize == heap.length;
    }

    private int parent(int i) {
        return (i - 1) / d;
    }

    private int kthChild(int i, int k) {
        return d * i + k;
    }

    /**
     *  This will insert new element in to heap
     *  Complexity: O(log N)
     *  As worst case scenario, we need to traverse till the root
     */
    public void insert(int x) {
        if (isFull()) {
            throw new NoSuchElementException("Heap is full, No space to insert new element");
        }
        heap[heapSize] = x;
        heapifyUp(heapSize);
        heapSize++;
    }

    /**
     *  This will delete element at index x
     *  Complexity: O(log N)
     *
     */
    public int delete(int x) {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty, No element to delete");
        }
        int key = heap[x];
        heapSize--;
        heap[x] = heap[heapSize];
        heap[heapSize] = -1;
        heapifyDown(x);
        return key;
    }

    /**
     *  This method used to maintain the heap property while inserting an element.
     *
     */
    private void heapifyUp(int i) {
        int temp = heap[i];
        while (i > 0 && temp > heap[parent(i)]) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = temp;
    }

    /**
     *  This method used to maintain the heap property while deleting an element.
     *
     */
    private void heapifyDown(int i) {
        int child;
        int temp = heap[i];
        while (kthChild(i, 1) < heapSize) {
            child = maxChild(i);
            if (temp < heap[child]) {
                heap[i] = heap[child];
            } else {
                break;
            }

            i = child;
        }
        heap[i] = temp;
    }

    private int maxChild(int i) {
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);

        return heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
    }

    /**
     *  This method used to print all element of the heap
     *
     */
    public void printHeap() {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    /**
     *  This method returns the max element of the heap.
     *  complexity: O(1)
     */
    public int findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap[0];
    }

    public void print() {
        for (int i = 0; i <= (heapSize / 2); i++) {
            String leftChild = 2 * i + 1 < heapSize ? String.valueOf(heap[2 * i + 1]) : "EMPTY";
            String rightChild =  2 * i + 2 < heapSize ? String.valueOf(heap[2 * i + 2]) : "EMPTY";
            System.out.print(" PARENT : " + heap[i] + " LEFT CHILD : " + leftChild
                + " RIGHT CHILD :" + rightChild);
            System.out.println();
        }
    }

    // test cases
    public static void main(String[] args) {
        int[] a = new int[] {};
        MaxHeap maxHeap = new MaxHeap(9);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);
        maxHeap.printHeap();
        maxHeap.print();
        maxHeap.delete(0);
        maxHeap.printHeap();
        maxHeap.print();
    }
}