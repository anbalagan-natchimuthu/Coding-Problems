package interview.LinkedList;

import java.util.Stack;

public class ReverseOfLinkedList {

  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    n5.next = n6;
    reverseOfLinkedList(n1);

    System.out.println("*************Reversed Linked List Iterative Way***********");

    Node d1 = new Node(10);
    Node d2 = new Node(20);
    Node d3 = new Node(30);
    Node d4 = new Node(40);
    Node d5 = new Node(50);
    Node d6 = new Node(60);

    d1.next = d2;
    d2.next = d3;
    d3.next = d4;
    d4.next = d5;
    d5.next = d6;

    Node reverseNode = reverseIterativeWay(d1);
    while (reverseNode != null) {
      System.out.print("-->" + reverseNode.data);
      reverseNode = reverseNode.next;
    }

    System.out.println("\n\n*************Reversed Linked List Recursive Way ***********");

    Node r1 = new Node(10);
    Node r2 = new Node(20);
    Node r3 = new Node(30);
    Node r4 = new Node(40);
    Node r5 = new Node(50);
    Node r6 = new Node(60);

    r1.next = r2;
    r2.next = r3;
    r3.next = r4;
    r4.next = r5;
    r5.next = r6;

    Node reverseNode1 = reverseRecursiveWay(r1);
    while (reverseNode1 != null) {
      System.out.print("-->" + reverseNode1.data);
      reverseNode1 = reverseNode1.next;
    }
  }

  /**
   * Print Reversed Linked List
   * @param node
   */
  private static void reverseOfLinkedList(Node node) {

    if (node == null) {
      return;
    }

    // While traversing push in stack in first pass
    // Next pass keep popping from stack until stack is empty

    if (node.next == null) {
      System.out.println("Single element in list");
      System.out.println(node.data);
      return;
    }
    Stack stack = new Stack();
    while (node != null) {
      stack.push(node.data);
      node = node.next;
    }

    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }

  /**
   * https://leetcode.com/problems/reverse-linked-list/
   *
   * Reverse a singly linked list.
   *
   * Example:
   *
   * Input: 1->2->3->4->5->NULL
   * Output: 5->4->3->2->1->NULL
   */
  public static Node reverseIterativeWay(Node head) {
    if (head == null) {
      return head;
    }

    Node prevNode = null;
    Node currNode = head;

    while (null != currNode) {
      Node nextNode = currNode.next;
      currNode.next = prevNode;
      prevNode = currNode;
      currNode = nextNode;
    }

    return prevNode;
  }

  /**
   * https://www.youtube.com/watch?v=MRe3UsRadKw
   */
  public static Node reverseRecursiveWay(Node head) {
    if(head == null || head.next == null) {
      return head;
    }
    Node prev = reverseRecursiveWay(head.next);
    head.next.next = head;
    head.next = null;
    return prev;
  }

  // private static Node reversePair(Node head){
  //     Node prev = null;
  //     Node curr = head;
  //     Node next = head.next;
  //     boolean rev = true;
  //     while(rev){
  //         curr.next = prev;
  //         prev = next;
  //         rev = false;
  //     }
  // }
}

// 1>2>3>4
// null < 1 < 2
//prev > curr > next
//

//prev = curr.next;


