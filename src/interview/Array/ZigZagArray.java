package interview.Array;

import java.util.Arrays;

/**
 * Convert normal array into ZigZag Array
 * <p>
 * https://www.youtube.com/watch?v=bgx1eAgBgaQ
 */
public class ZigZagArray {

    public static void main(String[] args) {
        ZigZagArray zigZagArray = new ZigZagArray();
        int[] inputArray = new int[] { 3, 4, 6, 2, 1, 8, 9 };
        zigZagArray.convertIntoZigZag(inputArray);
        System.out.println(Arrays.toString(inputArray));
    }

    private void convertIntoZigZag(int[] inputArray) {
        int i = 0;
        boolean flag = true;
        while (i < inputArray.length - 1) {
            if ((flag && inputArray[i] > inputArray[i + 1]) || (!flag && inputArray[i] < inputArray[i + 1])) {
                swap(inputArray, i, i + 1);
            }
            i++;
            flag = !flag;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
