package interview.LinkedList;

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
        Node x2 = new Node(6);
        Node x3 = new Node(10);
        Node x4 = new Node(25);
        Node x5 = new Node(26);

        x1.next = x2;
        x2.next = x3;
        x3.next = x4;
        x4.next = x5;
        Node finalList = mergeLinkedList(n1, x1);
        while (finalList != null) {
            System.out.println(finalList.data);
            finalList = finalList.next;
        }
    }

    private static Node mergeLinkedList(Node n1, Node x1) {
        Node head = new Node(0);
        Node newLL = head;

        while (n1 != null || x1 != null) {
            if (n1 != null && x1 != null) {
                if (n1.data < x1.data) {
                    newLL.next = n1;
                    n1 = n1.next;
                } else if (n1.data > x1.data) {
                    newLL.next = x1;
                    x1 = x1.next;
                }
                newLL = newLL.next;
            } else if (n1 == null) {
                newLL.next = x1;
                break;
            } else if (x1 == null) {
                newLL.next = n1;
                break;
            }
        }
        return head.next;
    }
}
