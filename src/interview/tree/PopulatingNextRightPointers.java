package interview.tree;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The
 * binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
 * should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 */
public class PopulatingNextRightPointers {

  // Definition for a Node.
  static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node(int _val) {
      val = _val;
    }
  }

  public static Node connect(Node root) {
    Node level_start = root;
    while (level_start != null) {
      Node cur = level_start;
      while (cur != null) {
        if (cur.left != null) {
          cur.left.next = cur.right;
        }
        if (cur.right != null && cur.next != null) {
          cur.right.next = cur.next.left;
        }

        cur = cur.next;
      }
      level_start = level_start.left;
    }
    return root;
  }

  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);

    node1.left = node2;
    node1.right = node3;

    node2.left = node4;
    node2.right = node5;

    node3.left = node6;
    node3.right = node7;

    Node res = connect(node1);
    System.out.println(res);
  }

}
