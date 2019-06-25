package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.youtube.com/watch?v=qli-JCrSwuk
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class NumberOfWaysDecodings {

  public int numDecodings(String s) {

    int[] memo = new int[s.length() + 1];
    Arrays.fill(memo, -1);

    return findWays(s, s.length(), memo);
  }

  private int findWays(String data, int length, int[] memo) {
    if (length == 0) {
      return 1;
    }

    int pos = data.length() - length;
    if (data.charAt(pos) == '0') {
      return 0;
    }

    if (memo[length] != -1) {
      return memo[length];
    }

    int returnVal = findWays(data, length - 1, memo);
    if (length >= 2 && Integer.parseInt(data.substring(pos, pos + 2)) <= 26) {
      returnVal += findWays(data, length - 2, memo);
    }
    memo[length] = returnVal;
    return returnVal;
  }

  /**
   * PROBLEM: 2
   * Return all possible combinations for String 123 where 1 mapped to 'a', 2 mapped to 'b' and 26 mapped to 'z'.
   * e.g. 123 {abc, aw, lc}
   */

  public static List<String> findAllCombinations(String str) {
    List<String> resList = new ArrayList<>();
    findCombinationsHelper(str, 0, resList, new ArrayList<>());
    return resList;
  }

  private static void findCombinationsHelper(String str, int index, List<String> resList, List<Character> tempList) {
    if (index == str.length()) {
      resList.add(tempList.stream().map(e->e.toString()).collect(Collectors.joining()));
      return;
    }

    int val = str.charAt(index) - '0';

    if (val != 0) {
      tempList.add((char) (val + 96));
      findCombinationsHelper(str, index + 1, resList, tempList);
      tempList.remove(tempList.size() - 1);
    }

    if ( index + 2 <= str.length()) {
      val = Integer.parseInt(str.substring(index, index + 2));

      if (val > 0 && val <= 26) {
        tempList.add((char) (val + 96));
        findCombinationsHelper(str, index + 2, resList, tempList);
        tempList.remove(tempList.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    NumberOfWaysDecodings solution = new NumberOfWaysDecodings();
    System.out.println("Result::" + solution.numDecodings("20"));
    System.out.println("Result::" + solution.numDecodings("11111"));


    List<String> res = findAllCombinations("123");
    System.out.println(res.toString());
  }
}
