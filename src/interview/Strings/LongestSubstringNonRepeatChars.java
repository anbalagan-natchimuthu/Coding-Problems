package interview.Strings;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Examples:
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 *
 * Given "bbbbb", the answer is "b", with the length of 1.
 *
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringNonRepeatChars {

  public static void main(String[] args) {
    System.out.println("lengthOfLongestSubstring:" + lengthOfLongestSubstring("abccbabb"));
    System.out.println("lengthOfLongestSubstringUsingHashMap:" + lengthOfLongestSubstringUsingHashMap("abcdedxybvm"));
    System.out.println("lengthOfLongestSubstringUsingTable:" + lengthOfLongestSubstringUsingTable("abcdefbdefckhc"));

    System.out.println(
        "Longest Substring with At Most Two Distinct Characters::" + LongestSubStringWithTwoDistinctCharacters(
            "eceba"));
  }

  /*
      Time complexity : O(2n) = O(n)O(2n)=O(n). In the worst case each character will be visited twice by ii and jj
      Space complexity : O(min(m, n))O(min(m,n))
   */
  public static int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    while (i < n && j < n) {
      // try to extend the range [i, j]
      if (!set.contains(s.charAt(j))) {
        set.add(s.charAt(j++));
        ans = Math.max(ans, j - i);
      } else {
        // Remove all characters from i to until current matching char (j)
        set.remove(s.charAt(i++));
      }
    }
    return ans;
  }

  /*
   * no assumption on the charset of the string
   * Better solution using hashmap
   */

  public static int lengthOfLongestSubstringUsingHashMap(String s) {
    int n = s.length(), maxLength = 0;
    Map<Character, Integer> map = new HashMap<>(); // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(map.get(s.charAt(j)), i);
      }
      maxLength = Math.max(maxLength, j - i + 1);
      map.put(s.charAt(j), j + 1);
    }
    return maxLength;
  }

  /**
   * https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
   * The previous implements all have no assumption on the charset of the string s.
   *
   * If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.
   *
   * Commonly used tables are:
   *
   * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
   * int[128] for ASCII
   * int[256] for Extended ASCII
   */

  public static int lengthOfLongestSubstringUsingTable(String s) {
    int n = s.length(), ans = 0;
    int[] index = new int[128]; // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      i = Math.max(index[s.charAt(j)], i);
      ans = Math.max(ans, j - i + 1);
      index[s.charAt(j)] = j + 1;
    }
    return ans;
  }

  /**
   * PROBLEM: 2
   * Longest Substring with At Most Two Distinct Characters
   * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
   *
   * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
   *
   * Example 1:
   *
   * Input: "eceba"
   * Output: 3
   * Explanation: t is "ece" which its length is 3.
   */

  public static int LongestSubStringWithTwoDistinctCharacters(String inputStr) {
    Map<Character, Integer> map = new HashMap<>();

    int j = 0;
    int maxLength = 0;
    for (int i = 0; i < inputStr.length(); i++) {
      if (map.size() <= 2) {
        map.put(inputStr.charAt(i), i);
      }

      if (map.size() > 2) {
        int lowIndex = Collections.min(map.values());
        map.remove(inputStr.charAt(lowIndex));
        j = lowIndex + 1;
      }
      maxLength = Math.max(maxLength, i - j + 1);
    }
    return maxLength;
  }

  /**
   * PROBLEM: 2.1 Without using HashMap
   * length of longest subarray that contains up to two distinct integers
   * https://leetcode.com/problems/fruit-into-baskets/
   * https://leetcode.com/problems/fruit-into-baskets/discuss/170745/Problem%3A-Longest-Subarray-With-2-Elements
   *
   * Example 2:
   *
   * Input: [0,1,2,2]
   * Output: 3
   * Explanation: We can collect [1,2,2].
   * If we started at the first tree, we would only collect [0, 1].
   */
  public static int LongestSubArrayWithTwoDistinctIntegers(int[] inputArr) {
   int firstInteger = -1;
   int secondInteger = -1;
   int totalCount = 0;
   int max = 0;
   int currMax = 0;

   for (int value : inputArr) {
     // Current Value is same as first / second integer
     if (value == firstInteger || value == secondInteger) {
       currMax++;
     } else {
       // Got new integer which is not same as first and second
       currMax = totalCount + 1;
     }

     // If current Integer value is matching with first integer, then increment the total count
     if (value == secondInteger) {
       totalCount++;
     } else {
       totalCount = 1;
     }

     if (value != secondInteger) {
       firstInteger = secondInteger;
       secondInteger = value;
     }
     max = Math.max(max, currMax);
   }
   return max;
  }
}
