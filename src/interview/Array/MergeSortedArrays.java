package interview.Array;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {

        int arr1[] = new int[13];
        arr1[0] = 1;
        arr1[1] = 2;
        arr1[2] = 4;
        arr1[3] = 8;
        arr1[4] = 10;

        int arr2[] = { 3, 5, 6, 7, 9, 11, 12, 13 };
        mergeArrays(arr1, arr2);
    }

    private static void mergeArrays(int[] arr1, int[] arr2) {

        int i = arr1.length - arr2.length - 1;
        int j = arr2.length - 1;
        int k = arr1.length-1;

        while (i >= 0 && j >=0) {
            if (arr1[i] >= arr2[j]) {
                arr1[k--] = arr1[i--];
            } else {
                arr1[k--] = arr2[j--];
            }
        }

        while (i >= 0) {
            arr1[k--] = arr1[i--];
        }

        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }

        System.out.println(Arrays.toString(arr1));
    }
}

// 1,2,3,4,5
// 6,7,8,9,10
