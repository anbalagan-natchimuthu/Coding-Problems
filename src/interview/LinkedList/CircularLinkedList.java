package interview.LinkedList;

public class CircularLinkedList {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n4;
        findCircular(n1);
    }

    private static void findCircular(Node node) {
        if (node == null) {
            return;
        }

        Node p1 = node;
        Node p2 = node;
        Node p3 = node;
        while (p2 != null) {
            p2 = p2.next.next;
            p1 = p1.next;
            if (p1.data == p2.data) {
                System.out.println("they meet" + p2.data);
                break;
            }
        }

        while (p3 != null) {
            p3 = p3.next;
            p2 = p2.next;
            if (p3.data == p2.data) {
                System.out.println("they first met" + p3.data);
                break;
            }
        }
    }
}
