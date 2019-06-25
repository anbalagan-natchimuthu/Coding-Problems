package interview.tree;

class BinaryNode {

  private BinaryNode leftNode;
  private BinaryNode rightNode;
  private int value;

  public BinaryNode(int value) {
    this.value = value;
    this.leftNode = null;
    this.rightNode = null;
  }

  public BinaryNode(int value, BinaryNode leftNode, BinaryNode rightNode) {
    this.value = value;
    this.leftNode = leftNode;
    this.rightNode = rightNode;
  }

  public BinaryNode getLeftNode() {
    return leftNode;
  }

  public void setLeftNode(BinaryNode leftNode) {
    this.leftNode = leftNode;
  }

  public BinaryNode getRightNode() {
    return rightNode;
  }

  public void setRightNode(BinaryNode rightNode) {
    this.rightNode = rightNode;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}

class BinaryTree {

  BinaryNode root;

  public void add(BinaryNode currentnode) {
    if (root == null) {
      root = currentnode;
      return;
    }

    addCurrentnode(root, currentnode);
  }

  private void addCurrentnode(BinaryNode rootNode, BinaryNode currentnode) {

    if (currentnode.getValue() < rootNode.getValue()) {
      if (rootNode.getLeftNode() == null) {
        rootNode.setLeftNode(currentnode);
      } else {
        addCurrentnode(rootNode.getLeftNode(), currentnode);
      }
    } else {
      if (rootNode.getRightNode() == null) {
        rootNode.setRightNode(currentnode);
      } else {
        addCurrentnode(rootNode.getRightNode(), currentnode);
      }
    }
  }

  public int getMaximum() {
    if (root == null) {
      return 0;
    } else {
      BinaryNode headNode = root;
      while (headNode.getRightNode() != null) {
        headNode = headNode.getRightNode();
      }
      return headNode.getValue();
    }
  }

  public int getMinimum() {
    if (root == null) {
      return 0;
    } else {
      BinaryNode headNode = root;
      while (headNode.getLeftNode() != null) {
        headNode = headNode.getLeftNode();
      }
      return headNode.getValue();
    }
  }

  public int getHeight() {
    System.out.println("Total Nodes:" + getTotalNodes(root));
    return getHeightVal(root);
  }

  private int getHeightVal(BinaryNode currentHead) {
    if (currentHead == null) {
      return 0;
    }

    int left = getHeightVal(currentHead.getLeftNode());
    int right = getHeightVal(currentHead.getRightNode());
    return 1 + Math.max(left, right);
  }

  private int getTotalNodes(BinaryNode currentHead) {
    if (currentHead == null) {
      return 0;
    }

    return (1 + getTotalNodes(currentHead.getLeftNode()) + getTotalNodes(currentHead.getRightNode()));
  }

  /**
   * https://www.youtube.com/watch?v=MILxfAbIhrE
   */
  public boolean isBinarySearchTree() {
    return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isBinarySearchTree(BinaryNode rootNode, int low, int high) {
    if (rootNode == null) {
      return true;
    }

    if (low < rootNode.getValue() && high > rootNode.getValue()) {
      return isBinarySearchTree(rootNode.getLeftNode(), low, rootNode.getValue()) && isBinarySearchTree(
          rootNode.getRightNode(), rootNode.getValue(), high);
    } else {
      return false;
    }
  }

  public boolean isSameTree(BinaryTree tree1, BinaryTree tree2) {
    return isSameTree(tree1.root, tree2.root);
  }

  public boolean isSameTree(BinaryNode tree1, BinaryNode tree2) {
    if (tree1 == null && tree2 == null) {
      return true;
    }

    if (tree1 == null || tree2 == null) {
      return false;
    }

    return tree1.getValue() == tree2.getValue() && isSameTree(tree1.getLeftNode(), tree2.getLeftNode()) && isSameTree(
        tree1.getRightNode(), tree2.getRightNode());
  }
}

public class BinarySearchTree {

  public static void main(String args[]) {
    BinaryNode node = new BinaryNode(20);
    BinaryTree b = new BinaryTree();
    b.add(node);
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum() + ":Height:" + b.getHeight());

    BinaryNode b6 = new BinaryNode(80);
    BinaryNode b7 = new BinaryNode(90);
    BinaryNode b8 = new BinaryNode(60, b6, b7);
    b.add(b8);
    System.out.println("Is Binary Tree:" + b.isBinarySearchTree());

    BinaryNode b1 = new BinaryNode(85);
    BinaryNode b2 = new BinaryNode(65);
    BinaryNode b3 = new BinaryNode(70, b2, b1);
    b.add(b3);

    BinaryNode node1 = new BinaryNode(25);
    b.add(node1);
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum() + ":Height:" + b.getHeight());

    BinaryNode node2 = new BinaryNode(15);
    b.add(node2);
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum() + ":Height:" + b.getHeight());

    System.out.println(b.isBinarySearchTree());

    b.add(new BinaryNode(78));
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum() + ":Height:" + b.getHeight());
    System.out.println("isSameTree:" + b.isSameTree(b, b));
    System.out.println(b.isBinarySearchTree());

    b.add(new BinaryNode(10));
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum() + ":Height:" + b.getHeight());
    System.out.println(b.isBinarySearchTree());

    b.add(new BinaryNode(32));
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum() + ":Height:" + b.getHeight());

    System.out.println(b.isBinarySearchTree());

    b3 = new BinaryNode(45);
    BinaryNode b4 = new BinaryNode(60);
    BinaryNode b5 = new BinaryNode(50, b4, b3);

    b.add(b5);
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum() + ":Height:" + b.getHeight());
    System.out.println(b.isBinarySearchTree());

    b.add(new BinaryNode(70));
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum());

    b.add(new BinaryNode(30));
    System.out.println("Maximum:" + b.getMaximum() + ":Minimum:" + b.getMinimum());
  }
}
