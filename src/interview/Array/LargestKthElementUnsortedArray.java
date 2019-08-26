package interview.Array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 * <p>
 * For example, given [3,2,1,5,6,4] and k = 2, return 5.
 * https://www.programcreek.com/2014/05/leetcode-kth-largest-element-in-an-array-java/
 */
public class LargestKthElementUnsortedArray {

    private static Random random = new Random();

    public static void main(String[] args) {
        int[] input = { 3, 2, 1, 5, 6, 4 };
        System.out.println(KthLargestUsingSort(input, 3));
        System.out.println(KthLargestUsingMinHeap(input, 3));
        System.out.println(findKthLargestUsingQuickSelect(new int[] {5, 1, 6, 7, 2, 9}, 4));
    }

    /**
     * Time complexity - O(nlog(n)).
     */
    private static int KthLargestUsingSort(int[] inputArray, int kthElement) {
        Arrays.sort(inputArray);
        return inputArray[inputArray.length - kthElement];
    }

    /**
     * Time complexity is O(nlog(k)). Space complexity is O(k) for storing the top k numbers.
     */
    private static int KthLargestUsingMinHeap(int[] inputArray, int kthElement) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(inputArray.length);
        for (int num : inputArray) {
            /*pq.add(num);

            if (pq.size() > kthElement) {
                pq.poll();
            }*/

            if (pq.size() < kthElement) {
                pq.add(num);
            } else if (pq.peek() <= num) {
                pq.add(num);
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * This solution works in O(n) time on average. However, in the worst case, the time complexity will be O(n^2).
     * https://www.baeldung.com/java-kth-largest-element
     * https://www.programcreek.com/2014/05/leetcode-kth-largest-element-in-an-array-java/
     */
    public static int findKthLargestUsingQuickSelect(int[] nums, int k) {
        if (k < 1 || nums == null) {
            return 0;
        }

        return getKth(nums.length - k, nums, 0, nums.length - 1);
    }

    public static int getKth(int k, int[] nums, int start, int end) {

        int pivot = nums[end];
        int left = start;
        int right = end;

        while (true) {

            while (nums[left] < pivot && left < right) {
                left++;
            }

            while (nums[right] >= pivot && right > left) {
                right--;
            }

            if (left == right) {
                break;
            }

            swap(nums, left, right);
        }

        swap(nums, left, end);

        if (k == left) {
            return pivot;
        } else if (k < left) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }

    public static void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }
}
