package interview.LinkedList;

/**
 * Write a function to insert a new value in a sorted Circular Linked List (CLL).
 * https://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 */
public class InsertCircularLinkedList {

  Node head;

  // Constructor
  InsertCircularLinkedList() {
    head = null;
  }

  /* function to insert a new_node in a list in sorted way.
   Note that this function expects a pointer to head node
   as this can modify the head of the input linked list */
  void sortedInsert(Node new_node) {
    Node current = head;

    // Case 1 of the above algo
    if (current == null) {
      new_node.next = new_node;
      head = new_node;
    }

    // Case 2 of the above algo
    else if (current.data >= new_node.data) {

            /* If value is smaller than head's value then
             we need to change next of last node */
      while (current.next != head) {
        current = current.next;
      }

      current.next = new_node;
      new_node.next = head;
      head = new_node;
    }

    // Case 3 of the above algo
    else {

      /* Locate the node before the point of insertion */
      while (current.next != head && current.next.data < new_node.data) {
        current = current.next;
      }

      new_node.next = current.next;
      current.next = new_node;
    }
  }

  // Utility method to print a linked list
  void printList() {
    if (head != null) {
      Node temp = head;
      do {
        System.out.print(temp.data + " ");
        temp = temp.next;
      } while (temp != head);
    }
  }

  // Driver code to test above
  public static void main(String[] args) {
    InsertCircularLinkedList list = new InsertCircularLinkedList();

    // Creating the linkedlist
    int arr[] = new int[]{12, 56, 2, 11, 1, 90};

    /* start with empty linked list */
    Node temp = null;

        /* Create linked list from the array arr[].
         Created linked list will be 1->2->11->12->56->90*/
    for (int i = 0; i < 6; i++) {
      temp = new Node(arr[i]);
      list.sortedInsert(temp);
    }

    list.printList();

    System.out.println("\n\n***** Circulr Linked List After inserting node at tail *****");
    Node res = insertAtTail(null, 1);
    if (res != null) {
      Node resTemp = res;
      do {
        System.out.print(resTemp.data + " -> ");
        resTemp = resTemp.next;
      } while (resTemp != res);
    }

    System.out.println("\n\n***** Circulr Linked List After inserting node at tail *****");
    Node level1 = new Node(1);
    Node level2 = new Node(2);
    level1.next = level2;
    level2.next = level1;

    res = insertAtTail(level1, 3);
    if (res != null) {
      Node resTemp = res;
      do {
        System.out.print(resTemp.data + " -> ");
        resTemp = resTemp.next;
      } while (resTemp != res);
    }
  }

  /**
   * Given a circular linked list, write a method to insert a node at its tail. Return the list's head.
   *
   * Examples:
   *
   * *x = indicates head node
   * Insert 1 ==> *1
   * Insert 2 ==> 1->2->*1
   * Insert 3 ==> 1->2->3->*1
   */
  public static Node insertAtTail(Node head, int data) {

    Node newNode = new Node(data);
    Node curr = head;
    newNode.next = newNode; //after creation, point to itself

    if (head == null) {
      head = newNode;
    } else {
      while (curr.next != head) {
        curr = curr.next;
      }
      newNode.next = head;
      curr.next = newNode;
    }
    return head;
  }

  static class Node {

    int data;
    Node next;

    Node(int d) {
      data = d;
      next = null;
    }
  }
}
