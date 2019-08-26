package interview.Strings;

/**
 * PROBLEM:1 Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
 * So a string of length n has 2^n different possible subsequences.
 * https://www.youtube.com/watch?v=NnD96abizww.
 */
public class LongestCommonSubsequence {

  public int lcs(char str1[], char str2[], int len1, int len2) {

    if (len1 == str1.length || len2 == str2.length) {
      return 0;
    }
    if (str1[len1] == str2[len2]) {
      return 1 + lcs(str1, str2, len1 + 1, len2 + 1);
    } else {
      return Math.max(lcs(str1, str2, len1 + 1, len2), lcs(str1, str2, len1, len2 + 1));
    }
  }

  public int lcsDynamic(char str1[], char str2[]) {

    int temp[][] = new int[str1.length + 1][str2.length + 1];
    int max = 0;
    for (int i = 1; i < temp.length; i++) {
      for (int j = 1; j < temp[i].length; j++) {
        if (str1[i - 1] == str2[j - 1]) {
          temp[i][j] = temp[i - 1][j - 1] + 1;
        } else {
          temp[i][j] = Math.max(temp[i][j - 1], temp[i - 1][j]);
        }
        if (temp[i][j] > max) {
          max = temp[i][j];
        }
      }
    }
    return max;
  }

  /**
   * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
   *
   * Problem: 2
   * find Longest Contiguous Substring matching
   * e.g.
   * Str1[] = {"apple", "orange", "banana", "blueberry", "pears", "dates"}
   * Str2[] = {"dates", "pears", "orange", "banana", "blueberry", "apple"}
   * output: 3 "orange", "banana" and "blueberry"
   */
  public int longestContiguousSubstring(String str1[], String str2[]) {

    int temp[][] = new int[str1.length + 1][str2.length + 1];
    int max = 0;
    for (int i = 1; i < temp.length; i++) {
      for (int j = 1; j < temp[i].length; j++) {
        if (str1[i - 1].equals(str2[j - 1])) {
          temp[i][j] = temp[i - 1][j - 1] + 1;
        }
        if (temp[i][j] > max) {
          max = temp[i][j];
        }
      }
    }
    return max;
  }

  // ***************************************************************************
  // PROBLEM:2
    /*
    Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
    where in each step you can delete one character in either string.
    Input: "sea", "eat"
    Output: 2
    Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
     */

  private int minDistance(char[] word1, char[] word2) {
    int longest = lcs(word1, word2, 0, 0);
    return word1.length - longest + word2.length - longest;
  }

  /**
   * PROBLEM 3: Given two strings how many minimum edits(update, delete or add) is needed to convert one string to
   * another
   * Time complexity is O(m*n)
   * Space complexity is O(m*n)
   *
   * References:
   * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
   * https://www.youtube.com/watch?v=We3YDTzNXEk
   */

  private int minimumEdits(char[] str1, char[] str2) {
    int[][] table = new int[str1.length + 1][str2.length + 1];

    for (int i = 0; i < table[0].length; i++) {
      table[0][i] = i;
    }

    for (int i = 0; i < table.length; i++) {
      table[i][0] = i;
    }

    for (int i = 1; i <= str1.length; i++) {
      for (int j = 1; j <= str2.length; j++) {
        if (str1[i - 1] == str2[j - 1]) {
          table[i][j] = table[i - 1][j - 1];
        } else {
          table[i][j] = 1 + Math.min(Math.min(table[i - 1][j - 1], table[i][j - 1]), table[i - 1][j]);
        }
      }
    }
    printActualEdits(table, str1, str2);
    return table[str1.length][str2.length];
  }

  /**
   * Prints the actual edits which needs to be done.
   */
  public void printActualEdits(int T[][], char[] str1, char[] str2) {
    int i = T.length - 1;
    int j = T[0].length - 1;
    while (true) {
      if (i == 0 || j == 0) {
        break;
      }
      if (str1[i - 1] == str2[j - 1]) {
        i = i - 1;
        j = j - 1;
      } else if (T[i][j] == T[i - 1][j - 1] + 1) {
        System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
        i = i - 1;
        j = j - 1;
      } else if (T[i][j] == T[i - 1][j] + 1) {
        System.out.println("Delete in string1 " + str1[i - 1]);
        i = i - 1;
      } else if (T[i][j] == T[i][j - 1] + 1) {
        System.out.println("Delete in string2 " + str2[j - 1]);
        j = j - 1;
      } else {
        throw new IllegalArgumentException("Some wrong with given data");
      }
    }
  }

  public static void main(String args[]) {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();
    String str1 = "ABCDGHLQR";
    String str2 = "AEDPHR";

    int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
    System.out.println(result);

    String[] s1 = new String[]{"apple", "orange", "banana", "blueberry", "pears", "dates"};
    String[] s2 = new String[]{"dates", "pears", "orange", "banana", "blueberry", "apple"};
    System.out.println("Longest Contiguous array elements : " + lcs.longestContiguousSubstring(s1, s2));

    System.out.println("Minimum Delete operation:" + lcs.minDistance("seau".toCharArray(), "eat".toCharArray()));
    System.out.println(
        "Minimum operations (add, delete, update):" + lcs.minimumEdits("a".toCharArray(), "b".toCharArray()));
  }
}
