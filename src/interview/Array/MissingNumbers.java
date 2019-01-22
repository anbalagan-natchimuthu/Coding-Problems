package interview.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by 212438472 on 7/15/18.
 */

/**
 * find missing elements in an area of 100 integers, which contains numbers between 1 and 100. But it's not in sequence
 */
public class MissingNumbers {

    public static void main(String[] args) {
        new MissingNumbers().findMissingAndDuplicateNumber(new int[] { 1, 4, 3, 3 }, 5);
        new MissingNumbers().findMissingNumber(new int[] { 1, 4, 3 }, 5);
        new MissingNumbers().findDuplicateNumbers(new int[] { 1, 4, 3, 3 });
        new MissingNumbers().missingNumbers(new int[] { 10, 8, 12 });
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
     */
    void findMissingNumber(int[] inputArray, int arrayLength) {
        BitSet storage = new BitSet(arrayLength);
        for (int number : inputArray) {
            // Set corresponding index. BitSet index starts from zero
            storage.set(number - 1);
        }

        int lastMissingIndex = 0;
        System.out.print("missingNumbers::");
        for (int i = 1; i <= arrayLength - inputArray.length; i++) {
            lastMissingIndex = storage.nextClearBit(lastMissingIndex);
            System.out.print(++lastMissingIndex + "   ");
        }
        System.out.println();
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
     */

    private void missingNumbers(int[] arr) {
        int minNumber = Arrays.stream(arr).min().orElseThrow(NoSuchElementException::new);
        int maxNumber = Arrays.stream(arr).max().orElseThrow(NoSuchElementException::new);

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
