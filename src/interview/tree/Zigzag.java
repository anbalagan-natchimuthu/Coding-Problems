package interview.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Zigzag {

  public static void main(String[] args) {

    /**
     *             1
     *           /   \
     *          2     3
     *         / \   / \
     *        4  5  6   7
     *       / \    \    \
     *      8  9     10  11
     *     /  / \    /
     *    12 13 14  15
     */

    Node root = new Node(1);

    Node node2 = new Node(2);
    Node node3 = new Node(3);
    root.setLeft(node2);
    root.setRight(node3);

    Node node4 = new Node(4);
    Node node5 = new Node(5);
    node2.setLeft(node4);
    node2.setRight(node5);

    Node node6 = new Node(6);
    Node node7 = new Node(7);
    node3.setLeft(node6);
    node3.setRight(node7);

    Node node8 = new Node(8);
    Node node9 = new Node(9);
    node4.setLeft(node8);
    node4.setRight(node9);

    Node node10 = new Node(10);
    node6.setRight(node10);

    Node node11 = new Node(11);
    node7.setRight(node11);

    Node node12 = new Node(12);
    node8.setLeft(node12);

    Node node13 = new Node(13);
    Node node14 = new Node(14);
    node9.setLeft(node13);
    node9.setRight(node14);

    Node node15 = new Node(15);
    node10.setLeft(node15);

    printZigZag(root);

    System.out.println("\n\n");

    printZigZagUsingSingleQueue(root);

    System.out.println("\n\n");
  }

  // https://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
  // https://www.youtube.com/watch?v=vjt5Y6-1KsQ
  private static void printZigZag(Node root) {

    if (root == null) {
      return;
    }

    // For levels to be printed from left to right
    Stack<Node> leftToRightNodes = new Stack<>();

    // For levels to be printed from right to left
    Stack<Node> rightToLeftNodes = new Stack<>();

    // Push first level to first stack 'leftToRightNodes'
    leftToRightNodes.push(root);

    // Keep printing while any of the stacks has some nodes
    while (!leftToRightNodes.isEmpty() || !rightToLeftNodes.isEmpty()) {

      // Print nodes of current level from "leftToRightNodes" and push nodes of
      // next level to "rightToLeftNodes"
      while (!leftToRightNodes.isEmpty()) {
        Node temp = leftToRightNodes.pop();
        System.out.print(temp.getData() + " ");

        // Note that is left is pushed before right
        if (temp.getLeft() != null) {
          rightToLeftNodes.push(temp.getLeft());
        }

        if (temp.getRight() != null) {
          rightToLeftNodes.push(temp.getRight());
        }
      }

      // Print nodes of current level from "rightToLeftNodes" and push nodes of
      // next level to "leftToRightNodes"
      while (!rightToLeftNodes.isEmpty()) {
        Node temp = rightToLeftNodes.pop();
        System.out.print(temp.getData() + " ");

        // Note that is right is pushed before left
        if (temp.getRight() != null) {
          leftToRightNodes.push(temp.getRight());
        }

        if (temp.getLeft() != null) {
          leftToRightNodes.push(temp.getLeft());
        }
      }
    }
  }

  private static void printZigZagUsingSingleQueue(Node root) {
    Deque<Node> deQueue = new ArrayDeque<>();
    deQueue.addFirst(root);
    int count = 1;
    boolean flip = true;

    while (!deQueue.isEmpty()) {
      int currentCount = 0;
      while (count > 0) {
        if (flip) {
          Node tempNode = deQueue.removeFirst();
          System.out.print(tempNode.getData() + " ");
          count--;

          if (tempNode.getLeft() != null) {
            deQueue.addLast(tempNode.getLeft());
            currentCount++;
          }
          if (tempNode.getRight() != null) {
            deQueue.addLast(tempNode.getRight());
            currentCount++;
          }
        } else {
          Node tempNode = deQueue.removeLast();
          System.out.print(tempNode.getData() + " ");
          count--;

          if (tempNode.getRight() != null) {
            deQueue.addFirst(tempNode.getRight());
            currentCount++;
          }
          if (tempNode.getLeft() != null) {
            deQueue.addFirst(tempNode.getLeft());
            currentCount++;
          }
        }
      }
      count = currentCount;
      flip = !flip;
    }
  }
}
