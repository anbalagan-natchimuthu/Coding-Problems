package interview.tree;

public class MirrorTree {

  public static void main(String[] args) {

    /**
     *             1
     *           /   \
     *          2     3
     *         / \
     *        4  5
     */

    Node node1 = new Node(1);

    Node node2 = new Node(2);
    Node node3 = new Node(3);
    node1.setLeft(node2);
    node1.setRight(node3);

    Node node4 = new Node(4);
    Node node5 = new Node(5);
    node2.setLeft(node4);
    node2.setRight(node5);

    printTree(node1);
    Node resultNode = printMirrorTree(node1);
    System.out.println();
    printTree(resultNode);
  }

  public static Node printMirrorTree(Node root) {

    if (root == null) {
      return null;
    }

    //if (root != null) {
    printMirrorTree(root.getLeft());
    printMirrorTree(root.getRight());
    Node temp = root.getLeft();
    root.setLeft(root.getRight());
    root.setRight(temp);
    //}
    return root;
  }

  public static void printTree(Node root) {
    if (root == null) {
      return;
    }

    if (root != null) {
      System.out.print(root.getData() + "  ");
      printTree(root.getLeft());
      printTree(root.getRight());
    }
  }
}
