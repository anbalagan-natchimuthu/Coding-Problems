package interview.LinkedList;

public class CircularLinkedList {

  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Node n8 = new Node(8);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    n5.next = n6;
    n6.next = n7;
    n7.next = n8;
    n8.next = n4;
    findCircular(n1);

    Node n11 = new Node(1);
    Node n22 = new Node(2);
    Node n33 = new Node(3);
    n11.next = n22;
    n22.next = n33;
    findCircular(n11);
  }

  private static void findCircular(Node head) {
    if (head == null) {
      return;
    }

    // If there is a cycle, the fast/slow pointers will intersect at some node.
    // Otherwise, there is no cycle, so we cannot find an entrance to a cycle.
    Node intersect = getIntersect(head);

    if (intersect == null) {
      System.out.println("there is no circular linked list");
      return;
    }

    Node p3 = head;
    while (p3 != intersect) {
      p3 = p3.next;
      intersect = intersect.next;
    }
    System.out.println("they first met" + p3.data);
  }

  private static Node getIntersect(Node head) {
    Node slowPointer = head;
    Node fastPointer = head;

    // A fast pointer will either loop around a cycle and meet the slow
    // pointer or reach the `null` at the end of a non-cyclic list.
    while (fastPointer != null && fastPointer.next != null) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;
      if (slowPointer == fastPointer) {
        return slowPointer;
      }
    }

    return null;
  }
}
