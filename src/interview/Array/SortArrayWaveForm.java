package interview.Array;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/sort-array-wave-form-2/
 * Given an unsorted array of integers, sort the array into a wave like array.
 * An array ‘arr[0..n-1]’ is sorted in wave form if arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] >= …..

 Examples:

 Input:  arr[] = {10, 5, 6, 3, 2, 20, 100, 80}
 Output: arr[] = {10, 5, 6, 2, 20, 3, 100, 80} OR
 {20, 5, 10, 2, 80, 6, 100, 3} OR
 any other array that is in wave form

 Input:  arr[] = {20, 10, 8, 6, 4, 2}
 Output: arr[] = {20, 8, 10, 4, 6, 2} OR
 {10, 8, 20, 2, 6, 4} OR
 any other array that is in wave form

 Input:  arr[] = {2, 4, 6, 8, 10, 20}
 Output: arr[] = {4, 2, 8, 6, 20, 10} OR
 any other array that is in wave form

 Input:  arr[] = {3, 6, 5, 10, 7, 20}
 Output: arr[] = {6, 3, 10, 5, 20, 7} OR
 any other array that is in wave form
 */
public class SortArrayWaveForm {

    public static void main(String[] args) {
        SortArrayWaveForm srwf = new SortArrayWaveForm();

        int[] arr = new int[] {10, 5, 6, 3, 2, 20, 100, 80};
        srwf.waveFormUsingSort(arr);
        srwf.printArray(arr);

        System.out.println();

        int[] arr1 = new int[] {10, 90, 49, 2, 1, 5, 23};
        srwf.waveFormWithoutUsingSort(arr1);
        srwf.printArray(arr1);
    }

    private void waveFormUsingSort(int[] inputArray) {
        Arrays.sort(inputArray);
        for (int i=0; i<inputArray.length; i+=2) {
            swap(inputArray, i, i+1);
        }
    }

    private void waveFormWithoutUsingSort(int[] inputArray) {
        int length = inputArray.length -1;
        for (int i=0; i<length; i+=2) {
            if (i > 0 && inputArray[i] < inputArray[i-1]) {
                swap(inputArray, i, i + 1);
            }

            if (i < length && inputArray[i] < inputArray[i+1]) {
                swap(inputArray, i, i+1);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
