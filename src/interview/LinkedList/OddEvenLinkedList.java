package interview.LinkedList;

public class OddEvenLinkedList {

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
        oddevnLL(n1);
    }

    private static Node oddEvenLinkedList(Node head) {
        Node p1 = head;
        Node p2 = head.next;
        Node finalNode = head;
        Node connectNode = head.next;

        if (head == null) {
            return head;
        }

        while (p1 != null && p2 != null) {
            Node t = p2.next;
            if (t == null) {
                break;
            }
        }

        p1.next = connectNode;

        return finalNode;
    }

    private static void oddevnLL(Node head) {
        Node p1 = head;
        Node p2 = head.next;
        Node finalNode = head;
        Node connectNode = head.next;

        while (p1.next != null && p2.next != null) {
            p1.next = p1.next.next;
            p1 = p1.next;
            p2.next = p2.next.next;
            p2 = p2.next;
        }
        p1.next = connectNode;
        while (finalNode != null) {
            System.out.println(finalNode.data);
            finalNode = finalNode.next;
        }
    }
}

