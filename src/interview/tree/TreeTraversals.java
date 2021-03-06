package interview.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversals {

  public static void main(String[] args) {

    /**
     *             1
     *           /   \
     *          2     3
     *         / \   / \
     *        4  5  6   7
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

    System.out.println("********** Pre order Traversal *********");

    preOrder(root);

    System.out.println("\n");
    System.out.println("********** In order Traversal *********");

    inorder(root);

    System.out.println("\n");
    System.out.println("********** Post order Traversal *********");

    postorder(root);

    System.out.println("\n");
    System.out.println("********** Pre order Traversal Using Stack *********");

    List<Integer> result = preorderTraversal(root);
    for (Integer each : result) {
      System.out.print(each + " ");
    }

    System.out.println("\n");
    System.out.println("********** In order Traversal Using Stack *********");

    result = inorderTraversal(root);
    for (Integer each : result) {
      System.out.print(each + " ");
    }

    System.out.println("\n");
    System.out.println("********** Post order Traversal Using Single Stack - Approach 1 *********");

    result = postorderTraversalSingleStack(root);
    for (Integer each : result) {
      System.out.print(each + " ");
    }

    System.out.println("\n");
    System.out.println("********** Post order Traversal Using single Stack - Approach 2 *********");

    result = postorderTraversal(root);
    for (Integer each : result) {
      System.out.print(each + " ");
    }

    System.out.println("\n");
    System.out.println("********** Level order Traversal (BFS) *********");
    levelOrderTraversal(root);
  }

  /**
   * Pre order traversal recursive way
   */
  public static void preOrder(Node node) {

    if (node == null) {
      return;
    }

    /* first print data of node */
    System.out.print(node.getData() + " ");

    /* then recur on left subtree */
    preOrder(node.getLeft());

    /* now recur on right subtree */
    preOrder(node.getRight());
  }

  /**
   * In order traversal recursive way
   */
  public static void inorder(Node node) {

    if (node == null) {
      return;
    }


    /* First recur on left subtree */
    inorder(node.getLeft());

    /* then print data of node */
    System.out.print(node.getData() + " ");

    /* now recur on right subtree */
    inorder(node.getRight());
  }

  /**
   * Post order traversal recursive way
   */
  public static void postorder(Node node) {

    if (node == null) {
      return;
    }

    /* First recur on left subtree */
    postorder(node.getLeft());

    /* then recur on right subtree */
    postorder(node.getRight());

    /* now print data of node */
    System.out.print(node.getData() + " ");
  }

  /**
   * Pre order Traversal using Stack / Iterative way
   * https://youtu.be/nzmtCFNae9k
   */
  public static ArrayList<Integer> preorderTraversal(Node root) {
    ArrayList<Integer> returnList = new ArrayList<Integer>();

    if (root == null) {
      return returnList;
    }

    Stack<Node> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      Node n = stack.pop();
      returnList.add(n.getData());

      if (n.getRight() != null) {
        stack.push(n.getRight());
      }
      if (n.getLeft() != null) {
        stack.push(n.getLeft());
      }
    }
    return returnList;
  }

  /**
   * In order Traversal using Stack / Iterative way
   * https://youtu.be/elQcrJrfObg
   */
  public static List<Integer> inorderTraversal(Node root) {
    List<Integer> res = new ArrayList<>();
    Stack<Node> stack = new Stack<>();
    //Node root = head;

    while (true) {
      if (root != null) {
        stack.push(root);
        root = root.getLeft();
      } else {
        if (stack.isEmpty()) {
          break;
        }
        root = stack.pop();
        res.add(root.getData());
        root = root.getRight();
      }
    }
    return res;
  }

  /**
   * post order Traversal using two Stacks / Iterative way
   * https://www.youtube.com/watch?v=qT65HltK2uE&t=311s
   */
  public static List<Integer> postorderTraversalSingleStack(Node root) {
    List<Integer> res = new ArrayList<>();
    Stack<Node> stack1 = new Stack<>();
    //Stack<Integer> stack2 = new Stack<>();

    stack1.push(root);

    while (!stack1.isEmpty()) {
      Node node = stack1.pop();

      if (node.getLeft() != null) {
        stack1.push(node.getLeft());
      }
      if (node.getRight() != null) {
        stack1.push(node.getRight());
      }

      //stack2.push(node.getData());
      res.add(0, node.getData());
    }

       /* while(!stack2.isEmpty()) {
            res.add(stack2.pop());
        }*/
    return res;
  }

  /**
   * post order Traversal using single Stack / Iterative way
   * https://www.youtube.com/watch?v=xLQKdq0Ffjg
   */
  public static List<Integer> postorderTraversal(Node root) {
    List<Integer> res = new ArrayList<>();
    Stack<Node> stack = new Stack<>();

    Node current = root;

    while (current != null || !stack.isEmpty()) {
      if (current != null) {
        stack.push(current);
        current = current.getLeft();
      } else {
        Node temp = stack.peek().getRight();
        if (temp == null) {
          temp = stack.pop();
          res.add(temp.getData());
          while (!stack.isEmpty() && temp == stack.peek().getRight()) {
            temp = stack.pop();
            res.add(temp.getData());
          }
        } else {
          current = temp;
        }
      }
    }
    return res;
  }

  /**
   * BFS traversal
   * https://www.youtube.com/watch?v=9PHkM0Jri_4&t=265s
   */
  private static void levelOrderTraversal(Node root) {
    if (root == null) {
      return;
    }

    Queue<Node> nodes = new LinkedList<>();
    nodes.add(root);

    // Need to add below NULL check to print the level. Otherwise, it's not needed
    //nodes.add(null);
    int level = 1;

    while (!nodes.isEmpty()) {

      int size = nodes.size();

      while (size -- >0) {
        Node current = nodes.remove();

        System.out.println(current.getData() + " " + "Level ::" + level);

        if (current.getLeft() != null) {
          nodes.add(current.getLeft());
        }

        if (current.getRight() != null) {
          nodes.add(current.getRight());
        }
      }

      level ++;
    }
  }
}

