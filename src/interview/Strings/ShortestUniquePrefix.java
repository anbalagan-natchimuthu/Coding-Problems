package interview.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Find shortest unique prefix for every word in a given list | Set 1 (Using Trie)
 * Given an array of words, find all shortest unique prefixes to represent each word in the given array. Assume that
 * no word is prefix of another.
 *
 * Examples:
 *
 * Input: arr[] = {"zebra", "dog", "duck", "dove"}
 * Output: dog, dov, du, z
 * Explanation: dog => dog
 *              dove = dov
 *              duck = du
 *              z   => zebra
 *
 * Input: arr[] =  {"geeksgeeks", "geeksquiz", "geeksforgeeks"};
 * Output: geeksf, geeksg, geeksq}
 *
 * https://www.geeksforgeeks.org/find-all-shortest-unique-prefixes-to-represent-each-word-in-a-given-list/
 */
public class ShortestUniquePrefix {

    TrieNode root;

    // Trie DS - Start
    class TrieNode {

        Map<Character, TrieNode> children;

        boolean isEndOfWord;

        int freq;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
            freq = 1;
        }
    }

    // Initialize root Node
    public ShortestUniquePrefix() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            } else {
                node.freq++;
            }
            current = node;
        }
        current.isEndOfWord = true; // mark end of word
    }
    // Trie DS - End

    public String[] getUniquePrefixes(String[] inputStrArr) {
        // first construct trie out of given strings
        for (int i = 0; i < inputStrArr.length; i++) {
            insert(inputStrArr[i]);
        }

        String[] resultArr = new String[inputStrArr.length];

        for (int i = 0; i < inputStrArr.length; i++) {
            resultArr[i] = "";
            TrieNode current = root;
            for (int j = 0; j < inputStrArr[i].length(); j++) {
                char ch = inputStrArr[i].charAt(j);
                resultArr[i] += ch;
                current = current.children.get(ch);
                if (current.freq == 1) {
                    break;
                }
            }
        }

        return resultArr;
    }

    public static void main(String[] args) {
        ShortestUniquePrefix sup = new ShortestUniquePrefix();
        String[] input = { "zebra", "dog", "duck", "dove" };
        String[] result = sup.getUniquePrefixes(input);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i] + " --> " + result[i]);
        }
    }
}
