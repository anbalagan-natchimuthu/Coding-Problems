package interview.LinkedList;

public class MiddleOfLinkedList {

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

        //findMidElementOfLL(n1);
        midOfLLL(n1);
    }

    private static void findMidElementOfLL(Node head) {
        Node p1 = head;
        Node p2 = head;

        if (head == null) {
            return;
        }

        if (head.next == null) {
            System.out.println(head.data);
            return;
        }

        while (p2.next != null) {
            p2 = p2.next.next;
            if (p2 == null) {
                System.out.println(p1.data);
                return;
            }
            p1 = p1.next;
        }
        System.out.println(p1.data);
    }

    private static void midOfLLL(Node root) {
        if (root == null) {
            return;
        }

        if (root.next == null) {
            System.out.println("single elemnt " + root.data);
        }

        Node p1 = root;
        Node p2 = root;
        while (p2 != null) {

            p2 = p2.next.next;
            // if (p2 == null) {
            //     System.out.println(p1.data);
            //     return;
            // }
            p1 = p1.next;
        }
        System.out.println(p1.data);
    }
}
// 2 pointers to get the middle element
// At any point of time p2 is null return p1 // this will take care of odd even numbers of node
// check if one element
