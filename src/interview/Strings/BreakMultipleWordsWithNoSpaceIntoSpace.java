package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.youtube.com/watch?v=WepWFGxiwRs
 * Given a string and a dictionary, split this string into multiple words such that
 *  * each word belongs in dictionary.
 *  *
 *  * e.g peanutbutter -> pea nut butter
 *  * e.g Iliketoplay -> I like to play
 *  *
 *  * Solution
 *  * DP solution to this problem
 *  * if( input[i...j] belongs in dictionary) T[i][j] = i
 *  * else{
 *  *     T[i][j] = k if T[i][k-1] != -1 && T[k][j] != -1
 *  *
 *  * Test cases
 *  * 1) Empty string
 *  * 2) String where entire string is in dictionary
 *  * 3) String which cannot be split into words which are in dictionary
 *  * 3) String which can be split into words which are in dictionary
 */
public class BreakMultipleWordsWithNoSpaceIntoSpace {

    public String breakWordDP(String word, Set<String> dict) {
        int T[][] = new int[word.length()][word.length()];

        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                T[i][j] = -1; //-1 indicates string between i to j cannot be split
            }
        }

        //fill up the matrix in bottom up manner
        for (int l = 1; l <= word.length(); l++) {
            for (int i = 0; i < word.length() - l + 1; i++) {
                int j = i + l - 1;
                String str = word.substring(i, j + 1);
                //if string between i to j is in dictionary T[i][j] is true
                if (dict.contains(str)) {
                    T[i][j] = i;
                    continue;
                }
                //find a k between i+1 to j such that T[i][k-1] && T[k][j] are both true
                for (int k = i + 1; k <= j; k++) {
                    if (T[i][k - 1] != -1 && T[k][j] != -1) {
                        T[i][j] = k;
                        break;
                    }
                }
            }
        }
        if (T[0][word.length() - 1] == -1) {
            return null;
        }

        //create space separate word from string is possible
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            int k = T[i][j];
            if (i == k) {
                buffer.append(word.substring(i, j + 1));
                break;
            }
            buffer.append(word.substring(i, k) + " ");
            i = k;
        }

        return buffer.toString();
    }

    public static void main(String args[]) {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("I");
        dictionary.add("like");
        dictionary.add("had");
        dictionary.add("play");
        dictionary.add("to");
        dictionary.add("am");
        dictionary.add("a");
        dictionary.add("ace");
        BreakMultipleWordsWithNoSpaceIntoSpace bmw = new BreakMultipleWordsWithNoSpaceIntoSpace();

        String str = "Ihadliketoplay";
        String result1 = bmw.breakWordDP(str, dictionary);
        System.out.println(result1);

        str = "Iamace";
        result1 = bmw.breakWordDP(str, dictionary);
        System.out.println(result1);

        Set<String> wordDict = new HashSet<>();
        wordDict.addAll(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        List<String> outputList = bmw.wordBreakTopDown("catsanddog", wordDict);
        System.out.println(outputList);

        outputList = bmw.wordBreakTopDown("catsandog", wordDict);
        System.out.println(outputList);

        bmw.wordBreakTopDownOneSolution("catsanddog", wordDict);

    }

    /**
     * PROBLEM 2:
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to
     * construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
     *
     * Prints all the words possible instead of just one combination.
     * Reference https://leetcode.com/problems/word-break-ii/
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Example 1:
     *
     * Input:
     * s = "catsanddog"
     * wordDict = ["cat", "cats", "and", "sand", "dog"]
     * Output:
     * [
     *   "cats and dog",
     *   "cat sand dog"
     * ]
     */
    public List<String> wordBreakTopDown(String s, Set<String> wordDict) {
        Map<Integer, List<String>> dp = new HashMap<>();
        int max = 0;
        for (String s1 : wordDict) {
            max = Math.max(max, s1.length());
        }
        return wordBreakUtil(s, wordDict, dp, 0, max);
    }

    private List<String> wordBreakUtil(String s, Set<String> dict, Map<Integer, List<String>> dp, int start, int max) {
        if (start == s.length()) {
            return Collections.singletonList("");
        }

        if (dp.containsKey(start)) {
            return dp.get(start);
        }

        List<String> words = new ArrayList<>();
        for (int i = start; i < start + max && i < s.length(); i++) {
            String newWord = s.substring(start, i + 1);
            if (!dict.contains(newWord)) {
                continue;
            }
            List<String> result = wordBreakUtil(s, dict, dp, i + 1, max);
            for (String word : result) {
                String extraSpace = word.length() == 0 ? "" : " ";
                words.add(newWord + extraSpace + word);
            }
        }
        dp.put(start, words);
        return words;
    }

    /**
     * PROBLEM 3:
     * Check if any one solution exists.
     * https://leetcode.com/problems/word-break/
     */
    public boolean wordBreakTopDownOneSolution(String s, Set<String> wordDict) {
        Map<Integer, Boolean> dp = new HashMap<>();
        int max = 0;
        for (String s1 : wordDict) {
            max = Math.max(max, s1.length());
        }
        return wordBreakTopDownOneSolutionUtil(s, wordDict, 0, max, dp);

    }

    private boolean wordBreakTopDownOneSolutionUtil(String s, Set<String> dict, int start, int max, Map<Integer, Boolean> dp) {
        if (start == s.length()) {
            return true;
        }

        if (dp.containsKey(start)) {
            return dp.get(start);
        }

        for (int i = start; i < start + max && i < s.length(); i++) {
            String newWord = s.substring(start, i + 1);
            if (!dict.contains(newWord)) {
                continue;
            }
            if (wordBreakTopDownOneSolutionUtil(s, dict, i + 1, max, dp)) {
                dp.put(start, true);
                return true;
            }
        }
        dp.put(start, false);
        return false;
    }

}
