package interview.Strings;

/**
 * https://www.geeksforgeeks.org/check-if-frequency-of-all-characters-can-become-same-by-one-removal/
 */
public class FreqOfEachCharSameByOneRemoval {

    public static void main(String[] args) {
        System.out.println(isSameByOneRemoval("xyyz"));
        System.out.println(isSameByOneRemoval("xyyzz"));
        System.out.println(isSameByOneRemoval("xxxxyyzz"));
    }

    // Input  : str = “xyyz”
    // Output : Yes
    // We can remove character ’y’ from above
    // string to make the frequency of each
    // character same.
    //
    // Input : str = “xyyzz”
    // Output : Yes
    // We can remove character ‘x’ from above
    // string to make the frequency of each
    // character same.
    //
    // Input : str = “xxxxyyzz”
    // Output : No
    // It is not possible to make frequency of
    // each character same just by removing at
    // most one character from above string.

    // keep freq of each char in string
    // if by diff of one all become same then yes else no


    private static boolean isSameByOneRemoval(String input) {
        int[] count = new int[26];
        for (int i = 0; i < input.length(); i++) {
            count[getIdx(input.charAt(i))]++;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            int index = getIdx(ch);
            // Check character only if it occurs in str
            if (count[index] > 0) {
                count[index]--;
                if (isSame(count)) {
                    return true;
                }
                count[index]++;
            }
        }
        return false;
    }

    private static boolean isSame(int[] countArray) {
        int count = 0;
        int i;
        //  get first non-zero element
        for (i = 0; i < countArray.length; i++) {
            if (countArray[i] > 0) {
                count = countArray[i];
                break;
            }
        }

        for (int j = i + 1; j < countArray.length; j++) {
            if (countArray[j] > 0 && countArray[j] != count) {
                return false;
            }
        }
        return true;
    }

    // Utility method to get index of character ch
    // in lower alphabet characters
    static int getIdx(char ch)
    {
        return (ch - 'a');
    }
}
