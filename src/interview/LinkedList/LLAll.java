package interview.LinkedList;

public class LLAll {

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

    // circularLL(n1);
    // deleteNode(n1, 3);
    middleOfLL(n1);

    Node l1 = new Node(4);
    l1.next = new Node(4);
    l1.next.next = new Node(8);

    Node l2 = new Node(5);
    l2.next = new Node(6);
    l2.next.next = new Node(6);

    Node res = addTwoNumbers(l1, l2);
    while (res != null) {
      System.out.print(res.data + " ");
      res = res.next;
    }
  }

  /**
   * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
   * reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
   *
   * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
   *
   * Example:
   *
   * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
   * Output: 7 -> 0 -> 8
   * Explanation: 342 + 465 = 807.
   *
   * https://leetcode.com/problems/add-two-numbers/
   */
  public static Node addTwoNumbers(Node c1, Node c2) {
    Node dummyHead = new Node(0);
    Node d = dummyHead;
    int sum = 0;
    while (c1 != null || c2 != null) {
      if (c1 != null) {
        sum += c1.data;
        c1 = c1.next;
      }
      if (c2 != null) {
        sum += c2.data;
        c2 = c2.next;
      }
      d.next = new Node(sum % 10);
      d = d.next;
      sum /= 10;
    }
    if (sum == 1) {
      d.next = new Node(1);
    }
    return dummyHead.next;
  }

  private static void circularLL(Node node) {

    Node node1 = node;
    Node node2 = node;
    Node node3 = node;

    while (node2.next != null) {
      node2 = node2.next.next;
      node1 = node1.next;

      if (node1.data == node2.data) {
        System.out.println("they meet" + node2.data);
        break;
      }
    }

    while (node3 != null) {
      node2 = node2.next;
      node3 = node3.next;
      if (node3.data == node2.data) {
        System.out.println("first meet at " + node3.data);
        break;
      }
    }
  }

  private static void deleteNode(Node node, int x) {
    Node newNode = node;

    while (node.next != null) {
      if (node.data == x) {
        node.data = node.next.data;
        node.next = node.next.next;
      } else {
        node = node.next;
      }
    }

    while (newNode != null) {
      System.out.println(newNode.data);
      newNode = newNode.next;
    }
  }

  private static void middleOfLL(Node node) {
    Node p1 = node;
    Node p2 = node;

    while (p2 != null) {
      p2 = p2.next.next;
      p1 = p1.next;
    }
    System.out.println("Middle element " + p1.data);
  }
}
