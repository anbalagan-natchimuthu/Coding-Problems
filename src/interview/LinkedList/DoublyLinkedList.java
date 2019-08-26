package interview.LinkedList;

public class DoublyLinkedList {

  static class DoublyLinkedNode {
    int data;
    DoublyLinkedNode next;
    DoublyLinkedNode prev;
    public DoublyLinkedNode(int data) { this.data = data; }
  }

  /**
   * In doubly linked list, implement a method to insert a node at specified position and return the list's head.
   * Do nothing if insertion position is outside the bounds of the list.
   *
   * insertAtPos(1<=>2<=>3,4,2) ==> 1<=>4<=>2<=>3
   * insertAtPos(1,4,3) ==> 1
   */
  public static DoublyLinkedNode insertAtPos(DoublyLinkedNode head, int data, int pos) {

    if (pos <1 || (head == null && pos >1)) {
      return head;
    }

    if (pos == 1) {
      DoublyLinkedNode newNode = new DoublyLinkedNode(data);
      newNode.next = head;
      if (head != null) {
        head.prev = newNode;
      }
      return newNode;
    }

    int i = 1;
    DoublyLinkedNode returnNode = head;
    while (head != null) {
      i++;
      if (pos == i) {
        DoublyLinkedNode newNode = new DoublyLinkedNode(data);
        newNode.next = head.next;
        head.next = newNode;
        newNode.prev = head;
        break;
      }
      head = head.next;
    }

    return returnNode;

  }

  private static void printNode(DoublyLinkedNode node) {
    while(node != null) {
      System.out.print(node.data + "->");
      node = node.next;
    }
  }

  public static void main(String[] args) {
    DoublyLinkedNode node1 = new DoublyLinkedNode(1);
    DoublyLinkedNode node2 = new DoublyLinkedNode(2);
    DoublyLinkedNode node3 = new DoublyLinkedNode(3);
    node1.next = node2;
    node2.prev = node1;
    node2.next = node3;
    node3.prev = node2;

    DoublyLinkedNode result = insertAtPos(node1, 4, 1);
    printNode(result);


  }
}
