package interview.Strings;

public class Palindrome {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(findPalindrome("45312343214344543543"));
    //findPalindrome("anna");
    System.out.println(palindromeIndex("aaab"));
    System.out.println(palindromeIndex("abdbca"));
  }

  public static String findPalindrome(String str) {
    int longStart = 0;
    int longEnd = 0;
    for (int i = 0; i < str.length(); i++) {
      for (int j = i + 1; j <= str.length(); j++) {
        //System.out.println(i + ":" + j+":"+str.substring(i, j));
        if (checkPalindrome(str.substring(i, j)) && (j - i > longEnd - longStart)) {
          //System.out.println(str.substring(i, j));
          longEnd = j;
          longStart = i;
        }
      }
    }
    return str.substring(longStart, longEnd);
  }

  public static boolean checkPalindrome(String input) {
    if (input.length() < 2) {
      return false;
    }
    int j = input.length() - 1;
    //System.out.println(input);
    for (int i = 0; i < input.length() / 2; i++) {
      //System.out.println(input.charAt(i)+":"+input.charAt(j));
      if (input.charAt(i) != input.charAt(j--)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Given a string of lowercase letters in the range ascii[a-z], determine a character that can be removed to make
   * the string a palindrome. There may be more than one solution, but any will do. For example, if your string is
   * "bcbc", you can either remove 'b' at index  or 'c' at index . If the word is already a palindrome or there is no
   * solution, return -1. Otherwise, return the index of a character to remove.
   *
   * Input: aaab
   * Output: 3 (Removing 'b' at index  results in a palindrome, so we print  on a new line)
   *
   * Input: baa
   * Output: 0 (Removing 'b' at index  results in a palindrome, so we print  on a new line)
   *
   * Input: aaa
   * Output: -1 (This string is already a palindrome, so we print .
   * Removing any one of the characters would result in a palindrome, but this test comes first.)
   */

  static int palindromeIndex(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) {
        if (isPalindrome(s.substring(0, i) + s.substring(i + 1))) {
          return i;
        }
        return j;
      }
    }
    return -1;
  }

  public static boolean isPalindrome(String str) {
    int i = 0, j = str.length() - 1;

    while (i < j) {
      if (str.charAt(i++) != str.charAt(j--)) {
        return false;
      }
    }
    return true;
  }
}
