package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllString {

    public static void main(String[] args) {

        String input = "abc,def*$g";
        reverseStringWithSplCharsAsIs(input);

        int cout = maxOccuringChar("tesstt");
        System.out.println(cout);

        boolean isPalin = isPalindrome("PDDP");
        System.out.println("IsPalindrome " + isPalin);

        boolean isAnag = isAnagram("anagram", "gramana");
        System.out.println("IsAnagram " + isAnag);

        groupAnagrams("life in a file");

        int arr[] = new int[] { 1, 5, 3, 5, 18, 25, 26 };
        int minDiff = findMinDifferenceInArray(arr);
        System.out.println("MinDiff " + minDiff);
    }

    public static void reverseStringWithSplCharsAsIs(String input) {

        char[] charArray = input.toCharArray();
        int i = 0, j = input.length() - 1;
        char temp;

        while (i <= j) {
            if ((charArray[i] >= 'a' && charArray[i] <= 'z') && charArray[j] >= 'a' && charArray[j] <= 'z') {

                temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
                i++;
                j--;
            } else if (!(charArray[i] >= 'a' && charArray[i] <= 'z')) {
                i++;
            } else if (!(charArray[j] >= 'a' && charArray[j] <= 'z')) {
                j--;
            }
        }
        //for (int k = 0; k < charArray.length; k++) {
            System.out.println("reverseStringWithSplCharsAsIs:" + String.valueOf(charArray));
        //}
        System.out.println();
    }

    public static int maxOccuringChar(String input) {

        // Approach 1 : Using char[]
        char[] inputArr = input.toCharArray();
        //Approach 2 : Using HashMap
        Map<Character, Integer> hashMap = new HashMap<>();
        int[] resultArr = new int[52];
        int max = 0;
        int maxPosition = 0;

        for (char ch : inputArr) {
            resultArr[ch - 'A']++;
            hashMap.compute(ch, (key, value) -> {
                if (value == null) {
                    return 1;
                } else {
                    return value + 1;
                }
            });
        }

        for (int i = 0; i < resultArr.length; i++) {
            if (resultArr[i] > max) {
                max = resultArr[i];
                maxPosition = i;
            }
        }
        System.out.println("Character repeated Max times::" + (char) (maxPosition + (int) 'A') + ":: times " + max);

        max = 0;
        char resultChar = '\u0000';
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > max) {
                resultChar = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println("Character repeated Max times Using Hashmap::" + resultChar);
        return resultChar;
    }

    //
    public static boolean isPalindrome(String input) {
        int j = input.length() - 1;
        for (int i = 0; i < input.length() / 2; i++) {

            while (i <= j) {
                System.out.println("i  " + input.charAt(i));
                System.out.println("j  " + input.charAt(j));
                if (input.charAt(i) == input.charAt(j)) {
                    j--;
                    i++;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     *  function to check whether two strings are anagram of each other
     *  https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
   */
    public static boolean isAnagram(String input1, String input2) {

        // If both strings are of different length.
        // Removing this condition will make the program
        // fail for strings like "aaca" and "aca"
        if (input1.length() != input2.length()) {
            return false;
        }

        int NO_OF_CHARS = 256;
        int frequencies[] = new int[NO_OF_CHARS];


        // For each character in input string, increment count in the corresponding frequencies array
        for (int i = 0; i < input1.length(); i++) {
            frequencies[input1.charAt(i)]++;
        }

        for (int i = 0; i < input2.length(); i++) {
            if (frequencies[input2.charAt(i)] == 0) {
                return false;
            }
            frequencies[input2.charAt(i)]--;
        }

        return true;
    }

    /**
     * Given an array of strings, return all groups of strings that are anagrams.
     * https://www.programcreek.com/2014/04/leetcode-anagrams-java/
     * Refer: GroupAnagram.java for better solution
     * @param inputStr
     * @return
     */
    public static List<List<String>> groupAnagrams(String inputStr) {
        String[] strs = inputStr.split(" ");
        List<List<String>> result = new ArrayList<>();

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = new char[26];
            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }
            String ns = new String(arr);

            if (map.containsKey(ns)) {
                map.get(ns).add(str);
            } else {
                ArrayList<String> al = new ArrayList<>();
                al.add(str);
                map.put(ns, al);
            }
        }

        result.addAll(map.values());
        for(List<String> res :result) {
            System.out.print(res);
        }
        System.out.println();
        return result;
    }

    public static int findMinDifferenceInArray(int[] arr) {

        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i + 1] - arr[i]) <= minDiff) {
                minDiff = arr[i + 1] - arr[i];
            }
        }
        return minDiff;
    }
}
