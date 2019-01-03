package interview.Strings;
// We are playing the Guess Game. The game is as follows:
//
//     I pick a number from 1 to n. You have to guess which number I picked.
//
//     Every time you guess wrong, I'll tell you whether the number is higher or lower.
//
//     You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
//
//     -1 : My number is lower
//     1 : My number is higher
//     0 : Congrats! You got it!
//     Example:
//     n = 10, I pick 6.
//
//     Return 6.

// num
// range [0-10]
// i say mid =

public class GuessNumber {

    public static void main(String[] args) {
        int[] inputArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    }

    // public int guessNumber(int n) {
    //     int low=1;
    //     int high=n;
    //
    //     while(low <= high){
    //         int mid = low+((high-low)/2);
    //         int result = guess(mid);
    //         if(result==0){
    //             return mid;
    //         }else if(result==1){
    //             low = mid+1;
    //         }else{
    //             high=mid-1;
    //         }
    //     }
    //
    //     return -1;
    // }

    public static int isNumber(int num) {
        int[] inputArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int mid = findNumber(inputArray, 0, inputArray.length - 1);
        if (mid == num) {
            return 0;
        } else if (num > mid) {
            findNumber(inputArray, mid + 1, inputArray.length - 1);
            return 1;
        }
        if (num < mid) {
            findNumber(inputArray, 0, mid);
            return -1;
        }

        return 0;
    }

    public static int findNumber(int[] inputArray, int start, int end) {
        int mid = start + (end - start) / 2;
        return mid;
    }
}
