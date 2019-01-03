package interview.Array;

/**
 * Created by 212438472 on 7/26/18.
 */
public class MedianOfTwoSortedArray {
    // A Simple Merge based O(n) solution to find median of two sorted arrays
    static double getMedian(int ar1[], int ar2[]) {
        int i = 0;
        int j = 0;
        int count = 0;
        int n = (ar1.length + ar2.length) / 2;
        int m1 = -1, m2 = -1;

        /* Since there are 2n elements, median will
           be average of elements at index n-1 and
           n in the array obtained after merging ar1
           and ar2 */
        while (count <= n && i < ar1.length && j < ar2.length) {

            if (ar1[i] < ar2[j]) {
                /* Store the prev median */
                m1 = m2;
                m2 = ar1[i];
                i++;
            } else {
                /* Store the prev median */
                m1 = m2;
                m2 = ar2[j];
                j++;
            }

            count++;
        }

        while (count <= n && i == ar1.length) {
            m1 = m2;
            m2 = ar2[j++];
            count++;
        }

        while (count <= n && j == ar2.length) {
            m1 = m2;
            m2 = ar2[i++];
            count++;
        }

        if ((ar1.length + ar2.length) % 2 == 0){
            return (m1 + m2) / 2.0;
        } else {
            return m2;
        }
    }

    /**
     * Algorithm :

     1) Calculate the medians m1 and m2 of the input arrays ar1[]
     and ar2[] respectively.
     2) If m1 and m2 both are equal then we are done.
     return m1 (or m2)
     3) If m1 is greater than m2, then median is present in one
     of the below two subarrays.
     a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
     b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
     4) If m2 is greater than m1, then median is present in one
     of the below two subarrays.
     a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
     b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
     5) Repeat the above process until size of both the subarrays
     becomes 2.
     6) If size of the two arrays is 2 then use below formula to get
     the median.
     Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
     */
    public static float find(int[] a, int start_a, int end_a, int[] b, int start_b, int end_b) {

        if (end_a - start_a + 1 == 2 && end_b - start_b + 1 == 2) {
            float x = Math.max(a[start_a], b[start_b]);
            float y = Math.min(a[end_a], b[end_b]);
            return (x + y) / 2;
        }

        float median_a = getMedian(a, start_a, end_a);
        float median_b = getMedian(b, start_b, end_b);

        int mid_a = (start_a + end_a) / 2;
        int mid_b = (start_b + end_b) / 2;
        if (median_a > median_b) {
            return find(a, start_a, mid_a, b, mid_b, end_b);
        } else {
            return find(a, mid_a, end_a, b, start_b, mid_b);
        }
    }

    public static float getMedian(int[] x, int start, int end) {
        int size = end - start + 1;
        if (size % 2 == 0) {
            float m = x[start + (size / 2)];
            float n = x[start + (size - 1) / 2];
            return (m + n) / 2;
        } else {
            return x[start + (size - 1) / 2];
        }
    }

    /* Driver program to test above function */
    public static void main(String[] args) {
        int ar1[] = { 1, 2, 3, 4, 5 };
        int ar2[] = { 12, 13, 17, 30, 45 };

        int n1 = ar1.length;
        int n2 = ar2.length;
        System.out.println("Median is " + getMedian(ar1, ar2));

        float x = find(ar1, 0, ar2.length - 1, ar2, 0, ar2.length - 1);
        System.out.println("Median of combined sorted array is: " + x);
    }
}