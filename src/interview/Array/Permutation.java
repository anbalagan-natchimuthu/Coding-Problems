package interview.Array;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=bGC2fNALbNU Find and print all subsets of a given set! (Given as an array.)
 * <p>
 * e.g. given array {1,2} print all subsets {}, {1}, {2}, {1,2}
 */
public class Permutation {

  public static void main(String[] args) {
    int[] input = {1, 2};
    Integer[] subSet = new Integer[input.length];
    printSubSet(input, subSet, 0);
  }

  private static void printSubSet(int[] inputArr, Integer[] subsetArr, int i) {
    if (i == inputArr.length) {
      System.out.println(Arrays.toString(subsetArr));
    } else {

      // Not including current element in the subset
      subsetArr[i] = null;
      printSubSet(inputArr, subsetArr, i + 1);

      // Including current element in the subset
      subsetArr[i] = inputArr[i];
      printSubSet(inputArr, subsetArr, i + 1);
    }
  }
}
