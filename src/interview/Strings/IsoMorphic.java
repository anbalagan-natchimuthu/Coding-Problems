package interview.Strings;

import java.util.Arrays;

/* Two strings str1 and str2 are called isomorphic if there is a one to one mapping possible for every character
    of str1 to every character of str2. And all occurrences of every character in ‘str1’ map to same character in ‘str2’

    Examples:

    Input:  str1 = "aab", str2 = "xxy"
    Output: True
    'a' is mapped to 'x' and 'b' is mapped to 'y'.

    Input:  str1 = "aab", str2 = "xyz"
    Output: False
    One occurrence of 'a' in str1 has 'x' in str2 and
    other occurrence of 'a' has 'y'.
 */
public class IsoMorphic {

    public static void main(String[] args) {
        System.out.println("Is Ismorphic " + areIsomorphicString("egf", "add"));
        System.out.println("Is Ismorphic " + areIsomorphicString("foo", "bar"));
        System.out.println("Is Ismorphic " + areIsomorphicString("abca", "zbxz"));
        System.out.println("Is Ismorphic " + areIsomorphic("ppierasdadasdasdasdasdasdasda", "titleasdasdasdasdasdasdasdasd"));
    }

    public static boolean areIsomorphicString(String s1, String s2) {
        int[] st1Array = new int[128];
        int[] st2Array = new int[128];
        int mask = 1;
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (st1Array[s1.charAt(i)] != st1Array[s2.charAt(i)]) {
                return false;
            }
            st1Array[s1.charAt(i)] = mask;
            st1Array[s2.charAt(i)] = mask;
            mask++;
        }
        return true;
    }

    // Function returns true if str1 and str2 are ismorphic
    static boolean areIsomorphic(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Length of both strings must be same for one to one
        // corresponance
        if (m != n) {
            return false;
        }

        // To mark visited characters in str2
        boolean[] marked = new boolean[256];

        // To store mapping of every character from str1 to
        // that of str2. Initialize all entries of map as -1.
        int[] map = new int[256];
        Arrays.fill(map, -1);

        // Process all characters one by on
        for (int i = 0; i < n; i++) {
            // If current character of str1 is seen first time in it.
            if (map[str1.charAt(i)] == -1) {
                // If current character of str2 is already
                // seen, one to one mapping not possible
                if (marked[str2.charAt(i)] == true) {
                    return false;
                }

                // Mark current character of str2 as visited
                marked[str2.charAt(i)] = true;

                // Store mapping of current characters
                map[str1.charAt(i)] = str2.charAt(i);
            }

            // If this is not first appearance of current
            // character in str1, then check if previous
            // appearance mapped to same character of str2
            else if (map[str1.charAt(i)] != str2.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
