package interview.Sorting;

/**
 * https://www.youtube.com/watch?v=lCDZ0IprFw4&list=PLj8W7XIvO93qVnnXxyeWmCSvMFqRBP4Jw&index=1
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] inputArray = new int[] { 40, 20, 60, 30, 70, 10, 20 };
        printArray(inputArray);
        sort(inputArray);
        printArray(inputArray);
    }

    private static void sort(int[] inputArray) {
        for (int i = 1; i < inputArray.length; i++) {
            int j = i - 1;
            int key = inputArray[i];
            while (j >= 0 && key < inputArray[j]) {
                //swap
                int temp = inputArray[j];
                inputArray[j] = inputArray[j + 1];
                inputArray[j + 1] = temp;
                j--;
            }
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
