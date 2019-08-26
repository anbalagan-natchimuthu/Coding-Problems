package interview.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=e9ZGxH1y_PE
 *
 * Print Diagonal of Binary tree
 */
public class Diagonal {

  public static void main(String[] args) {
    /*
             1
           /   \
          2     3
         / \   / \
        4  5  6   7
     */

    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.left = new Node(6);
    root.right.right = new Node(7);

    printDiagonal(root);
  }

  private static void printDiagonal(Node root) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    //queue.add(null);

    while (!queue.isEmpty()) {
      Node temp = queue.remove();
      /*if (temp == null) {
        queue.add(null);
        temp = queue.remove();
        if (temp == null) {
          break;
        }
      }*/

      while (temp != null) {
        System.out.print(temp.data + " ");
        if (temp.left != null) {
          queue.add(temp.left);
        }
        temp = temp.right;
      }
    }
  }
}
