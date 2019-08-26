package interview.Array;

/**
 * https://www.techiedelight.com/count-occurrences-number-sorted-array-duplicates/
 * https://www.geeksforgeeks.org/count-number-of-occurrences-or-frequency-in-a-sorted-array/
 *
 * Count number of occurrences (or frequency) in a sorted array
 * Given a sorted array arr[] and a number x, write a function that counts the occurrences of x in arr[].
 * Expected time complexity is O(Logn)
 * Examples:
 *
 *   Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 2
 *   Output: 4 // x (or 2) occurs 4 times in arr[]
 *
 *   Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 3
 *   Output: 1
 *
 *   Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 1
 *   Output: 2
 *
 *   Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 4
 *   Output: -1 // 4 doesn't occur in arr[]
 */
public class CountOccurrencesOfANumber {

  public static void main(String[] args) {
    System.out.println(countFrequencies(new int[]{2, 5, 5, 5, 6, 6, 8, 9, 9, 9}, 5));
    System.out.println(countFrequencies(new int[]{1, 1, 2, 2, 2, 2, 3}, 1));
    System.out.println(countFrequencies(new int[]{1, 1, 2, 2, 2, 2, 3}, 5));
  }

  public static int countFrequencies(int[] number, int key) {
    if (number == null || number.length <=0) {
      return 0;
    }

    // pass true for first occurrence
    int firstIndex = binarySearch(number, key, true);

    if (firstIndex == -1) {
      return firstIndex;
    }

    // pass false for last occurrence
    int lastIndex = binarySearch(number, key, false);
    return lastIndex -firstIndex +1;
  }

  // If searchFirst is true, we return the first occurrence of a number
  // in sorted array of integers; else we return its last occurrence
  private static int binarySearch(int[] number, int key, boolean searchFirst) {

    int left = 0;
    int right = number.length-1;

    // initialize the result by -1
    int result = -1;

    // iterate till search space contains at-least one element
    while (left <= right) {

      // find the mid value in the search space and
      // compares it with key value
      int mid = (left + right) / 2;

      // if key is found, update the result
      if (number[mid] == key) {
        result = mid;

        // go on searching towards left (lefter indices)
        if (searchFirst) {
          right = mid - 1;
        } else {
          // go on searching towards right (higher indices)
          left = mid + 1;
        }
      } else if (key < number[mid]) {
        // if key is less than the mid element, discard right half
        right = mid -1;
      } else {
        // if key is more than the mid element, discard left half
        left = mid + 1;
      }
    }
    return result;
  }
}
