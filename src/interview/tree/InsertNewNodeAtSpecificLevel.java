package interview.tree;

import java.util.LinkedList;
import java.util.Queue;

public class InsertNewNodeAtSpecificLevel {

  /**
   * Insert New node at specific Level and push all nodes at that level to down
   */
  public static Node insertNode(Node root, int depth, int value) {

    Queue<Node> nodeQueue = new LinkedList<>();
    nodeQueue.add(root);
    nodeQueue.add(null);
    depth--;

    while (!nodeQueue.isEmpty()) {
      Node temp = nodeQueue.remove();

      if (temp == null) {
        if(nodeQueue.isEmpty()) {
          break;
        }
        nodeQueue.add(null);
        depth--;
      } else {
        if (temp.left != null) {
          Node tempNode = temp.left;
          nodeQueue.add(tempNode);
          if (depth == 1) {
            Node newNode = new Node(value);
            temp.left = newNode;
            newNode.left = tempNode;
          }
        }

        if (temp.right != null) {
          Node tempNode = temp.right;
          nodeQueue.add(tempNode);
          if (depth == 1) {
            Node newNode = new Node(value);
            temp.right = newNode;
            newNode.right = tempNode;
          }
        }
      }
    }
    return root;
  }

  public static void main(String[] args) {
    Node root = new Node (1);

    Node level2LeftChild = new Node(2);
    Node level2RightChild = new Node(3);
    root.left = level2LeftChild;
    root.right = level2RightChild;


    Node level3LeftLeftChild = new Node(4);
    Node level3RightLeftChild = new Node(5);
    Node level3RightRightChild = new Node(6);
    level2LeftChild.left = level3LeftLeftChild;
    level2RightChild.left = level3RightLeftChild;
    level2RightChild.right = level3RightRightChild;


    Node result = insertNode(root, 3, 8);

    Queue<Node> nodeQueue = new LinkedList<>();
    nodeQueue.add(result);
    nodeQueue.add(null);
    while (!nodeQueue.isEmpty()) {
      Node temp = nodeQueue.remove();

      if (temp == null) {
        if (nodeQueue.isEmpty()) {
          break;
        }
        System.out.println();
        nodeQueue.add(null);
      } else {
        System.out.print(temp.data + " ");
        if (temp.left != null) {
          nodeQueue.add(temp.left);
        }

        if (temp.right != null) {
          nodeQueue.add(temp.right);
        }
      }
    }
  }
}
