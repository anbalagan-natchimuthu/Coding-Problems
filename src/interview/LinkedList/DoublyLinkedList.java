package interview.LinkedList;

public class DoublyLinkedList {

  static class DoublyLinkedNode {
    int data;
    DoublyLinkedNode next;
    DoublyLinkedNode prev;
    public DoublyLinkedNode(int data) { this.data = data; }
  }

  public static DoublyLinkedNode insertAtPos(DoublyLinkedNode head, int data, int pos) {

    if (head == null) {
      if (pos == 1) {
        head = new DoublyLinkedNode(data);
      }
      return head;
    }


    DoublyLinkedNode current = head;
    DoublyLinkedNode previous = null;
    pos = pos -1;

    while (current != null && pos > 0) {
      previous = current;
      current = current.next;
      pos --;
    }
    if (pos == 0) {
      DoublyLinkedNode node = new DoublyLinkedNode(data);
      if (current != null) {
        current.prev = node;
      }
      node.prev = previous;
      node.next = current;
      if (previous != null) {
        previous.next = node;
      } else {
        return node;
      }
    }

    return head;

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
