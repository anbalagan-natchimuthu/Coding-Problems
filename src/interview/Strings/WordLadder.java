package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/**
 * https://leetcode.com/problems/word-ladder/
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 */
public class WordLadder {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {

    if (!wordList.contains(endWord)) {
      return 0;
    }

    // Dictionary to hold combination of words that can be formed,
    // from any given word. By changing one letter at a time.
    Map<String, List<String>> dictionary = new HashMap<>();

    for (String word : wordList) {
      for (int i = 0; i < word.length(); i++) {
        String newWord = word.substring(0, i) + "*" + word.substring(i + 1);

        // Key is the generic word
        // Value is a list of words which have the same intermediate generic word.
        dictionary.computeIfAbsent(newWord, value -> new ArrayList<>()).add(word);
      }
    }

    // Queue for BFS
    Queue<Pair<String, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(beginWord, 1));

    // Visited to make sure we don't repeat processing same word.
    List<String> visited = new ArrayList<>();
    visited.add(beginWord);

    while (!queue.isEmpty()) {
      Pair<String, Integer> pair = queue.poll();

      String word = pair.getKey();

      for (int i = 0; i < word.length(); i++) {

        // Intermediate words for current word
        String newWord = word.substring(0, i) + "*" + word.substring(i + 1);

        // Next states are all the words which share the same intermediate state.
        for (String children : dictionary.getOrDefault(newWord, new ArrayList<>())) {

          // If at any point if we find what we are looking for
          // i.e. the end word - we can return with the answer.
          if (children.equals(endWord)) {
            return pair.getValue() + 1;
          }

          // Otherwise, add it to the BFS Queue. Also mark it visited
          if (!visited.contains(children)) {
            queue.add(new Pair<>(children, pair.getValue() + 1));
            visited.add(children);
          }
        }
      }
    }

    return 0;
  }

  /**
   * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence
   * (s) from beginWord to endWord, such that:
   *
   * Only one letter can be changed at a time
   * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
   * Note:
   *
   * Return an empty list if there is no such transformation sequence.
   * All words have the same length.
   * All words contain only lowercase alphabetic characters.
   * You may assume no duplicates in the word list.
   * You may assume beginWord and endWord are non-empty and are not the same.
   * Example 1:
   *
   * Input:
   * beginWord = "hit",
   * endWord = "cog",
   * wordList = ["hot","dot","dog","lot","log","cog"]
   *
   * Output:
   * [
   * ["hit","hot","dot","dog","cog"],
   * ["hit","hot","lot","log","cog"]
   * ]
   */
  public List<List<String>> wordLadderII(String beginWord, String endWord, List<String> wordList) {

    List<List<String>> resultList = new ArrayList<>();
    if (!wordList.contains(endWord)) {
      return resultList;
    }

    // Dictionary to hold combination of words that can be formed,
    // from any given word. By changing one letter at a time.
    Map<String, List<String>> dictionary = new HashMap<>();

    for (String word : wordList) {
      for (int i = 0; i < word.length(); i++) {
        String newWord = word.substring(0, i) + "*" + word.substring(i + 1);

        // Key is the generic word
        // Value is a list of words which have the same intermediate generic word.
        dictionary.computeIfAbsent(newWord, value -> new ArrayList<>()).add(word);
      }
    }

    // Queue for BFS
    Queue<Pair<String, List<String>>> queue = new LinkedList<>();
    List<String> tempList = new ArrayList<>();
    tempList.add(beginWord);
    queue.add(new Pair<>(beginWord, tempList));

    // Visited to make sure we don't repeat processing same word.
    List<String> visited = new ArrayList<>();
    visited.add(beginWord);

    while (!queue.isEmpty()) {
      Pair<String, List<String>> pair = queue.poll();

      String word = pair.getKey();
      List<String> listValues = pair.getValue();

      for (int i = 0; i < word.length(); i++) {

        // Intermediate words for current word
        String newWord = word.substring(0, i) + "*" + word.substring(i + 1);

        // Next states are all the words which share the same intermediate state.
        for (String children : dictionary.getOrDefault(newWord, new ArrayList<>())) {

          List<String> res = new ArrayList<>(listValues);
          res.add(children);

          // If at any point if we find what we are looking for
          // i.e. the end word - we can return with the answer.
          if (children.equals(endWord)) {
            resultList.add(res);
            continue;
          }

          // Otherwise, add it to the BFS Queue. Also mark it visited
          if (!visited.contains(children)) {
            queue.add(new Pair<>(children, res));
            visited.add(children);
          }
        }
      }
    }

    return resultList;
  }

  public static void main(String[] args) {
    WordLadder wordLadder = new WordLadder();
    System.out.println(wordLadder.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));

    System.out.println(wordLadder.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));

    List<List<String>> res = wordLadder.wordLadderII("hit", "cog",
        Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
    System.out.println(res);
  }
}
