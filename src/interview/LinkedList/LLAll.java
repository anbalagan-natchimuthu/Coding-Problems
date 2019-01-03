package interview.LinkedList;

public class LLAll {

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

        // circularLL(n1);
        // deleteNode(n1, 3);
        middleOfLL(n1);
    }

    private static void circularLL(Node node) {

        Node node1 = node;
        Node node2 = node;
        Node node3 = node;

        while (node2.next != null) {
            node2 = node2.next.next;
            node1 = node1.next;

            if (node1.data == node2.data) {
                System.out.println("they meet" + node2.data);
                break;
            }
        }

        while (node3 != null) {
            node2 = node2.next;
            node3 = node3.next;
            if (node3.data == node2.data) {
                System.out.println("first meet at " + node3.data);
                break;
            }
        }
    }

    private static void deleteNode(Node node, int x) {
        Node newNode = node;

        while (node.next != null) {
            if (node.data == x) {
                node.data = node.next.data;
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        while (newNode != null) {
            System.out.println(newNode.data);
            newNode = newNode.next;
        }
    }

    private static void middleOfLL(Node node) {
        Node p1 = node;
        Node p2 = node;

        while (p2 != null) {
            p2 = p2.next.next;
            p1 = p1.next;
        }
        System.out.println("Middle element " + p1.data);
    }
}
