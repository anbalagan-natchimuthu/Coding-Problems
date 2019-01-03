package interview.Strings;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".

 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0.
 */
public class WildCardMatching {

    public static void main(String args[]) {
        WildCardMatching wcm = new WildCardMatching();
        System.out.println(wcm.isMatch("xbylmz", "x?y*z"));
    }

    private boolean isMatch(String inputString, String pattern) {
        char[] inputStringArr = inputString.toCharArray();
        char[] patternArr = pattern.toCharArray();

        //replace multiple * with one *
        //e.g a**b***c --> a*b*c
        boolean isFirst = true;
        int writeIndex = 0;
        for(int i = 0; i < patternArr.length; i++) {
            if (patternArr[i] == '*') {
                if (isFirst) {
                    patternArr[writeIndex ++] = patternArr[i];
                    isFirst = false;
                }
            } else {
                patternArr[writeIndex ++] = patternArr[i];
                isFirst = true;
            }
        }

        boolean T[][] = new boolean[inputStringArr.length + 1][writeIndex + 1];

        if (writeIndex > 0 && patternArr[0] == '*') {
            T[0][1] = true;
        }

        T[0][0] = true;

        for (int i= 1; i< T.length; i++) {
            for (int j= 1; j< T[0].length; j++) {
                // inputStringArr and patternArr are start from 0 but T[i][j] starts from 1.
                // So, here we are comparing inputStringArr[i] == patternArr[j]
                if (patternArr[j-1] == '?' || inputStringArr[i-1] == patternArr[j-1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (patternArr[j-1] == '*'){
                    T[i][j] = T[i-1][j] || T[i][j-1];
                }
            }
        }
        return T[inputStringArr.length][writeIndex];
    }
}
