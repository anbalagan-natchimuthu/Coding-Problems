package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  PROBLEM : 1
 * Given a digit string, return all possible letter combinations that the number could represent.
 * (Check out your cellphone to see the mappings) Input:Digit string "23", Output: ["ad", "ae", "af", "bd", "be",
 * "bf", "cd", "ce", "cf"].

 * https://www.geeksforgeeks.org/find-possible-words-phone-digits/
 */
public class LetterCombPhoneNumber {
    public static void main(String[] args) {
        LetterCombPhoneNumber lc = new LetterCombPhoneNumber();
        System.out.println(lc.letterCombinations("23"));

        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 2, 3));
        input.add(Arrays.asList(4, 5, 6));
        input.add(Arrays.asList(7, 8, 9));
        int[] tempArray = new int[input.size()];
        List<List<Integer>> resultList = new ArrayList<>();

        combinations(input, tempArray, 0, resultList);

        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i));
        }
    }

    public List<String> letterCombinations(String digits) {
        HashMap<Character, char[]> dict = new HashMap<>();
        dict.put('2', new char[] { 'a', 'b', 'c' });
        dict.put('3', new char[] { 'd', 'e', 'f' });
        dict.put('4', new char[] { 'g', 'h', 'i' });
        dict.put('5', new char[] { 'j', 'k', 'l' });
        dict.put('6', new char[] { 'm', 'n', 'o' });
        dict.put('7', new char[] { 'p', 'q', 'r', 's' });
        dict.put('8', new char[] { 't', 'u', 'v' });
        dict.put('9', new char[] { 'w', 'x', 'y', 'z' });

        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        char[] arr = new char[digits.length()];
        helper(digits, 0, dict, result, arr);

        return result;
    }

    private void helper(String digits, int index, HashMap<Character, char[]> dict, List<String> result, char[] arr) {
        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }

        char number = digits.charAt(index);
        char[] candidates = dict.get(number);
        for (int i = 0; i < candidates.length; i++) {
            arr[index] = candidates[i];
            helper(digits, index + 1, dict, result, arr);
        }
    }

    /**
     *   PROBLEM : 2
     * Generate all possible combinations for Lis<List<Integer>>
     */
    private static void combinations(List<List<Integer>> inputList, int[] tempArray, int level, List<List<Integer>> results) {

        if (tempArray.length == level) {
            List<Integer> tempList = Arrays.stream(tempArray).boxed().collect(Collectors.toList());
            results.add(tempList);
            return;
        }

        for (int i = 0; i < inputList.get(level).size(); i++) {
            tempArray[level] = inputList.get(level).get(i);
            combinations(inputList, tempArray, level + 1, results);
        }
    }
}
