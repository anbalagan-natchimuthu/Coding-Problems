package interview.LinkedList;

public class IntersectionOfLinkedList {

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

        Node x1 = new Node(11);
        Node x2 = new Node(21);
        Node x3 = new Node(31);
        Node x4 = new Node(5);
        Node x5 = new Node(6);

        x1.next = x2;
        x2.next = x3;
        x3.next = x4;
        x4.next = x5;

        Node node = intersectingElement(n1, x1);
        System.out.println("Intersecting node " + node.data);
    }

    private static Node intersectingElement(Node n1, Node x1) {

        int n1Size = 0;
        int x1Size = 0;

        Node n1Temp = n1;
        Node x1Temp = x1;

        if (n1 == null || x1 == null) {
            return null;
        }
        while (n1Temp.next != null) {
            n1Size++;
            n1Temp = n1Temp.next;
        }

        while (x1Temp.next != null) {
            x1Size++;
            x1Temp = x1Temp.next;
        }

        if (n1Temp.data != x1Temp.data) {
            System.out.println("They dont meet");
            return null;
        }
        int diff = 0;
        String indicateListToBeFwded = null;
        if (x1Size > n1Size) {
            diff = x1Size - n1Size;
            indicateListToBeFwded = "x1";
        } else {
            diff = n1Size - x1Size;
            indicateListToBeFwded = "n1";
        }

        if (("n1").equalsIgnoreCase(indicateListToBeFwded)) {
            while (diff != 0) {
                n1 = n1.next;
                diff--;
            }
        } else if ("x1".equalsIgnoreCase(indicateListToBeFwded)) {
            while (diff != 0) {
                x1 = x1.next;
                diff--;
            }
        }

        while (n1.next != null && x1.next != null) {

            if (n1.next.data == x1.next.data) {

                System.out.println("Intersection found" + n1.next.data);
                return n1.next;
            }
            n1 = n1.next;
            x1 = x1.next;
        }

        return null;
    }
}
