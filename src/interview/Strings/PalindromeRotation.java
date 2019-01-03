package interview.Strings;

import java.util.Arrays;

/*
Given a string, check if it is a rotation of a palindrome. For example your function should return true for “aab” as
it is a rotation of “aba”.
 */
public class PalindromeRotation {

    public static void main(String[] args) {
        System.out.println("isRotatedPalindrom  " + isRotatedPalindrom("bddpp", "pbddc"));
    }

    public static boolean isRotatedPalindrom(String input, String rotatedStr) {

        // count length to be same
        // the rotatedStr should have at max single char with odd count
        // count array of both should be same

        if (input.length() != rotatedStr.length()) {
            return false;
        }

        int[] countInput = new int[26];
        int[] countRotateArr = new int[26];
        Arrays.fill(countInput, 0);
        Arrays.fill(countRotateArr, 0);
        for (int i = 0; i < rotatedStr.length(); i++) {
            countInput[input.charAt(i) - 'a']++;
            countRotateArr[rotatedStr.charAt(i) - 'a']++;
        }

        for (int j = 0; j < countInput.length; j++) {
            System.out.println("countInput[i] " + countInput[j]);
            System.out.println("countRotateArr[i] " + countRotateArr[j]);
            if (countInput[j] != countRotateArr[j]) {
                return false;
            } else {
                continue;
            }
        }
        return true;
    }
}
