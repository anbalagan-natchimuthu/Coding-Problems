package interview.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PROBLEM: 1
 * https://leetcode.com/problems/word-search-ii/
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 *
 * Input:
 * board = [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 * Idea: Naive way is to search for every word in the dictionary directly by DFS all cells for every word. The time
 * complexity will be O(m * n * l * wl) where n is board.length, m is board[0].length, l is words.length and wl is
 * the average of length of words in 'words'.
 *
 * Use a Trie to check multiple words at the same time when DFS from a certain cell.
 *
 * Complexity -
 * Time: O(m * n * wl) = max(O(l * wl), O(m * n * l * wl)) where
 * O(l * wl) - Build the trie
 * O(m * n * l * wl) - In the worst case where all words start with different characters, and there is a word starting
 * with a character in the cell board[m - 1][n - 1], we have O(m * n * l * wl). However, if there are words starting
 * with same characters and paths sharing cells, Trie can check multiple words when DFS from a certain cell, rather
 * than check only one word when DFS from a certain cell like the naive way.
 *
 * Space: O(l * wl) = max(O(wl), O(l * wl)) where
 * O(wl) - The recursive stack can grow at most to wl layers.
 * O(l * wl) - In the worst case when all words start with different characters, the trie has l * wl nodes. Also,
 * since each word is stored in a leaf node, all the leaf nodes require l * wl memory.
 *
 */
public class WordSearch {

  public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<>();
    TrieNode root = buildTrie(words);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(board, i, j, root, res);
      }
    }
    return res;
  }

  public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {

    if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || p.children.get(board[i][j]) == null
        || board[i][j] == '#') {
      return;
    }

    char c = board[i][j];
    p = p.children.get(c);
    if (p.word != null) {   // found one
      res.add(p.word);
      p.word = null;     // de-duplicate
    }

    board[i][j] = '#';

    dfs(board, i - 1, j, p, res);
    dfs(board, i, j - 1, p, res);
    dfs(board, i + 1, j, p, res);
    dfs(board, i, j + 1, p, res);

    board[i][j] = c;
  }

  public TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String w : words) {
      TrieNode p = root;
      for (char c : w.toCharArray()) {
        //int i = c - 'a';
        /*if (p.next[i] == null) p.next[i] = new TrieNode();
        p = p.next[i];*/

        if (p.children.get(c) == null) {
          p.children.put(c, new TrieNode());
        }
        p = p.children.get(c);
      }
      p.word = w;
    }
    return root;
  }

  class TrieNode {

    //TrieNode[] next = new TrieNode[26];
    Map<Character, TrieNode> children = new HashMap<>();
    String word;
  }

  /**
   *  PROBLEM: 2
   *  https://leetcode.com/problems/word-search/
   *  Given a 2D board and a word, find if the word exists in the grid.
   *
   * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
   * horizontally or vertically neighboring. The same letter cell may not be used more than once.
   *
   * Example:
   *
   * board =
   * [
   *   ['A','B','C','E'],
   *   ['S','F','C','S'],
   *   ['A','D','E','E']
   * ]
   *
   * Given word = "ABCCED", return true.
   * Given word = "SEE", return true.
   * Given word = "ABCB", return false.
   */
  public boolean exist(char[][] board, String word) {

    char initialChar = word.charAt(0);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == initialChar) {
          if (dfs(board, i, j, word, 0)) {
            return true;
          }
        }
      }
    }

    return false;
  }


  private boolean dfs(char[][] board, int i, int j, String word, int index) {
    if (word.length() == index) {
      return true;
    }

    if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index)
        || board[i][j] == '#') {
      return false;
    }

    char ch = board[i][j];
    board[i][j] = '#';

    if (dfs(board, i - 1, j, word, index + 1)) {
      return true;
    }
    if (dfs(board, i, j - 1, word, index + 1)) {
      return true;
    }
    if (dfs(board, i + 1, j, word, index + 1)) {
      return true;
    }
    if (dfs(board, i, j + 1, word, index + 1)) {
      return true;
    }
    board[i][j] = ch;
    return false;
  }

  public static void main(String[] args) {
    WordSearch wordSearch = new WordSearch();
    char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},
        {'i', 'f', 'l', 'v'},};

    List<String> matchedWords = wordSearch.findWords(board, new String[] {"oath","pea","eat","rain"});
    System.out.println(matchedWords);

    System.out.println(wordSearch.exist(new char[][]{{'a'}}, "a"));
  }
}
