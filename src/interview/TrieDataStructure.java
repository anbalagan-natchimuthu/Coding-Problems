package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 212438472 on 3/29/18.
 * https://www.youtube.com/watch?v=AXjmTQ8LEoI.
 */
public class TrieDataStructure {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("interview");
        trie.insertWord("ananya");
        trie.insert("rihaan");
        System.out.println("search iterative:" + trie.search("ananya"));
        System.out.println("search Recursive:" + trie.searchRecursive("interview"));
        System.out.println("search iterative:" + trie.search("rihaan"));
        System.out.println("delete:" + trie.delete("rihaan"));
        System.out.println("search iterative:" + trie.search("rihaan"));
        System.out.println("search iterative:" + trie.search("interview"));
        System.out.println("delete:" + trie.delete("interview"));
        System.out.println("search iterative:" + trie.search("interview"));
        System.out.println("delete:" + trie.delete("ananya"));
        System.out.println("search Recursive:" + trie.searchRecursive("ananya"));
    }
}

class Trie {
    private TrieNode root;

    class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean endOfWord;

        public TrieNode() {
            this.children = new HashMap<>();
            this.endOfWord = false;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Iterative implementation of insert into trie
     * @param word
     */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (char ch : word.toCharArray()) {
            TrieNode node = currentNode.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                currentNode.children.put(ch, node);
            }
            currentNode = node;
        }
        currentNode.endOfWord = true;
    }

    /**
     * Recursive implementation of insert into trie
     * @param word
     */
    public void insertWord(String word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode currentNode, String word, int index) {
        if (word.length() == index) {
            currentNode.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode newNode = currentNode.children.get(ch);
        if (newNode == null) {
            newNode = new TrieNode();
            currentNode.children.put(ch, newNode);
        }
        insertRecursive(newNode, word, index + 1);
    }

    /**
     * Iterative implementation of Search of trie
     * @param word
     */
    public boolean search(String word) {
        TrieNode currentNode = root;
        for (char ch : word.toCharArray()) {
            TrieNode node = currentNode.children.get(ch);
            if (node == null) {
                return false;
            }
            currentNode = node;
        }
        return currentNode.endOfWord;
    }

    /**
     * Recursive implementation of search of trie
     * @param word
     */
    public boolean searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode currentNode, String word, int index) {
        if (word.length() == index) {
            return currentNode.endOfWord;
        }
        TrieNode newNode = currentNode.children.get(word.charAt(index));
        if (newNode == null) {
            return false;
        }
        currentNode = newNode;
        return searchRecursive(currentNode, word, index + 1);
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }
    public boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            // Return false if it is not complete word
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            // Only delete this hierarchy (word) if there is no children for this node.
            // e.g. If user is trying to delete the word 'the' but other word 'their' exist, then
            // just make the word 'the' as not endOfWord and keep the hierarchy.
            return current.children.size() == 0;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.size() == 0;
        }
        return false;
    }
}
