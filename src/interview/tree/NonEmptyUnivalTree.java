package interview.tree;

/**
 * Given a binary tree, write a program to count the number of Single Valued Subtrees. A Single Valued Subtree is
 * one in which all the nodes have same value. Expected time complexity is O(n).
 *
 * https://www.youtube.com/watch?v=7HgsS8bRvjo
 *
 * https://www.geeksforgeeks.org/find-count-of-singly-subtrees/
 */
public class NonEmptyUnivalTree {

  public static void main(String[] args) {

    /**
     *             1
     *           /   \
     *          2     3
     *         / \   / \
     *        8  9  3   3
     *          / \     \
     *         9   9    5
     *                 /\
     *                6  7
     */

    Node root = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    root.setLeft(node2);
    root.setRight(node3);

    Node node8 = new Node(8);
    Node node9 = new Node(9);
    node2.setLeft(node8);
    node2.setRight(node9);

    Node node9Left = new Node(9);
    Node node9Right = new Node(9);
    node9.setLeft(node9Left);
    node9.setRight(node9Right);

    Node node3Left = new Node(3);
    Node node3Right = new Node(3);
    node3.setLeft(node3Left);
    node3.setRight(node3Right);

    Node node5 = new Node(8);
    node3Right.setRight(node5);

    Node node6 = new Node(6);
    Node node7 = new Node(7);
    node5.setLeft(node6);
    node5.setRight(node7);

    NonEmptyUnivalTree univalTree = new NonEmptyUnivalTree();
    System.out.println(univalTree.findNonEmptyUnivalTree(root));
  }

  public int findNonEmptyUnivalTree(Node root) {
    Count count = new Count();
    countUnivalTree(root, count);
    return count.counter;
  }

  class Count {

    int counter = 0;
  }

  public boolean countUnivalTree(Node currNode, Count count) {

    if (currNode == null) {
      return true;
    }

    // Recursively count in left and right subtrees also
    boolean left = countUnivalTree(currNode.getLeft(), count);
    boolean right = countUnivalTree(currNode.getRight(), count);

    // If any of the subtrees is not Unival, then this
    // cannot be Unival.
    if (left == false || right == false) {
      return false;
    }

    // If left subtree is Unival and non-empty, but data
    // doesn't match
    if (currNode.left != null && currNode.data != currNode.left.data) {
      return false;
    }

    // Same for right subtree
    if (currNode.right != null && currNode.data != currNode.right.data) {
      return false;
    }

    // If none of the above conditions is true, then
    // tree rooted under root is Unival, increment
    // count and return true.
    count.counter++;
    return true;
  }
}
