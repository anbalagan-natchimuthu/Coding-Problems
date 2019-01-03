package interview.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringToDo {

    public static void main(String[] args) {
        boolean isPalin = isPalindrome("PDbc&*DP");
        System.out.println("IsPalindrome " + isPalin);

        String input = "abc,def*g";
        reverseStringWithSplChars(input);

        String anagramInput = "life in a file ni eifl";
        groupAnagrams(anagramInput);
    }

    private static boolean isPalindrome(String input) {
        char[] inputArr = input.toCharArray();

        int i = 0;
        int j = input.length() - 1;

        while (i <= j) {
            if (input.charAt(i) < 'a' && input.charAt(i) > 'z') {
                i++;
            }
            if (input.charAt(j) < 'a' && input.charAt(j) > 'z') {
                j--;
            }
            if (input.charAt(i) == input.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    private static void reverseStringWithSplChars(String input) {
        char[] inputArr = input.toCharArray();
        int j = inputArr.length - 1;
        char temp;
        int i = 0;
        while (i <= j) {
            if (inputArr[i] >= 'a' && inputArr[i] <= 'z' && inputArr[j] >= 'a' && inputArr[j] <= 'z') {
                temp = inputArr[i];
                inputArr[i] = inputArr[j];
                inputArr[j] = temp;
                i++;
                j--;
            } else if (!(inputArr[i] >= 'a' && inputArr[i] <= 'z')) {
                i++;
            } else if (!(inputArr[j] >= 'a' && inputArr[j] <= 'z')) {
                j--;
            }
        }

        for (int k = 0; k < inputArr.length; k++) {
            System.out.println(inputArr[k]);
        }
    }

    private static void groupAnagrams(String input) {
        String[] words = input.split(" ");
        Map<String, List<String>> anagramsMap = new HashMap<>();
        String[] dupArray = words;
        String temp;
        int i = 0;
        StringBuilder sbr;
        List<String> wordArrayList = new ArrayList<>();
        for (String word : words) {
            char[] arr = new char[26];
            for (int j = 0; j < word.length(); j++) {
                arr[word.charAt(j) - 'a']++;
            }
            sbr = new StringBuilder();
            for (int m = 0; m < arr.length; m++) {
                if (arr[m] != 0) {
                    sbr.append((char) (m + 'a'));
                    System.out.println((char) m + 'a');
                }
            }

            if (anagramsMap.containsKey(sbr.toString())) {
                wordArrayList = anagramsMap.get(sbr.toString());
                wordArrayList.add(dupArray[i]);
            } else {
                wordArrayList = new ArrayList<>();
                wordArrayList.add(dupArray[i]);
            }
            anagramsMap.put(sbr.toString(), wordArrayList);
            i++;
        }

        for (String key : anagramsMap.keySet()) {
            System.out.println(key);
            List<String> list = anagramsMap.get(key);
            for (int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j));
            }

            System.out.println("********");
        }
    }
}