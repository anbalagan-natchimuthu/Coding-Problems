package interview.tree;

public class Node {

    protected Node left;

    protected Node right;

    protected int data;

    protected int height;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, int height) {
        this.data = data;
        this.height = height;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
