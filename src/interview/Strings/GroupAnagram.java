package interview.Strings;

/*Given an array of words, print all anagrams together.
For example, if the given array is {“cat”, “dog”, “tac”, “god”, “act”}, then output may be “cat tac act dog god”.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * PROBLEM: 1
 */
public class GroupAnagram {

  static final int NO_OF_CHARS = 26;

  // Class to represent a Trie Node
  static class TrieNode {

    boolean isEnd;  // indicates end of word

    // 26 slots each for 'a' to 'z'
    TrieNode[] child = new TrieNode[NO_OF_CHARS];

    // head of the index list
    LinkedList<String> head;

    // constructor
    public TrieNode() {
      isEnd = false;
      head = new LinkedList<>();
      for (int i = 0; i < NO_OF_CHARS; ++i) {
        child[i] = null;
      }
    }
  }

  // A utility function to insert a word to Trie
  static TrieNode insert(TrieNode root, String word, String originalString, int i) {
    // Base case
    if (root == null) {
      root = new TrieNode();
    }

    if (i < word.length()) {
      int index1 = word.charAt(i) - 'a';
      root.child[index1] = insert(root.child[index1], word, originalString, i + 1);
    } else  // If end of the word reached
    {
      // Insert index of this word to end of
      // index linked list
      if (root.isEnd == true) {
        root.head.add(originalString);
      } else // If Index list is empty
      {
        root.isEnd = true;
        root.head.add(originalString);
      }
    }
    return root;
  }

  // This function traverses the built trie. When a leaf
  // node is reached, all words connected at that leaf
  // node are anagrams. So it traverses the list at leaf
  // node and uses stored index to print original words
  static void printAnagramsUtil(TrieNode root, String wordArr[]) {
    if (root == null) {
      return;
    }

    // If a lead node is reached, print all anagrams
    // using the indexes stored in index linked list
    if (root.isEnd) {
      // traverse the list
      for (String pCrawl : root.head) {
        System.out.println(pCrawl);
      }
    }

    for (int i = 0; i < NO_OF_CHARS; ++i) {
      printAnagramsUtil(root.child[i], wordArr);
    }
  }

  // The main function that prints all anagrams together.
  // wordArr[] is input sequence of words.
  static void printAnagramsTogether(String wordArr[], int size) {
    // Create an empty Trie
    TrieNode root = null;

    // Iterate through all input words
    for (int i = 0; i < size; ++i) {
      // Create a buffer for this word and copy the
      // word to buffer
      char[] buffer = wordArr[i].toCharArray();

      // Sort the buffer
      Arrays.sort(buffer);

      // Insert the sorted buffer and its original
      // index to Trie
      root = insert(root, new String(buffer), wordArr[i], 0);
    }

    // Traverse the built Trie and print all anagrms
    // together
    printAnagramsUtil(root, wordArr);
  }

  // Driver program to test above functions
  public static void main(String args[]) {
    String wordArr[] = {"cat", "dog", "tac", "god", "act", "gdo"};
    int size = wordArr.length;
    printAnagramsTogether(wordArr, size);

    boolean isAnag = isAnagram("anagram", "gramana");
    System.out.println("IsAnagram " + isAnag);

    String[] input = {"aaa", "dog", "tac", "god", "act", "gdo"};
    System.out.println("Before Anagram:: " + Arrays.toString(input));
    groupAnagrams(input);
  }

  /**
   * PROBLEM: 2
   * function to check whether two strings are anagram of each other
   * https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
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
   * PROBLEM: 3
   * Given an array of strings, return all groups of strings that are anagrams.
   * https://www.programcreek.com/2014/04/leetcode-anagrams-java/
   * Refer: GroupAnagram.java for better solution
   */
  public static List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> result = new ArrayList<>();

    HashMap<String, ArrayList<String>> map = new HashMap<>();
    for (String str : strs) {
      int[] arr = new int[26];
      for (int i = 0; i < str.length(); i++) {
        arr[str.charAt(i) - 'a']++;
      }
      String ns = Arrays.toString(arr);

      if (map.containsKey(ns)) {
        map.get(ns).add(str);
      } else {
        ArrayList<String> al = new ArrayList<>();
        al.add(str);
        map.put(ns, al);
      }
    }

    /*List<List<String>> res = map.values().stream().filter(v -> v.size() > 1).collect(Collectors.toList());
    System.out.println(res.toString());*/

    result.addAll(map.values());
    for (List<String> res : result) {
      System.out.print(res);
    }
    System.out.println();
    return result;
  }
}