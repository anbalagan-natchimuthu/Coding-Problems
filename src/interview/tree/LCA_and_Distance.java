package interview.tree;

public class LCA_and_Distance {

  /**
   * PROBLEM: 1
   * This method is for Binary Tree
   * https://www.youtube.com/watch?v=13m9ZCB8gjw
   */
  public static void LCA_Binary_Tree(BinaryNode root, BinaryNode node1, BinaryNode node2) {
    System.out.println(
        "LCA of Binary Tree:" + LCA_Binary_Tree_Helper(root, node1, node2).getValue() + " for nodes " + node1.getValue()
            + ":" + node2.getValue());
  }

  private static BinaryNode LCA_Binary_Tree_Helper(BinaryNode root, BinaryNode node1, BinaryNode node2) {

    if (root == null) {
      return null;
    }

    /**
     * If Node 'node1' or Node 'node2' is also the root, then the root itself is lowest common ancestor
     */
    if (root == node1 || root == node2) {
      return root;
    }

    BinaryNode left = LCA_Binary_Tree_Helper(root.getLeftNode(), node1, node2);
    BinaryNode right = LCA_Binary_Tree_Helper(root.getRightNode(), node1, node2);

    /**
     * If Node 'node1' and Node 'node2' lie in the left, their Lowest Common Ancestor is in the left.
     * If Node 'node1' and Node 'node2' lie in the right,their Lowest Common Ancestor is in the right.
     *
     * Otherwise, root is the Lowest common ancestor.
     */
    if (left != null && right != null) {
      return root;
    }

    /**
     * We couldn't find the nodes
     */
    if (left == null && right == null) {
      return null;
    }

    return (left != null) ? left : right;
  }

  /***
   * PROBLEM: 2 Recursive Approach
   * This Method is for Binary Search Tree
   * https://www.youtube.com/watch?v=TIoCCStdiFo
   *
   * Time Complexity: O(N), where N is the number of nodes in the BST. In the worst case we might be visiting
   * all the nodes of the BST.
   *
   * Space Complexity: O(N). This is because the maximum amount of space utilized by the recursion stack would be
   * N since the height of a skewed BST could be N.
   */
  public static void LCA_Binary_Search_Tree(BinaryNode root, BinaryNode node1, BinaryNode node2) {
    System.out.println("LCA:" + LCA_Binary_Search_Tree_Helper(root, node1, node2).getValue());
  }

  private static BinaryNode LCA_Binary_Search_Tree_Helper(BinaryNode currentRoot, BinaryNode node1, BinaryNode node2) {
    if (currentRoot == null || node1 == null || node2 == null) {
      return null;
    }

    if (Math.max(node1.getValue(), node2.getValue()) < currentRoot.getValue()) {
      return LCA_Binary_Search_Tree_Helper(currentRoot.getLeftNode(), node1, node2);
    } else if (Math.min(node1.getValue(), node2.getValue()) > currentRoot.getValue()) {
      return LCA_Binary_Search_Tree_Helper(currentRoot.getRightNode(), node1, node2);
    } else {
      return currentRoot;
    }
  }

  /***
   * PROBLEM: 2.1 Iterative Approach
   * This Method is for Binary Search Tree
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/
   * @param node1
   * @param node2
   */
  public static void LCA_Binary_Search_Tree_iterative(BinaryNode currentRoot, BinaryNode node1, BinaryNode node2) {

    while (currentRoot != null) {
      if (Math.max(node1.getValue(), node2.getValue()) < currentRoot.getValue()) {
        currentRoot = currentRoot.getLeftNode();
      } else if (Math.min(node1.getValue(), node2.getValue()) > currentRoot.getValue()) {
        currentRoot = currentRoot.getRightNode();
      } else {
        System.out.println("LCA Iterative:" + currentRoot.getValue());
        return;
      }
    }
  }

  /**
   * PROBLEM: 3
   * https://www.geeksforgeeks.org/find-distance-between-two-nodes-of-a-binary-tree/
   * Find distance between two nodes of a binary Tree
   */
  private static int distBetweenTwoNodes(BinaryNode root, BinaryNode b1, BinaryNode b2) {
    // 1. first we find LCA of two nodes
    // 2. Then we find distance from LCA to two nodes

    BinaryNode lca = LCA_Binary_Tree_Helper(root, b1, b2);

    int d1 = findLevel(lca, b1, 0);
    int d2 = findLevel(lca, b2, 0);

    return d1 + d2;
  }

  // Returns level of key k if it is present in tree, otherwise returns -1
  public static int findLevel(BinaryNode root, BinaryNode child, int level) {
    if (root == null) {
      return -1;
    }
    if (root.getValue() == child.getValue()) {
      return level;
    }
    int left = findLevel(root.getLeftNode(), child, level + 1);
    if (left == -1) {
      return findLevel(root.getRightNode(), child, level + 1);
    }
    return left;
  }

  public static void main(String[] args) {

    /*                       20
                             \
                              60
                             / \
                           80   90
                               /
                             70
                            / \
                          65   85
     */
    BinaryNode node = new BinaryNode(20);
    BinaryTree b = new BinaryTree();
    b.add(node);

    BinaryNode b6 = new BinaryNode(80);
    BinaryNode b7 = new BinaryNode(90);
    BinaryNode b8 = new BinaryNode(60, b6, b7);
    b.add(b8);

    BinaryNode b1 = new BinaryNode(85);
    BinaryNode b2 = new BinaryNode(65);
    BinaryNode b3 = new BinaryNode(70, b2, b1);
    b.add(b3);

    LCA_Binary_Search_Tree(b.root, b2, b1);
    LCA_Binary_Search_Tree_iterative(b.root, b2, b1);

    LCA_Binary_Tree(b.root, b2, b1);
    LCA_Binary_Tree(b.root, b2, new BinaryNode(1000));

    int dist = distBetweenTwoNodes(b.root, b2, b1);
    System.out.println("distance between Node : " + b2.getValue() + " and Node: " + b1.getValue() + " is = " + dist);

    dist = distBetweenTwoNodes(b.root, b6, b1);
    System.out.println("distance between Node : " + b6.getValue() + " and Node: " + b1.getValue() + " is = " + dist);
  }
}
