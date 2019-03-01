package interview.Array;

public class MoveZeroes {

    public static void main(String[] args) {
        int[] arr = { 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9 };
        moveZeroesWithMinWrites(arr);
    }

    // private static void moveZeroes(int[] arr) {
    //     int i = 0;
    //     int j = arr.length - 1;
    //     while (i <= j) {
    //         if (arr[i] == 0 && arr[j] != 0) {
    //             int temp = arr[j];
    //             arr[j] = arr[i];
    //             arr[i] = temp;
    //             i++;
    //             j--;
    //         } else if (arr[i] != 0 && arr[j] == 0) {
    //             j--;
    //         } else if (arr[i] != 0 && arr[j] != 0) {
    //             if (arr[j - 1] == 0) {
    //                 int newTemp = arr[j];
    //                 arr[j] = arr[j - 1];
    //                 arr[j - 1] = arr[j];
    //             }
    //             j--;
    //             i++;
    //         }
    //     }
    //
    //     for (int k = 0; k < arr.length; k++) {
    //         System.out.print(arr[k]);
    //     }
    // }

    public static void moveZeroesWithMinWrites(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] != 0) {
                left++;
            }
            while (left < right && nums[right] == 0) {
                right--;
            }
            if (left < right) {
                nums[left++] = nums[right--];
            }
        }

        for (int j= left; j < nums.length; j++) {
            nums[j] = 0;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }

    }
}
