package interview.tree;

import java.util.Stack;

public class SymmetricTree {

  public static void main(String[] args) {
        /* Create following Binary Tree For Left View
             1
           /   \
          2     2
         / \   / \
        5   7 7   5
       */
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(2);
    root.left.left = new Node(5);
    root.left.right = new Node(7);

    root.right.left = new Node(7);
    root.right.right = new Node(5);

    System.out.println(isSymmetricTree(root));

    System.out.println("Symmetric Tree Iterative:" + symmetricTreeIterative(root));

    /* Create following Binary Tree
             1
           /  \
          2    2
           \    \
            4    4
        */

    Node root1 = new Node(1);
    root1.left = new Node(2);
    root1.right = new Node(2);
    root1.left.right = new Node(4);
    root1.right.right = new Node(4);

    System.out.println(isSymmetricTree(root1));
    System.out.println("Symmetric Tree Iterative:" + symmetricTreeIterative(root1));
  }

  public static boolean isSymmetricTree(Node root) {
    if (root == null) {
      return true;
    }
    return symmetricTreeHelper(root.getLeft(), root.getRight());
  }

  private static boolean symmetricTreeHelper(Node left, Node right) {

    if (left == null && right == null) {
      return true;
    } else if (left == null || right == null) {
      return false;
    } else {
      return left.getData() == right.getData() && symmetricTreeHelper(left.getLeft(), right.getRight())
          && symmetricTreeHelper(left.getRight(), right.getLeft());
    }
  }

  private static boolean symmetricTreeIterative(Node root) {

    if (root == null) {
      return true;
    }

    Stack<Node> treeStack = new Stack<>();
    treeStack.push(root.getLeft());
    treeStack.push(root.getRight());

    Node left, right;

    while (!treeStack.isEmpty()) {
      left = treeStack.pop();
      right = treeStack.pop();

      if (left == null && right == null) {
        continue;
      } else if (left == null || right == null) {
        return false;
      } else if (left.getData() != right.getData()) {
        return false;
      }

      treeStack.push(left.getLeft());
      treeStack.push(right.getRight());
      treeStack.push(left.getRight());
      treeStack.push(right.getLeft());
    }

    return true;
  }
}
