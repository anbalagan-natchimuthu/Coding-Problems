package interview.tree;

/**
 * https://www.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/
 *
 * https://www.youtube.com/watch?v=ey7DYc9OANo
 *
 * The diameter of a tree is the number of nodes on the longest path between two leaves in the tree.
 * The diagram below shows two trees each with diameter nine, the leaves that form the ends of a longest
 * path are colored (note that there may be more than one path in tree of same diameter).
 */
public class Diameter {

  static class Node {

    int data;
    Node left, right;
  }

  static class A {

    static int ans = Integer.MIN_VALUE;
  }

  /* Function to find height of a tree */
  static int height(Node root) {
    if (root == null) {
      return 0;
    }

    int left_height = height(root.left);

    int right_height = height(root.right);

    // update the answer, because diameter of a
    // tree is nothing but maximum value of
    // (left_height + right_height + 1) for each node
    A.ans = Math.max(A.ans, 1 + left_height + right_height);

    return 1 + Math.max(left_height, right_height);
  }

  /* Computes the diameter of binary
  tree with given root. */
  static int diameter(Node root) {
    if (root == null) {
      return 0;
    }

    // This will store the final answer
    height(root);
    return A.ans;
  }

  static Node newNode(int data) {
    Node node = new Node();
    node.data = data;
    node.left = null;
    node.right = null;

    return (node);
  }

  // Driver code
  public static void main(String[] args) {

    /*
             1
           /   \
          2     3
         / \
        4  5
       /  /
      8  6
      \  \
       9  7
     */

    Node root = newNode(1);
    root.left = newNode(2);
    root.right = newNode(3);
    root.left.left = newNode(4);
    root.left.right = newNode(5);
    root.left.left.left = newNode(8);
    root.left.left.left.right = newNode(9);
    root.left.right.left = newNode(6);
    root.left.right.left.right = newNode(7);

    System.out.println("Diameter is " + diameter(root));
  }
}
