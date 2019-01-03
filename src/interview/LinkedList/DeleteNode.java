package interview.LinkedList;

/**
 * Delete all occurrences of a given key in a linked list
 *
 * Given a singly linked list, delete all occurrences of a given key in it. For example, consider the following list.
 * Input: 2 -> 2 -> 1 -> 8 -> 2 ->  3 ->  2 -> 7
 *        Key to delete = 2
 * Output:  1 -> 8 -> 3 -> 7
 *
 */
public class DeleteNode {

    public static void main(String[] args) {
        Node n0 = new Node(6);
        Node n00 = new Node(6);
        Node n000 = new Node(6);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n66 = new Node(6);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(6);

        n0.next = n00;
        n00.next = n000;
        n000.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n66;
        n66.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        deleteNode(n0, 6);

        Node node2 = new Node(2);
        Node node21 = new Node(2);
        Node node22 = new Node(2);

        node2.next = node21;
        node21.next = node22;
        deleteNode(node2, 2);

        Node node3 = new Node(3);
        deleteNode(node3, 3);
    }

    private static void deleteNode(Node node, int x) {

        Node head = node;
        Node prev = null;

        while (node != null) {
            if (node.data == x) {
                if (node.next != null) {
                    node.data = node.next.data;
                    node.next = node.next.next;
                } else {
                    if (prev != null) {
                        prev.next = null;
                    }
                    node = prev;
                }
            } else {
                prev = node;
                node = node.next;
            }
        }

        if (head != null && head.data == x) {
            head = null;
            System.out.println("Empty Linked List");
        }

        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }
}
