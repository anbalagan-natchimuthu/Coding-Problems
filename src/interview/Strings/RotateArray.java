package interview.Strings;

// Can we do this in O(1) space and in O(n) time? The following solution does.
//
//     Assuming we are given {1,2,3,4,5,6} and order 2. The basic idea is:
//
//     1. Divide the array two parts: 1,2,3,4 and 5, 6
//     2. Reverse first part: 4,3,2,1,5,6
//     3. Reverse second part: 4,3,2,1,6,5
//     4. Reverse the whole array: 5,6,1,2,3,4
public class RotateArray {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        rotateArr(arr, 4);
    }

    private static void rotateArr(int[] arr, int rotateBy) {

        reversePart(arr, rotateBy, arr.length - 1);
        reversePart(arr, 0, rotateBy - 1);
        reversePart(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void reversePart(int[] arr, int start, int end) {
        if (arr == null || arr.length == 1) {
            return;
        }

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private static void rotateArray(int[] arr, int order) {

        if (arr == null || arr.length == 0 || order < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (order > arr.length) {
            order = order % arr.length;
        }

        //length of first part
        int a = arr.length - order;

        reverse(arr, 0, a - 1);
        reverse(arr, a, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    public static void reverse(int[] arr, int left, int right) {
        if (arr == null || arr.length == 1) {
            return;
        }

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}

//1234567 by 3

