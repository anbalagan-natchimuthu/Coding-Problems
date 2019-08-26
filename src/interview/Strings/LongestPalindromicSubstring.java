package interview.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM :1
 * Given a string, find the longest substring which is palindrome.
 * For example, if the given string is “forgeeksskeegfor”, the output should be “geeksskeeg”.
 */
public class LongestPalindromicSubstring {

  public static void main(String[] args) {
    System.out.println("longestPalindrome:" + longestPalSubstring("million"));
    System.out.println("Length is: " + longestPalSubstrUsingDB("million"));
    System.out.println("longest Palindromic subsequence:" + longestPalindromicSubsequence("agbdba"));

    System.out.println("Count of Palindromic Substrings in a String::" + countPalindromicSubStrings("aaa"));
    System.out.println(
        "Count of Unique Palindromic Substrings in a String::" + countUniquePalindromicSubStrings("aaa"));
  }

  // A utility function to print a substring str[low..high]
  static void printSubStr(String str, int low, int high) {
    System.out.println(str.substring(low, high));
  }

  // This function prints the longest palindrome substring
  // of str[].
  // It also returns the length of the longest palindrome
  // https://www.geeksforgeeks.org/?p=25463
  // Time Complexity O(n^2) and Space Complexity O(n^2)

  static int longestPalSubstrUsingDB(String str) {
    int n = str.length();   // get length of input string

    // table[i][j] will be false if substring str[i..j]
    // is not palindrome.
    // Else table[i][j] will be true
    boolean table[][] = new boolean[n][n];

    // All substrings of length 1 are palindromes (diagonal values)
    int maxLength = 1;
    for (int i = 0; i < n; ++i) {
      table[i][i] = true;
    }

    // check for sub-string of length 2.
    int start = 0;
    for (int i = 0; i < n - 1; ++i) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        table[i][i + 1] = true;
        start = i;
        maxLength = 2;
      }
    }

    // Check for lengths greater than 2. k is length
    // of substring
    for (int k = 3; k <= n; ++k) {

      // Fix the starting index
      for (int i = 0; i < n - k + 1; ++i) {
        // Get the ending index of substring from
        // starting index i and length k
        int j = i + k - 1;

        // checking for sub-string from ith index to
        // jth index if str.charAt(i+1) to
        // str.charAt(j-1) is a palindrome
        if (table[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
          table[i][j] = true;

          if (k > maxLength) {
            start = i;
            maxLength = k;
          }
        }
      }
    }
    System.out.print("Longest palindrome substring is; ");
    printSubStr(str, start, start + maxLength);

    return maxLength; // return length of LPS
  }

  /**
   * https://leetcode.com/problems/longest-palindromic-substring/solution/
   *
   * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
   * and there are only 2n - 12n−1 such centers
   * Time complexity : O(n^2). Since expanding a palindrome around its center could take O(n) time, the overall
   * complexity is O(n^2).
   *
   * Space complexity : O(1).
   */
  private static int longestPalSubstring(String str) {
    int maxLength = 1; // The result (length of LPS)

    int start = 0, end = 0;

    // One by one consider every character as center
    // point of even and odd length palindromes
    for (int i = 0; i < str.length(); i++) {
      int len1 = expandAroundCenter(str, i, i); // odd length
      int len2 = expandAroundCenter(str, i, i + 1); // even length
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
        maxLength = end - start;
      }
    }
    System.out.println(str.substring(start, end + 1));
    return maxLength;
  }

  private static int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
      L--;
      R++;
    }
    return R - L - 1;
  }

  /**
   * PROBLEM: 2
   * https://leetcode.com/problems/palindromic-substrings/
   * Given a string, your task is to count how many palindromic substrings in this string.
   *
   * The substrings with different start indexes or end indexes are counted as different substrings even they consist
   * of same characters.
   *
   * Example 1:
   *
   * Input: "abc"
   * Output: 3
   * Explanation: Three palindromic strings: "a", "b", "c".
   *
   *
   * Example 2:
   *
   * Input: "aaa"
   * Output: 6
   * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
   */
  public static int countPalindromicSubStrings(String s) {
    boolean[][] table = new boolean[s.length()][s.length()];
    int totalCount = 0;

    // count for all single characters
    for (int i = 0; i < s.length(); i++) {
      table[i][i] = true;
      totalCount++;
    }

    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        table[i][i + 1] = true;
        totalCount++;
      }
    }

    for (int len = 3; len <= s.length(); len++) {
      for (int i = 0; i <= s.length() - len; i++) {
        int j = i + len - 1;

        if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1]) {
          table[i][j] = true;
          totalCount++;
        }
      }
    }
    return totalCount;
  }

  /**
   * Count of Unique Polyndromic substrings
   */
  public static int countUniquePalindromicSubStrings(String s) {

    Set<String> uniqueStrings = new HashSet<>();
    int count = 0;

    for (int i = 0; i < s.length() - 1; i++) {
      count = expandAroundCenter(s, i, i, uniqueStrings, count);
      count = expandAroundCenter(s, i, i + 1, uniqueStrings, count);
    }
    return count;
  }

  private static int expandAroundCenter(String s, int left, int right, Set<String> uniqueStrings, int count) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      if (!uniqueStrings.contains(s.substring(left, right + 1))) {
        count++;
        uniqueStrings.add(s.substring(left, right + 1));
      }
      left--;
      right++;
    }
    return count;
  }

  /**
   * PROBLEM: 3
   * Given a string, find longest palindromic subsequence in this string.
   * e.g. for input String "agbdba" return length as 5 (abdba)
   * https://www.youtube.com/watch?v=_nCsPn7_OgI
   */
  public static int longestPalindromicSubsequence(String inputString) {
    int length = inputString.length();
    int[][] table = new int[length][length];
    int palindromicStringLength = 1;

    // All substrings of length 1 are palindromes (diagonal values)
    for (int i = 0; i < length; i++) {
      table[i][i] = 1;
    }

    for (int i = 0; i < length - 1; i++) {
      if (inputString.charAt(i) == inputString.charAt(i + 1)) {
        table[i][i + 1] = 2;
        palindromicStringLength = 2;
      }
    }

    for (int noOfChars = 3; noOfChars <= length; noOfChars++) {
      for (int startIndex = 0; startIndex < length - noOfChars + 1; startIndex++) {
        int endIndex = startIndex + noOfChars - 1;

        if (inputString.charAt(startIndex) == inputString.charAt(endIndex)) {
          table[startIndex][endIndex] = 2 + table[startIndex + 1][endIndex - 1];
        } else {
          table[startIndex][endIndex] = Math.max(table[startIndex][endIndex - 1], table[startIndex + 1][endIndex]);
        }

        if (table[startIndex][endIndex] > palindromicStringLength) {
          palindromicStringLength = table[startIndex][endIndex];
        }
      }
    }
    return palindromicStringLength;
  }
}
