package interview.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem: 1
 * https://leetcode.com/problems/pascals-triangle/
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 * Input: 5
 * Output:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalsTriangle {

  public static List<List<Integer>> generate(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>();

    // First base case; if user requests zero rows, they get zero rows.
    if (numRows == 0) {
      return triangle;
    }

    // Second base case; first row is always [1].
    triangle.add(new ArrayList<>());
    triangle.get(0).add(1);

    for (int rowNum = 1; rowNum < numRows; rowNum++) {
      List<Integer> currRow = new ArrayList<>();
      List<Integer> prevRow = triangle.get(rowNum - 1);

      // The first row element is always 1.
      currRow.add(1);

      // Each triangle element (other than the first and last of each row)
      // is equal to the sum of the elements above-and-to-the-left and
      // above-and-to-the-right.
      for (int j = 1; j < rowNum; j++) {
        currRow.add(prevRow.get(j - 1) + prevRow.get(j));
      }

      // The last row element is always 1.
      currRow.add(1);

      triangle.add(currRow);
    }

    return triangle;
  }

  public static void main(String[] args) {
    List<List<Integer>> result = generate(5);
    for (List<Integer> list : result) {
      System.out.println(list.toString());
    }

    System.out.println("**********************");

    System.out.println(generateTriangle(5).toString());
  }

  /**
   * PROBLEM: 2   https://leetcode.com/problems/pascals-triangle-ii/
   *
   * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
   *
   * Note that the row index starts from 0.
   *
   * Input: 3
   * Output: [1,3,3,1]
   *
   * runtime is O(k^2) and the space complexity is O(k).
   * k iterations will go through elements these many times:
   * 1 (first iteration) + 2 (second iteration) + .. k (kth iteration) = k(k+1)/2 i.e., O(k^2)
   */
  public static List<Integer> generateTriangle(int rows) {
    int[] result = new int[rows + 1];
    result[0] = 1;

    for (int row = 1; row <= rows; row++) {
      for (int j = row; j >= 1; j--) {
        result[j] += result[j - 1];
      }
    }
    return Arrays.stream(result).boxed().collect(Collectors.toList());
  }
}
