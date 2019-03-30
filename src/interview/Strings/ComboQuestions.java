package interview.Strings;

import java.util.PriorityQueue;

public class ComboQuestions {

    public static void main(String[] args) {
        String input = "abc,def*g";
        reverseStrWithSplChars(input);
        System.out.println();
        String input1 = "   This   is a ";
        reverseOfString(input1);
        System.out.println();

        int[] arr = { 1, -1, 0, 3, 2, 1, 5, 6, 4 };
        kthLargestElemt(arr, 3);

        int[] arr1 = { 1, 2, 3, 4, 5, 6 };
        rotateArrByK(arr1, 4);

        int[] arr2 = { 3, 4, 5, 9, 10, 11 };
        //int res = findMinElement(arr2, 0, arr2.length);
        System.out.println(findLowIndexElementV2(arr2, 5));
        System.out.println(findHighIndexElementV2(arr2, 5));
        //System.out.println(res);
    }

    private static int findMinElement(int[] arr, int low, int high) {

        while (low <= high) {
            int mid = (high + low) / 2;
            if (arr[mid] > arr[mid + 1]) {
                return arr[mid + 1];
            } else if (arr[mid] < arr[mid - 1]) {
                return arr[mid];
            } else if (arr[mid] > arr[low]) {

                findMinElement(arr, low, mid);
            } else if (arr[mid] < arr[high]) {
                findMinElement(arr, mid + 1, high);
            }
        }
        return -1;
    }

    private static int findMinElement1V(int[] a, int y) {

        int lo = 0, hi = a.length - 1, mid;
        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (a[mid] > y) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static int findMinElementV1(int[] a) {

        int lo = 0, hi = a.length - 1, mid;
        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (a[mid] < a[mid + 1]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static int findLowIndexElementV2(int[] a, int x) {

        int lo = 0, hi = a.length - 1, mid;

        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (a[mid] >= x) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static int findHighIndexElementV2(int[] a, int x) {

        int lo = 0, hi = a.length - 1, mid;

        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (a[mid] > x) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static void rotateArrByK(int[] arr, int k) {
        reverseInt(arr, 0, k - 1);
        reverseInt(arr, k, arr.length - 1);
        reverseInt(arr, 0, arr.length - 1);
        System.out.println("***** Rotated Array *****");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("***** ********** *****");
        System.out.println();

    }

    private static void reverseInt(int[] intArr, int start, int end) {
        int temp;
        while (start <= end) {
            temp = intArr[start];
            intArr[start] = intArr[end];
            intArr[end] = temp;
            start++;
            end--;
        }
    }

    private static void kthLargestElemt(int[] input, int k) {
        PriorityQueue pq = new PriorityQueue();
        for (int i = 0; i < input.length; i++) {

            pq.offer(input[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        System.out.println(pq.peek());
    }



    private static void reverseStrWithSplChars(String input) {
        char[] charArr = input.toCharArray();
        int i = 0;
        int j = input.length() - 1;
        while (i <= j) {
            if ((charArr[i] >= 'a' && charArr[i] <= 'z') && (charArr[j] >= 'a' && charArr[j] <= 'z')) {
                char temp = charArr[i];
                charArr[i] = charArr[j];
                charArr[j] = temp;
                i++;
                j--;
            } else if (!(charArr[i] >= 'a' && charArr[i] <= 'z')) {
                i++;
            } else if (!(charArr[j] >= 'a' && charArr[j] <= 'z')) {
                j--;
            }
        }
        for (int ik = 0; ik < charArr.length; ik++) {
            System.out.print(charArr[ik]);
        }
    }

    private static void reverseOfString(String input) {
        char[] charArr = input.toCharArray();
        reverse(charArr, 0, input.length() - 1);
        int k = 0;
        for (int i = 0; i < input.length(); i++) {
            if (charArr[i] == ' ') {
                reverse(charArr, k, i - 1);
                k = i + 1;
            }
        }
        // to reverse the last word
        reverse(charArr, k, input.length() - 1);

        System.out.print(":");
        for (int i = 0; i < charArr.length; i++) {
            System.out.print(charArr[i]);
        }
        System.out.print(":");
    }

    private static void reverse(char[] charArr, int start, int end) {
        char temp;
        while (start <= end) {
            temp = charArr[start];
            charArr[start] = charArr[end];
            charArr[end] = temp;
            start++;
            end--;
        }
    }

    private static void findDuplicate(int[] arr,int n){

        //
        // 1,2,3,4,6 = 16
        // 6*7/2 = 21
        //  21-16 = 5
        //
        //  1,2,4,5,6=    18
        //
    // 1,2,3,3,4 = 13
        //    13-10 = 3
        //
        //1 to n-1
        // n(n+1)/2 = 5*6/2 = 15
        // (n-1)   (n)/2 = 4*5/2 = 10
            //5

    }
}
