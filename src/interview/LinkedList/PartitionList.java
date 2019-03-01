package interview.LinkedList;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class PartitionList {

  public static Node partition(Node root, int x) {
    Node before_head = new Node(0);
    Node before = before_head;
    Node after_head = new Node(0);
    Node after = after_head;

    while (root != null) {
      if (root.data < x) {
        before.next = root;
        before = before.next;
      } else {
        after.next = root;
        after = after.next;
      }
      root = root.next;
    }
    after.next = null;
    before.next = after_head.next;

    return before_head.next;
  }

  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(4);
    Node n3 = new Node(3);
    Node n4 = new Node(2);
    Node n5 = new Node(5);
    Node n6 = new Node(2);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    n5.next = n6;
    Node resultNode = partition(n1, 3);

    while (resultNode != null) {
      System.out.print(resultNode.data + "->");
      resultNode = resultNode.next;
    }
  }
}
