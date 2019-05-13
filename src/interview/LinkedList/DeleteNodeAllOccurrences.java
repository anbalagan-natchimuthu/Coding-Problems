package interview.LinkedList;

/**
 * https://www.geeksforgeeks.org/delete-occurrences-given-key-linked-list/
 *
 * Delete all occurrences of a given key in a linked list
 *
 * Given a singly linked list, delete all occurrences of a given key in it. For example, consider the following list.
 * Input: 2 -> 2 -> 1 -> 8 -> 2 ->  3 ->  2 -> 7
 * Key to delete = 2
 * Output:  1 -> 8 -> 3 -> 7
 */
public class DeleteNodeAllOccurrences {

  public static void main(String[] args) {
    Node n0 = new Node(6);
    Node n00 = new Node(6);
    Node n000 = new Node(6);
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n66 = new Node(6);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(6);

    n0.next = n00;
    n00.next = n000;
    n000.next = n1;
    n1.next = n2;
    n2.next = n3;
    n3.next = n66;
    n66.next = n4;
    n4.next = n5;
    n5.next = n6;
    n6.next = n7;

    Node result = deleteNode(n0, 6);
    while (result != null) {
      System.out.print(result.data + "->");
      result = result.next;
    }
    System.out.println();

    Node node2 = new Node(2);
    Node node21 = new Node(2);
    Node node22 = new Node(2);

    node2.next = node21;
    node21.next = node22;
    result = deleteNode(node2, 2);
    System.out.println("Result after delete 2:" + result);
    System.out.println();

    Node node3 = new Node(3);
    result = deleteNode(node3, 3);
    System.out.println("Result after delete 3:" + result);
  }

  public static Node deleteNode(Node head, int val) {
    Node fakeHead = new Node(-1);
    fakeHead.next = head;
    Node curr = head, prev = fakeHead;

    while (curr != null) {
      if (curr.data == val) {
        prev.next = curr.next;
      } else {
        prev = prev.next;
      }
      curr = curr.next;
    }
    return fakeHead.next;
  }
}
