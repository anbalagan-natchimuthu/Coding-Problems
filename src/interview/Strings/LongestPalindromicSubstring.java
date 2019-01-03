package interview.Strings;

/**
 * PROBLEM :1
 * Given a string, find the longest substring which is palindrome.
 * For example, if the given string is “forgeeksskeegfor”, the output should be “geeksskeeg”.
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println("longestPalindrome:" + longestPalSubstring("abba"));
        System.out.println("Length is: " + longestPalSubstrUsingDB("abba"));
        System.out.println("longest Palindromic subsequence:" + longestPalindromicSubsequence("agbdba"));
    }

    // A utility function to print a substring str[low..high]
    static void printSubStr(String str, int low, int high) {
        System.out.println(str.substring(low, high));
    }

    // This function prints the longest palindrome substring
    // of str[].
    // It also returns the length of the longest palindrome
    // https://www.geeksforgeeks.org/?p=25463
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
                if (table[i + 1][j - 1] && str.charAt(i) ==
                    str.charAt(j)) {
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

    // Java implementation of O(n^2) time and O(1) space method
    // to find the longest palindromic substring
    // This function prints the longest palindrome substring
    // (LPS) of str[]. It also returns the length of the
    // longest palindrome
    //https://www.geeksforgeeks.org/longest-palindromic-substring-set-2/
    static int longestPalSubstring(String str) {
        int maxLength = 1; // The result (length of LPS)

        int start = 0;
        int len = str.length();

        int low, high;

        // One by one consider every character as center
        // point of even and length palindromes
        for (int i = 1; i < len; ++i) {
            // Find the longest even length palindrome with
            // center points as i-1 and i.
            low = i - 1;
            high = i;
            while (low >= 0 && high < len
                && str.charAt(low) == str.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }

            // Find the longest odd length palindrome with
            // center point as i
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < len
                && str.charAt(low) == str.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }
        }

        System.out.print("Longest palindrome substring is: ");
        printSubStr(str, start, start + maxLength);

        return maxLength;
    }

    /**
     * https://www.youtube.com/watch?v=_nCsPn7_OgI
     */
    public static int longestPalindromicSubsequence(String inputString) {
        int length = inputString.length();
        int[][] table = new int[length][length];
        int palindromicStringLength = 1;

        // All substrings of length 1 are palindromes (diagonal values)
        for (int i=0; i<length; i++) {
            table[i][i] = 1;
        }

        for(int i=0; i<length-1; i++) {
            if (inputString.charAt(i) == inputString.charAt(i + 1)) {
                table[i][i+1] = 2;
                palindromicStringLength = 2;
            }
        }

        for (int noOfChars = 3; noOfChars <= length; noOfChars++) {
            for (int startIndex = 0; startIndex < length - noOfChars + 1; startIndex++) {
                int endIndex = startIndex + noOfChars - 1;

                if (inputString.charAt(startIndex) == inputString.charAt(endIndex)) {
                    table[startIndex][endIndex] = 2 + table[startIndex + 1] [endIndex -1];
                } else {
                    table[startIndex][endIndex] = Math.max(table[startIndex][endIndex-1], table[startIndex+1][endIndex]);
                }

                if (table[startIndex][endIndex] > palindromicStringLength) {
                    palindromicStringLength = table[startIndex][endIndex];
                }
            }
        }
        return palindromicStringLength;
    }
}
