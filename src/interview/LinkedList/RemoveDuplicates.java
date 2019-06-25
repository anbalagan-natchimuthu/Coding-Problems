package interview.LinkedList;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
 * original list.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 *
 * Example 2:
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class RemoveDuplicates {

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static ListNode deleteDuplicates(ListNode head) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode pre = dummyHead;

    while (head != null) {
      while (head.next != null && head.val == head.next.val) {
        head = head.next;
      }

      if (head == pre.next) {
        pre = pre.next;
      } else {
        pre.next = head.next;
      }
      head = head.next;
    }
    return dummyHead.next;
  }

  public static void printNode(ListNode head) {
    ListNode dummy = head;
    while (dummy != null) {
      System.out.print(dummy.val + "->");
      dummy = dummy.next;
    }
  }

  public static void main(String[] args) {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(3);
    ListNode n5 = new ListNode(4);
    ListNode n6 = new ListNode(4);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    n5.next = n6;

    System.out.println("\n\n****** Before Deleting Duplicates *********");
    printNode(n1);
    System.out.println("\n****** After Deleting Duplicates *********");
    ListNode resultNode = deleteDuplicates(n1);
    printNode(resultNode);

    System.out.println("\n\n");

    ListNode m1 = new ListNode(1);
    ListNode m2 = new ListNode(2);
    ListNode m3 = new ListNode(3);
    ListNode m4 = new ListNode(3);
    ListNode m5 = new ListNode(4);
    ListNode m6 = new ListNode(4);

    m1.next = m2;
    m2.next = m3;
    m3.next = m4;
    m4.next = m5;
    m5.next = m6;

    System.out.println("\n****** Before Deleting Duplicates *********");
    printNode(m1);
    System.out.println("\n****** After Deleting Duplicates *********");
    ListNode result = removeDuplicates(m1);
    printNode(result);

    System.out.println("\n\n");
  }

  /**
   * Given a sorted linked list, delete all duplicates such that each element appear only once.
   *
   * Example 1:
   *
   * Input: 1->1->2
   * Output: 1->2
   * Example 2:
   *
   * Input: 1->1->2->3->3
   * Output: 1->2->3
   */
  public static ListNode removeDuplicates(ListNode head) {

    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode pre = dummy;

    while (head != null) {
      while (head.next != null && head.val == head.next.val) {
        head = head.next;
      }

      pre.next = head;
      head = head.next;
      pre = pre.next;
    }
    return dummy.next;
  }
}
