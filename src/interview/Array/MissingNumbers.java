package interview.Array;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 212438472 on 7/15/18.
 */

/**
 * find missing elements in an area of 100 integers, which contains numbers between 1 and 100.
 * But it's not in sequence
 */
public class MissingNumbers {
    public static void main(String[] args) {
        new MissingNumbers().findMissingAndDuplicateNumber(new int[] { 1, 4, 3, 3 }, 5);
        new MissingNumbers().findMissingNumber(new int[] { 1, 4, 3 }, 5);
        new MissingNumbers().findDuplicateNumbers(new int[] { 1, 4, 3, 3 });
    }

    /**
     * Using HashMap
     * @param inputArray
     * @param arrayLength
     */
    void findMissingAndDuplicateNumber(int[] inputArray, int arrayLength) {
        HashMap<Integer, Integer> storage = new HashMap<>();
        for (int number : inputArray) {
            storage.put(number, storage.get(number) == null ? 1 : storage.get(number) + 1);
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
     * Using BitSet
     * @param inputArray
     * @param arrayLength
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
     * @param inputArray
     */
    <T>  void findDuplicateNumbers(int[] inputArray) {
        Set<Integer> storage = new HashSet<>(inputArray.length);
        System.out.print("duplicateNumbers::");
        for (int number : inputArray) {
            if (!storage.add(number)) {
                System.out.print(number + "   ");
            }
        }
    }
}
