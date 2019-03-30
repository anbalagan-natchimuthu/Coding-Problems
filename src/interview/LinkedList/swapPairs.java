package interview.LinkedList;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class swapPairs {

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode temp = head.next;
    head.next = swapPairs(head.next.next);
    temp.next = head;
    return temp;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(3);
    l1.next.next.next = new ListNode(4);

    ListNode result = swapPairs(l1);
    while (result != null) {
      System.out.print(result.val + "->");
      result = result.next;
    }

    System.out.println("\n");

    ListNode l2 = new ListNode(11);
    l2.next = new ListNode(22);
    l2.next.next = new ListNode(33);

    ListNode result1 = swapPairs(l2);
    while (result1 != null) {
      System.out.print(result1.val + "->");
      result1 = result1.next;
    }
  }

}
