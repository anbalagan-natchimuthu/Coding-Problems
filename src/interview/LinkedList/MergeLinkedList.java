package interview.LinkedList;

import java.util.PriorityQueue;

public class MergeLinkedList {

  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(5);
    Node n3 = new Node(7);
    Node n4 = new Node(8);
    Node n5 = new Node(15);
    Node n6 = new Node(16);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    n5.next = n6;

    Node x1 = new Node(3);
    Node x2 = new Node(5);
    Node x3 = new Node(8);
    Node x4 = new Node(15);
    Node x5 = new Node(26);

    x1.next = x2;
    x2.next = x3;
    x3.next = x4;
    x4.next = x5;

    Node y1 = new Node(2);
    Node y2 = new Node(7);
    Node y3 = new Node(9);
    Node y4 = new Node(9);

    y1.next = y2;
    y2.next = y3;
    y3.next = y4;

    Node[] lists = new Node[3];
    lists[0] = n1;
    lists[1] = x1;
    lists[2] = y1;

    Node finalList = MergeKLinedList(lists);
    while (finalList != null) {
      System.out.print(finalList.data + " ->");
      finalList = finalList.next;
    }

    System.out.println("\n");

    Node l1 = new Node(1);
    Node l2 = new Node(5);
    Node l3 = new Node(7);
    Node l4 = new Node(8);
    Node l5 = new Node(15);
    Node l6 = new Node(16);

    l1.next = l2;
    l2.next = l3;
    l3.next = l4;
    l4.next = l5;
    l5.next = l6;

    Node m1 = new Node(3);
    Node m2 = new Node(5);
    Node m3 = new Node(8);
    Node m4 = new Node(15);
    Node m5 = new Node(26);

    m1.next = m2;
    m2.next = m3;
    m3.next = m4;
    m4.next = m5;

    Node o1 = new Node(2);
    Node o2 = new Node(7);
    Node o3 = new Node(9);
    Node o4 = new Node(9);

    o1.next = o2;
    o2.next = o3;
    o3.next = o4;

    Node[] arrays = new Node[3];
    arrays[0] = l1;
    arrays[1] = m1;
    arrays[2] = o1;
    Node mergedList = mergeKLists(arrays);
    while (mergedList != null) {
      System.out.print(mergedList.data + " ->");
      mergedList = mergedList.next;
    }
  }

  private static Node mergeLinkedList(Node n1, Node x1) {
    Node head = new Node(0);
    Node newLL = head;

    while (n1 != null && x1 != null) {
      if (n1.data < x1.data) {
        newLL.next = n1;
        n1 = n1.next;
      } else {
        newLL.next = x1;
        x1 = x1.next;
      }
      newLL = newLL.next;
    }

    while (x1 != null) {
      newLL.next = x1;
      x1 = x1.next;
      newLL = newLL.next;
    }

    while (n1 != null) {
      newLL.next = n1;
      n1 = n1.next;
      newLL = newLL.next;
    }
    return head.next;
  }

  /**
   * https://leetcode.com/problems/merge-k-sorted-lists/solution/
   */
  private static Node MergeKLinedList(Node[] lists) {
    Node finalList = lists[0];

    for (int i = 1; i < lists.length; i++) {
      finalList = mergeLinkedList(finalList, lists[i]);
    }

    return finalList;
  }

  /**
   * Time complexity : O(N\log k)O(Nlogk) where \text{k}k is the number of linked lists.
   *
   * The comparison cost will be reduced to O(\log k)O(logk) for every pop and insertion to priority queue. But
   * finding the node with the smallest value just costs O(1)O(1) time.
   * There are NN nodes in the final linked list.
   * Space complexity :
   *
   * O(n)O(n) Creating a new linked list costs O(n)O(n) space.
   * O(k)O(k) The code above present applies in-place method which cost O(1)O(1) space. And the priority queue (often
   * implemented with heaps) costs O(k)O(k) space (it's far less than NN in most situations).
   */
  public static Node mergeKLists(Node[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> {
      return n1.data - n2.data;
    });

    Node dummy = new Node(0);
    Node current = dummy;

    for (Node list : lists) {
      if (list != null) {
        queue.offer(list);
      }
    }

    while (!queue.isEmpty()) {
      // Remove Entire linked list
      Node n = queue.poll();

      current.next = n;
      current = current.next;

      if (n.next != null) {
        // Add it back entire list except current element to Queue
        queue.offer(n.next);
      }
    }

    return dummy.next;
  }
}
