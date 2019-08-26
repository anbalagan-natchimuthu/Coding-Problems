package interview.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/find-the-missing-number/
 *
 * find missing elements in an area of 100 integers, which contains numbers between 1 and 100. But it's not in sequence
 */
public class MissingNumbers {

  public static void main(String[] args) {
    /** Find Single Missing Number */
    new MissingNumbers().findMissingAndDuplicateNumber(new int[]{1, 4, 3, 3}, 5);
    System.out.println("********************");
    new MissingNumbers().findMissingNumberUsingXor(new int[]{1, 4, 3}, 4);
    System.out.println("********************");
    new MissingNumbers().findDuplicateNumbers(new int[]{1, 4, 3, 3});
    System.out.println("\n\n********************");

    /* Find Multiple Missing Numbers */
    new MissingNumbers().missingNumbers(new int[]{10, 8, 12});
    System.out.println("\n********************");
    new MissingNumbers().findMissingNumbersUsingBitSet(new int[]{1, 4, 5}, 5);
    System.out.println("********************");
  }

  /**
   * Space Complexity : O(n) Using HashMap
   */
  void findMissingAndDuplicateNumber(int[] inputArray, int arrayLength) {
    HashMap<Integer, Integer> storage = new HashMap<>();
    for (int number : inputArray) {
      storage.compute(number, (key, value) -> {
        if (value == null) {
          return 1;
        } else {
          return value + 1;
        }
      });
    }

    ArrayList<Integer> missingNumbers = new ArrayList<>();
    ArrayList<Integer> duplicateNumbers = new ArrayList<>();
    for (int i = 1; i <= arrayLength; i++) {
      if (storage.get(i) == null) {
        missingNumbers.add(i);
      } else if (storage.get(i) != 1) {
        duplicateNumbers.add(i);
      }
    }
    System.out.println("missingNumbers::" + missingNumbers);
    System.out.println("duplicateNumbers::" + duplicateNumbers);
  }

  /**
   * Space Complexity : O(n) Using BitSet
   * https://javarevisited.blogspot.com/2014/11/how-to-find-missing-number-on-integer-array-java
   * .html?_sm_au_=iWHtSQNQ1NrwVPQH
   *
   * NOTE: This will work 1 to 100 only
   */
  void findMissingNumbersUsingBitSet(int[] inputArray, int arrayLength) {
    BitSet storage = new BitSet(arrayLength);
    for (int number : inputArray) {
      // Set corresponding index. BitSet index starts from zero
      storage.set(number - 1);
    }

    int lastMissingIndex = 0;
    System.out.print("missingNumbers::");
    for (int i = 0; i < arrayLength - inputArray.length; i++) {
      lastMissingIndex = storage.nextClearBit(lastMissingIndex);
      // you have to increment lastMissingIndex, otherwise it will print the same number again
      System.out.print(++lastMissingIndex + "   ");
    }
    System.out.println();
  }

  /**
   * https://leetcode.com/problems/missing-number/
   * https://www.geeksforgeeks.org/find-the-missing-number/
   */
  void findMissingNumberUsingXor(int[] inputArray, int length) {
    int x1 = inputArray[0];
    int x2 = 1;

        /* For xor of all the elements in array */
    for (int i = 1; i < inputArray.length; i++)
      x1 = x1 ^ inputArray[i];

        /* For xor of all the elements from 1 to n+1 */
    for (int i = 2; i <= length; i++)
      x2 = x2 ^ i;

    int missing = (x1 ^ x2);
    System.out.println("Missing using XOR:" + missing);
  }

  /**
   * Using Set
   */
  <T> void findDuplicateNumbers(int[] inputArray) {
    Set<Integer> storage = new HashSet<>(inputArray.length);
    System.out.print("duplicateNumbers::");
    for (int number : inputArray) {
      if (!storage.add(number)) {
        System.out.print(number + "   ");
      }
    }
  }

  /**
   * PROBLEM : 2 Find missing numbers
   * <p>
   * https://www.geeksforgeeks.org/find-four-missing-numbers-array-containing-elements-1-n/
   *
   * Given an array of unique integers where each integer of the given array lies in the range [1, N]. The size of
   * array is (N-4). No Single element is repeated. Hence four numbers from 1 to N are missing in the array. Find the
   * 4 missing numbers in sorted order.
   *
   * Examples:
   *
   * Input : arr[] = {2, 5, 6, 3, 9}
   * Output : 1 4 7 8
   *
   * Input : arr[] = {1, 7, 3, 13, 5, 10, 8, 4, 9}
   * Output : 2 6 11 12
   */

  private void missingNumbers(int[] arr) {
    int minNumber = Arrays.stream(arr).min().orElseThrow(NoSuchElementException::new);
    int maxNumber = Arrays.stream(arr).max().getAsInt();

    int[] helper = new int[maxNumber - minNumber - arr.length + 1];

    for (int i = 0; i < arr.length; i++) {
      int temp = Math.abs(arr[i]);

      // If element is smaller than or equal to length, mark its presence in arr[]
      int indexVal = temp - minNumber;
      if (indexVal <= arr.length) {
        arr[indexVal] *= (-1);
      }

      // Mark presence in helper[]
      else if (indexVal > arr.length) {
        helper[indexVal - arr.length] = -1;
      }
    }

    System.out.print("\nMissing Numbers without Additional space :: ");
    // Print all those elements whose presence is not marked.
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > 0) {
        System.out.print(i + minNumber + " ");
      }
    }
    for (int i = 0; i < helper.length; i++) {
      if (helper[i] >= 0) {
        System.out.print(arr.length + minNumber + " ");
      }
    }
  }
}
