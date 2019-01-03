package interview.Strings;

import java.util.HashSet;

/**
 * Created by 212438472 on 1/10/18.
 */
public class UniqueCharacters {
    final static int MAX_CHAR = 256;

    private static boolean isUniqueCharactersWithDataStructures(String inputString) {
        HashSet<Character> uniqueMap = new HashSet<>();
        for (int i = 0; i<inputString.length(); i++) {
            if (!uniqueMap.add(inputString.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isUniqueCharacters(String inputString) {
        for (int i=0; i<inputString.length(); i++) {
            if (i != inputString.lastIndexOf(inputString.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isUniqueCharactersUsingStream(String inputString) {
        if (inputString.chars().distinct().count() == inputString.length()) {
            return true;
        }
        return false;
    }

    private static boolean uniqueCharactersWithExtraDataStructures(String str)
    {
        // If length is greater than 256,
        // some characters must have been repeated
        if (str.length() > MAX_CHAR)
            return false;

        boolean[] chars = new boolean[MAX_CHAR];

        for (int i=0; i<str.length(); i++)
        {
            int index = (int) str.charAt(i);

            /* If the value is already true, string
               has duplicate characters, return false */
            if (chars[index] == true)
                return false;

            chars[index] = true;
        }

        /* No duplicates encountered, return true */
        return true;
    }
    public static void main (String args[]) {
        System.out.println(isUniqueCharactersWithDataStructures("interview"));
        System.out.println(uniqueCharactersWithExtraDataStructures("interview"));
        System.out.println(isUniqueCharacters("interview"));
        System.out.println(isUniqueCharactersUsingStream("interview"));
    }
}
