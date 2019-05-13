package interview.LinkedList;

/**
 * Given a singly-linked list, remove its Nth from the end node.
 * Examples:
 * 1->2->3->4->5, n=3 ==> 1->2->4->5
 * 1->2->3->4->5, n=1 ==> 1->2->3->4
 * 1->2->3->4->5, n=5 ==> 2->3->4->5
 */
class ListNode {
  int data;
  ListNode next;
  ListNode(int data) { this.data = data; }
}

public class removeNthFromEnd {
  public static ListNode removeNthFromEnd(ListNode head, int n) {

    if (head==null || n<1) return head;

    ListNode prev = head;
    ListNode cur = head;

    while(cur.next!=null) {
      if (--n<0) prev = prev.next;
      cur = cur.next;
    }

    if (n<0) prev.next = prev.next.next;
    return (n==1 && cur.next==null) ? head.next : head;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(1);
    ListNode l2 = new ListNode(2);
    ListNode l3 = new ListNode(3);
    ListNode l4 = new ListNode(4);
    ListNode l5 = new ListNode(5);

    l1.next = l2;
    l2.next = l3;
    l3.next = l4;
    l4.next = l5;

    ListNode result = removeNthFromEnd(l1, 3);

    while(result != null) {
      System.out.print(result.data + " ");
      result = result.next;
    }
  }
}
