package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PROBLEM : 1 Solution: 1
 * Given a digit string, return all possible letter combinations that the number could represent.
 * (Check out your cellphone to see the mappings) Input:Digit string "23", Output: ["ad", "ae", "af", "bd", "be",
 * "bf", "cd", "ce", "cf"].
 *
 * https://www.geeksforgeeks.org/find-possible-words-phone-digits/
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/solution/
 *
 * Time complexity : O(3^N 4^M)
 * where N is the number of digits in the input that maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8)
 * and M is the number of digits in the input that maps to 4 letters (e.g. 7, 9),
 * and N+M is the total number digits in the input.
 *
 * Space complexity : O(3^N 4^M) since one has to keep 3^N  4^M solutions.
 */
public class LetterCombPhoneNumber {

  public static void main(String[] args) {
    LetterCombPhoneNumber lc = new LetterCombPhoneNumber();
    System.out.println(lc.letterCombinations("23"));

    System.out.println(letterCombinationsIterative("23"));

    List<List<Integer>> input = new ArrayList<>();
    input.add(Arrays.asList(1, 2, 3, 10));
    input.add(Arrays.asList(4, 5));
    input.add(Arrays.asList(7, 8, 9));
    int[] tempArray = new int[input.size()];
    List<List<Integer>> resultList = new ArrayList<>();

    combinations(input, tempArray, 0, resultList);

    for (int i = 0; i < resultList.size(); i++) {
      System.out.println(resultList.get(i));
    }
  }

  public List<String> letterCombinations(String digits) {
    HashMap<Integer, char[]> dict = new HashMap<>();
    dict.put(2, new char[]{'a', 'b', 'c'});
    dict.put(3, new char[]{'d', 'e', 'f'});
    dict.put(4, new char[]{'g', 'h', 'i'});
    dict.put(5, new char[]{'j', 'k', 'l'});
    dict.put(6, new char[]{'m', 'n', 'o'});
    dict.put(7, new char[]{'p', 'q', 'r', 's'});
    dict.put(8, new char[]{'t', 'u', 'v'});
    dict.put(9, new char[]{'w', 'x', 'y', 'z'});

    List<String> result = new ArrayList<>();
    if (digits == null || digits.length() == 0) {
      return result;
    }

    char[] arr = new char[digits.length()];
    helper(digits, 0, dict, result, arr);

    return result;
  }

  private void helper(String digits, int index, HashMap<Integer, char[]> dict, List<String> result, char[] arr) {
    if (index == arr.length) {
      result.add(new String(arr));
      return;
    }

    int number = digits.charAt(index) - '0';
    char[] candidates = dict.get(number);
    for (int i = 0; i < candidates.length; i++) {
      arr[index] = candidates[i];
      helper(digits, index + 1, dict, result, arr);
    }
  }

  /**
   * Solutions: 2
   * For time complexity, adding one more letter, 4 (for number 7) times more operations, so it is O(4^n).
   * For space complexity, final output will take O(4^n) space.
   */
  public static List<String> letterCombinationsIterative(String digits) {
    LinkedList<String> ans = new LinkedList<>();
    if (digits == null || digits.isEmpty() || digits.indexOf('0') != -1 || digits.indexOf('1') != -1) {
      return ans;
    }
    String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    ans.add("");
    for (int i = 0; i < digits.length(); i++) {
      int x = Character.getNumericValue(digits.charAt(i));
      while (ans.peek().length() == i) {
        String t = ans.remove();
        for (char s : mapping[x].toCharArray()) {
          ans.add(t + s);
        }
      }
    }
    return ans;
  }


  /**
   * PROBLEM : 2
   * Generate all possible combinations for Lis<List<Integer>>
   */
  private static void combinations(List<List<Integer>> inputList, int[] tempArray, int level,
      List<List<Integer>> results) {

    if (tempArray.length == level) {
      List<Integer> tempList = Arrays.stream(tempArray).boxed().collect(Collectors.toList());
      results.add(tempList);
      return;
    }

    for (int i = 0; i < inputList.get(level).size(); i++) {
      tempArray[level] = inputList.get(level).get(i);
      combinations(inputList, tempArray, level + 1, results);
    }
  }
}
