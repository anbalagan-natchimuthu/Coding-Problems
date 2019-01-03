package interview.Sorting;

/**
 * https://www.youtube.com/watch?v=cqh8nQwuKNE&index=2&list=PLj8W7XIvO93qVnnXxyeWmCSvMFqRBP4Jw
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] inputArray = new int[] { 40, 20, 60, 30, 70, 10, 20 };
        printArray(inputArray);
        sort(inputArray);
        printArray(inputArray);
    }

    private static void sort(int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            int minValue = inputArray[i];
            int minIndex = i;

            for (int j = i; j < inputArray.length; j++) {
                if (inputArray[j] < minValue) {
                    minValue = inputArray[j];
                    minIndex = j;
                }
            }

            if (minValue < inputArray[i]) {
                int temp = inputArray[i];
                inputArray[i] = inputArray[minIndex];
                inputArray[minIndex] = temp;
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
