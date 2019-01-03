package interview.Array;

import java.util.Arrays;

/**
 * Created by 212438472 on 8/3/18.
 */
public class RotateArray {

    /**
     * https://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/
     */
    /* Function to left rotate arr[] of size n by d */
    static void leftRotate(int arr[], int d)
    {
        int n = arr.length;
        if (d >= arr.length) {
            d = d % arr.length;
        }
        rvereseArray(arr, 0, d-1);
        rvereseArray(arr, d, n-1);
        rvereseArray(arr, 0, n-1);
    }

    /*Function to reverse arr[] from index start to end*/
    static void rvereseArray(int arr[], int start, int end)
    {
        int temp;
        while (start < end)
        {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

        /*
        * https://www.geeksforgeeks.org/array-rotation/
        * Function to left rotate arr[] of siz n by d */
        void leftRotate(int arr[], int noOfRotations, int arrLength) {
            int i, j, k, temp;
            if (noOfRotations >= arrLength) {
                noOfRotations = noOfRotations % arrLength;
            }
            int gcd = gcd(arrLength, noOfRotations);
            System.out.println("gcd:" + gcd);
            for (i = 0; i < gcd; i++) {
            /* move i-th values of blocks */
                temp = arr[i];
                j = i;
                while (true) {
                    k = j + noOfRotations;
                    if (k >= arrLength) {
                        k = k - arrLength;
                    }
                    if (k == i) {
                        break;
                    }
                    arr[j] = arr[k];
                    j = k;
                }
                arr[j] = temp;
            }
        }

	/*UTILITY FUNCTIONS*/

        /* function to print an array */
        void printArray(int arr[], int size)
        {
            int i;
            for (i = 0; i < size; i++)
                System.out.print(arr[i] + " ");
        }

        /*Fuction to get greatest common divisor of a and b*/
        public int gcd(int a, int b)
        {
            if (b == 0)
                return a;
            else
                return gcd(b, a % b);
        }

        // Driver program to test above functions
        public static void main(String[] args) {
            RotateArray rotate = new RotateArray();
            int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
            int arr1[] = Arrays.copyOf(arr, arr.length);

            rotate.leftRotate(arr, 3, arr.length);
            rotate.printArray(arr, arr.length);

            System.out.println("*************************");

            leftRotate(arr1, 15); // Rotate array by 2
            rotate.printArray(arr1, arr1.length);

            System.out.println("*************************");

        }

}
