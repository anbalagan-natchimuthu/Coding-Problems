package interview.LinkedList;

public class SwapElementsOfLinkedList {

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

        swapNodes(n1, 2, 5);
    }

    private static void swapNodes(Node n1, int x, int y) {

        Node p1_prev = null;
        Node p2_prev = null;
        Node p1 = n1;
        Node p2 = n1;

        while (p1 != null) {
            if (p1.next.data == x) {
                p1_prev = p1;
                p1 = p1.next;
                break;
            }
            p1 = p1.next;
        }

        while (p2 != null) {
            if (p2.next.data == y) {
                p2_prev = p2;
                p2 = p2.next;
                break;
            }
            p2 = p2.next;
        }

        Node temp = p1;
        p1 = p2;
        p2 = temp;

        p1_prev.next = p2;
        p1_prev.next.next = p2.next;

        p2_prev.next = p1;
        p2_prev.next.next = p1.next;
        System.out.println("P1-->" + p1.data);
        System.out.println("P2 " + p2.data);

        while (n1 != null) {
            if (n1.next != null && n1.next.data == x) {
                n1.next = p2;
            }
            if (n1.next != null && n1.next.data == y) {
                n1.next = p1;
            }
            System.out.println("N1 " + n1.data);
            n1 = n1.next;
        }
    }
}
