package interview.Sorting;

/**
 * https://www.youtube.com/watch?v=F13_wsHDIG4&list=PLj8W7XIvO93qVnnXxyeWmCSvMFqRBP4Jw&index=3
 * https://www.youtube.com/watch?v=6Gv8vg0kcHc
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] inputArray = new int[] { 40, 20, 60, 30, 70, 10, 20 };
        printArray(inputArray);
        sort(inputArray);
        printArray(inputArray);

        int[] inputArray1 = new int[] { 40, 20, 60, 30, 70, 10, 20 };
        //printArray(inputArray1);
        bubbleSort(inputArray1);
        printArray(inputArray1);
    }

    private static void sort(int[] inputArray) {
        for (int i = 0; i < inputArray.length - 1; i++) {
            for (int j = 0; j < inputArray.length - i - 1; j++) {
                if (inputArray[j] > inputArray[j + 1]) {
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                }
            }
        }
    }

    private static void bubbleSort(int[] input) {
        boolean isSorted = false;
        int lastUnsorted = input.length - 1;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < lastUnsorted; i++) {
                if (input[i] > input[i + 1]) {
                    isSorted = false;
                    swap(input, i, i + 1);
                }
            }
            lastUnsorted--;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
