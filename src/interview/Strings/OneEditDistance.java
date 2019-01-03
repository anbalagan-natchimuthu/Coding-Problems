package interview.Strings;

/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 *
 */
public class OneEditDistance {

    public static void main(String[] args) {
        boolean isOneEdit = isOneEditDistance("cat", "dog");
        System.out.println(isOneEdit);
        boolean isOneEdit1 = isOneEditDistance("cat", "cats");
        System.out.println(isOneEdit1);
        boolean isOneEdit2 = isOneEditDistance("cat", "cut");
        System.out.println(isOneEdit2);

        boolean isOneEdit3 = isOneEditApart("catsts", "casts");
        System.out.println(isOneEdit3);
    }

    public static boolean isOneEditDistance(String s, String t) {
        int len = Math.min(s.length(), t.length());
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(i + 1)); // replace
                } else if (s.length() < t.length()) {
                    return s.substring(i).equals(t.substring(i + 1)); // delete t
                } else {
                    return s.substring(i + 1).equals(t.substring(i)); // delete s
                }
            }
        }
        return Math.abs(s.length() - t.length()) == 1; // corner case: ""
    }

    private static boolean isOneEditApart(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }

        String smaller, larger;
        if (s.length() <= t.length()) {
            smaller = s;
            larger = t;
        } else {
            smaller = t;
            larger = s;
        }

        boolean isDiffFound = false;
        int j = 0;
        for (int i = 0; i < smaller.length(); ) {
            if (smaller.charAt(i) != larger.charAt(j)) {
                if (isDiffFound) {
                    return false;
                } else {
                    isDiffFound = true;
                    if (smaller.length() == larger.length()) {
                        i++;
                        j++;
                    } else {
                        j++;
                    }
                }
            } else {
                i++;
                j++;
            }
        }

        return isDiffFound || j < larger.length();
    }
}
