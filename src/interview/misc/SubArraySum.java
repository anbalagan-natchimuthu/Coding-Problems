package interview.misc;

public class SubArraySum {

    public static void main(String[] args) {
        int[] arr = { 3, 2, 4, 7, 5, 8 };
        subArraySum(arr, 13);
    }

    private static void subArraySum(int[] arr, int sum) {
        int addSum = 0;
        int end = 0;
        int start = 0;

        for (int i = 0; i < arr.length; i++) {
            addSum = addSum + arr[i];
            if (addSum == sum) {
                end = i;
                break;
            } else if (addSum > sum) {
                addSum = 0;
                start = i + 1;
            }
        }

        for (int j = start; j <= end; j++) {
            System.out.println(arr[j]);
        }
    }
}
