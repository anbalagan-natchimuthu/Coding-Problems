package interview.Sorting;

import java.util.Random;

/**
 * https://www.youtube.com/watch?v=Fiot5yuwPAg&list=PLj8W7XIvO93qVnnXxyeWmCSvMFqRBP4Jw&index=5
 */
// Java program for implementation of QuickSort
public class QuickSort {

    /* This function takes last element as pivot, places the pivot element at its correct position in sorted array,
    and places all smaller (smaller than pivot) to left of pivot and all greater elements to right of pivot */
    int partitionByLastElementPivot(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low; // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i;
    }

    private int partitionByRandomPivot(int arr[], int left, int right, int pivot) {

        int tmp;

        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sort(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is now at right place */
            int pi = partitionByLastElementPivot(arr, low, high);

            // Recursively sort elements before partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    void quickSort(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is now at right place */
            int pi = partitionByRandomPivot(arr, low, high, arr[getRandomPivotIndex(low, high)]);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi, high);
        }
    }

    private int getRandomPivotIndex(int low, int high) {
        Random random = new Random();
        return random.nextInt((high - low) + low);
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Driver program
    public static void main(String args[]) {
        int arr[] = { 3, 2, 2, 1, 2};
        int n = arr.length;

        QuickSort ob = new QuickSort();
        ob.quickSort(arr, 0, n - 1);

        System.out.println("sorted array");
        printArray(arr);
    }
}
