package interview.Strings;

/**
 * https://www.youtube.com/watch?v=l3hda49XcDE
 * <p>
 * Write a function that takes two strings as arguments, s and p and returns a boolean denoting whether s matches p
 * <p>
 * P is a sequence of any of the following:
 *
 *  1. a-z -- which stands for itself
 *  2. '.' -- which matches any character
 *  3. '*' -- which matches 0 or more occurrences of the previous single character
 */
public class RegexMatching {

    public static void main(String args[]) {
        RegexMatching rm = new RegexMatching();
        System.out.println(rm.isMatch("Tushar".toCharArray(), "Tushar".toCharArray()));
        System.out.println(rm.isMatch("Tusha".toCharArray(), "Tushar*a*b*".toCharArray()));
        System.out.println(rm.isMatch("".toCharArray(), "a*b*".toCharArray()));
        System.out.println(rm.isMatch("abbbbccc".toCharArray(), "a*ab*bbbbc*".toCharArray()));
        System.out.println(rm.isMatch("abbbbccc".toCharArray(), "aa*bbb*bbbc*".toCharArray()));
        System.out.println(rm.isMatch("abbbbccc".toCharArray(), ".*bcc".toCharArray()));
        System.out.println(rm.isMatch("abbbbccc".toCharArray(), ".*bcc*".toCharArray()));
        System.out.println(rm.isMatch("abbbbccc".toCharArray(), ".*bcc*".toCharArray()));
        System.out.println(rm.isMatch("aaa".toCharArray(), "ab*a*c*a".toCharArray()));

        System.out.println(rm.isMatch("by".toCharArray(), "a*b.*y".toCharArray()));
    }

    private boolean isMatch(char[] inputString, char[] pattern) {
        boolean[][] T = new boolean[inputString.length + 1][pattern.length + 1];

        // Empty String matches with Empty RegEx
        T[0][0] = true;

        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) {
            //assume that there is no paatern String starts with *.
            if (pattern[i - 1] == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == inputString[i - 1]) {
                    T[i][j] = T[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    T[i][j] = T[i][j - 2];
                    if (pattern[j - 2] == '.' || pattern[j - 2] == inputString[i - 1]) {
                        T[i][j] = T[i][j] || T[i - 1][j];
                    }
                }
            }
        }
        return T[inputString.length][pattern.length];
    }
}
